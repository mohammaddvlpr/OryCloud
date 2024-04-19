package com.example.orycloud.repo.identity

import com.example.orycloud.ui.register.AttributesUiModel
import com.example.orycloud.ui.register.Form
import com.example.orycloud.ui.register.LabelUiModel
import com.example.orycloud.ui.register.MetaUiModel
import com.example.orycloud.ui.register.NodeUiModel
import com.example.orycloud.ui.register.RegistrationSchemaUiModel
import com.example.orycloud.ui.register.RegistrationUiUiModel
import com.example.orycloud.ui.signin.LoginFlowUiModel
import javax.inject.Inject

class IdentityMapper @Inject constructor() {

    fun mapFromApi(registrationSchemaRepoModel: RegistrationSchemaRepoModel): RegistrationSchemaUiModel {
        return RegistrationSchemaUiModel(
            registrationUiUiModel = RegistrationUiUiModel(
                nodes = registrationSchemaRepoModel.registrationUiRepoModel.nodes.map {
                    NodeUiModel(
                        nodeType = it.nodeType,
                        attributes = AttributesUiModel(
                            name = it.attributes.name,
                            type = it.attributes.type,
                            required = it.attributes.required
                        ),
                        metaUiModel = if (it.metaRepoModel?.label?.text != null)
                            MetaUiModel(
                                label = LabelUiModel(
                                    it.metaRepoModel.label.text
                                )
                            )
                        else
                            null
                    )

                },
                action = registrationSchemaRepoModel.registrationUiRepoModel.action
            ),
            flowId = registrationSchemaRepoModel.flowId
        )
    }

    fun toMap(forms: List<Form>): Map<String, String> {
       return forms.associate { it.name to it.value }
    }

    fun toUiLoginFlow(loginFlowRepoModel: LoginFlowRepoModel): LoginFlowUiModel {
        return LoginFlowUiModel(loginFlowRepoModel.id)
    }
}