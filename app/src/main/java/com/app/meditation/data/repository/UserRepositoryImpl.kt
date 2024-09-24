package com.app.meditation.data.repository

import com.app.MyApp
import com.app.meditation.R
import com.app.meditation.data.model.ModelProfile
import com.app.meditation.domain.repository.UserRepository
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences

class UserRepositoryImpl(
    private var utilsSharedPreferences: UtilsSharedPreferences,
    private var applicationContext: MyApp
) : UserRepository {

    override suspend fun getFirstName(): String {
        return utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_firstname))
    }

    override suspend fun getUserName(): String {
        return utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_name))
    }

    override suspend fun getProfile(): ModelProfile {
        val uid = utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_uid))
        val name = utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_name))
        val imageURL = utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_img_url))

        return ModelProfile(uId = uid, name = name, imageURL = imageURL )
    }

    override suspend fun userLogout() {
        utilsSharedPreferences.clearAllPreferences()
    }
}