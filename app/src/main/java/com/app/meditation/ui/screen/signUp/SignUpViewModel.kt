package com.app.meditation.ui.screen.signUp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(): ViewModel() {

    var mEmailText = MutableStateFlow<String>("")
    var mNameText = MutableStateFlow<String>("")
    var mPassText = MutableStateFlow<String>("")

    fun validation(
        showToast: (String) -> Unit
    ): Boolean {
        if (mEmailText.value.isEmpty()) {

            showToast("Please enter email address")
            return false
        }
        else if (mPassText.value.isEmpty()) {

            showToast("Please enter password")
            return false
        }

        return true

    }

}