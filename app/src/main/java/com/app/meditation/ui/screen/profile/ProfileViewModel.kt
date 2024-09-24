package com.app.meditation.ui.screen.profile

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.MyApp
import com.app.meditation.R
import com.app.meditation.domain.usecase.GetProfileUseCase
import com.ctuil.intranet.businesslogic.preferences.UtilsSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: GetProfileUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ProfileTabState())
    val state : StateFlow<ProfileTabState>  =  _state.asStateFlow()

    init {
        getUserData()
    }

    private fun getUserData() {

        viewModelScope.launch {
            val model = profileUseCase.getUserData()
            _state.update {
                it.copy(name = model.name, imageURL = model.imageURL)
            }
        }
    }
}