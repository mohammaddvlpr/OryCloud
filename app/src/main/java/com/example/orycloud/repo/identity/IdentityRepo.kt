package com.example.orycloud.repo.identity

import com.example.orycloud.ui.register.Form
import com.example.orycloud.ui.register.RegistrationSchemaUiModel
import com.example.orycloud.ui.signin.LoginFlowUiModel

interface IdentityRepo {

    suspend fun getSchema(): RegistrationSchemaUiModel?
    suspend fun registerUser(forms: List<Form>, flowId: String): Boolean
    suspend fun getLoginFlow(): LoginFlowUiModel?
    suspend fun loginUser(email: String, password: String, flowId: String): Boolean
}