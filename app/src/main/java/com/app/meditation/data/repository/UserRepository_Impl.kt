package com.app.meditation.data.repository

import com.app.MyApp
import com.app.meditation.R
import com.app.meditation.domain.repository.UserRepository
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences

class UserRepository_Impl(var utilsSharedPreferences: UtilsSharedPreferences,var applicationContext: MyApp) : UserRepository {
    override suspend fun getUserName(): String {
      return  utilsSharedPreferences.getString(applicationContext.resources.getResourceName(R.string.user_name))?: ""
    }
}