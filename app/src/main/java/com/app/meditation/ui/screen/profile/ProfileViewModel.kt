package com.app.meditation.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.app.MyApp
import com.app.meditation.R
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    var preferences: UtilsSharedPreferences,
    var myApp: MyApp
) : ViewModel() {

    var mUserName = MutableStateFlow<String>(getUserName())


    private fun getUserName(): String {
        return preferences.getString(myApp.resources.getResourceName(R.string.user_name)).toString()

    }

}