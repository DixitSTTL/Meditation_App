package com.app.meditation.data.repository

import com.app.MyApp
import com.app.meditation.R
import com.app.meditation.common.Resource
import com.app.meditation.data.model.ModelSignIn
import com.app.meditation.domain.repository.AuthRepository
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.util.Date

class AuthRepositoryImpl(
    private var utilsSharedPreferences: UtilsSharedPreferences,
    private var applicationContext: MyApp,
    private var firebaseApp: FirebaseAuth,
    private var firestore: FirebaseFirestore,
    private var firebaseStorage: FirebaseStorage
) :
    AuthRepository {


    override suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> =
        flow {
            emit(value = Resource.Loading())

            val response = firebaseApp.signInWithEmailAndPassword(email, password).await()

            emit(value = Resource.Success(data = response))


        }.catch {
            emit(value = Resource.Error(message = it.message.toString()))

        }

    override suspend fun signUpUser(
        modelSignIn: ModelSignIn
    ): Flow<Resource<AuthResult>> {


        return flow<Resource<AuthResult>> {
            emit(value = Resource.Loading())
            val responseAuth = firebaseApp.createUserWithEmailAndPassword(modelSignIn.email, modelSignIn.password)
                    .await()

            modelSignIn.uId = responseAuth?.user?.uid


            if (modelSignIn.uId == null) {
                emit(Resource.Error(message = "UID not found"))

            }
            else{
                val reference = firebaseStorage.reference.child("userProfile/${modelSignIn.uId}")

                val stream: InputStream = withContext(Dispatchers.IO) {
                    applicationContext.contentResolver.openInputStream(modelSignIn.uri)!!  // Replace FileInputStream

                }

                // Upload the image
                reference.putStream(stream).await()
                val imageURL= reference.downloadUrl.await()

                if (imageURL==null){
                    emit(Resource.Error(message = "image not found"))

                }
                else{
                    firestore.collection("users").document(modelSignIn.uId!!).set(
                        hashMapOf(
                            "firstName" to modelSignIn.firstName,
                            "lastName" to modelSignIn.lastName,
                            "email" to modelSignIn.email,
                            "password" to modelSignIn.password,
                            "imageURL" to imageURL,
                            "createdAt" to Date()
                        )
                    ).await()

                    emit(Resource.Success(data = responseAuth))
                }
            }
        }.catch {
            emit(value = Resource.Error(message = it.message.toString()))
        }
    }

    override suspend fun saveUserData(uId: String): Flow<Resource<Boolean>> {


        return flow{
            emit(value = Resource.Loading())
            val response = firestore.collection("users").document(uId).get().await()
            response.data?.let {

                utilsSharedPreferences.setBoolean(applicationContext.resources.getResourceName(R.string.user_login),true)
                utilsSharedPreferences.setString(applicationContext.resources.getResourceName(R.string.user_uid),uId)
                utilsSharedPreferences.setString(applicationContext.resources.getResourceName(R.string.user_name),it["firstName"].toString()+" "+it["lastName"].toString())
                utilsSharedPreferences.setString(applicationContext.resources.getResourceName(R.string.user_firstname),it["firstName"].toString())
                utilsSharedPreferences.setString(applicationContext.resources.getResourceName(R.string.user_lastname),it["lastName"].toString())
                utilsSharedPreferences.setString(applicationContext.resources.getResourceName(R.string.user_email),it["email"].toString())
                utilsSharedPreferences.setString(applicationContext.resources.getResourceName(R.string.user_img_url),it["imageURL"].toString())
                emit(value = Resource.Success(data = true))

            }
        }.catch {
            emit(value = Resource.Error(message = it.message.toString()))
        }
    }

}