package com.example.orycloud.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orycloud.repo.identity.IdentityRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val identityRepo: IdentityRepo) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state

    private var flowId: String? = null

    init {
        viewModelScope.launch {
            val result = identityRepo.getLoginFlow()
            if (result != null)
                flowId = result.id

        }
    }

    fun onSignInClick() {
        flowId?.let {
            viewModelScope.launch {

                val result = identityRepo.loginUser(
                    email = _state.value.email,
                    password = _state.value.password,
                    flowId = it
                )

                if (result)
                    _state.update { it.copy(showSuccessToast = true) }
            }
        }
    }

    fun onEmailChange(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun onToastShowed() {
        _state.update { it.copy(showSuccessToast = false) }
    }

}