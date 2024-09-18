package com.app.meditation.data.repository

import com.app.MyApp
import com.app.meditation.R
import com.app.meditation.domain.repository.AuthRepository
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences

class AuthRepository_Impl(
    var utilsSharedPreferences: UtilsSharedPreferences,
    var applicationContext: MyApp
) :
    AuthRepository {


    override suspend fun loginUser(email: String, password: String): Boolean {
        val _email =
            utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_email))
        val _password =
            utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_password))
        val status = _email == email && _password == password

        if (status)
            utilsSharedPreferences.setBoolean(
                applicationContext.resources.getResourceName(R.string.user_login),
                true
            )


        return status
    }

    override suspend fun signUpUser(name: String, email: String, password: String): Boolean {
        utilsSharedPreferences.setString(
            applicationContext.resources.getResourceName(R.string.user_name),
            name
        )
        utilsSharedPreferences.setString(
            applicationContext.resources.getResourceName(R.string.user_email),
            email
        )
        utilsSharedPreferences.setString(
            applicationContext.resources.getResourceName(R.string.user_password),
            password
        )
        return true
    }
}