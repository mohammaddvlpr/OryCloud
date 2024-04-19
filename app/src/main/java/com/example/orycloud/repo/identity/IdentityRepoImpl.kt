package com.example.orycloud.repo.identity

import com.example.orycloud.api.identity.IdentityApi
import com.example.orycloud.ui.register.Form
import com.example.orycloud.ui.register.RegistrationSchemaUiModel
import com.example.orycloud.ui.signin.LoginFlowUiModel
import javax.inject.Inject

class IdentityRepoImpl @Inject constructor(
    private val identityApi: IdentityApi,
    private val identityMapper: IdentityMapper
) : IdentityRepo {

    override suspend fun getSchema(): RegistrationSchemaUiModel? {
        val result = identityApi.getRegistrationFlow(
            "Bearer ory_pat_8QEbDc2G6wd9GE5uIUvqwXnuPBGwDNpC"
        )
        val body = result.body()

        return if (result.isSuccessful && body != null)
            identityMapper.mapFromApi(body)
        else
            null
    }

    override suspend fun registerUser(forms: List<Form>, flowId: String): Boolean {
        val result = identityApi.register(
            apiKey = "Bearer ory_pat_8QEbDc2G6wd9GE5uIUvqwXnuPBGwDNpC",
            hashFields = identityMapper.toMap(forms).toMutableMap().apply {
                put("method", "password")
            },
            flowId = flowId
        )

        return result.isSuccessful
    }

    override suspend fun getLoginFlow(): LoginFlowUiModel? {
        val result = identityApi.getLoginFlow(
            "Bearer ory_pat_8QEbDc2G6wd9GE5uIUvqwXnuPBGwDNpC"
        )
        val body = result.body()

        return if (result.isSuccessful && body != null)
            identityMapper.toUiLoginFlow(body)
        else
            null
    }

    override suspend fun loginUser(email: String, password: String, flowId: String): Boolean {
        val result = identityApi.login(
            apiKey = "Bearer ory_pat_8QEbDc2G6wd9GE5uIUvqwXnuPBGwDNpC",
            hashFields = mapOf(
                "identifier" to email,
                "password" to password,
                "method" to "password"
            ),
            flowId = flowId
        )

        return result.isSuccessful
    }
}