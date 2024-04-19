package com.example.orycloud.ui.register


data class RegistrationSchemaUiModel(
    val registrationUiUiModel: RegistrationUiUiModel,
    val flowId: String,
)

data class RegistrationUiUiModel(
    val nodes: List<NodeUiModel>,
    val action: String,
)

data class NodeUiModel(
    val nodeType: String,
    val attributes: AttributesUiModel,
    val metaUiModel: MetaUiModel?
)

data class MetaUiModel(
    val label: LabelUiModel
)

data class LabelUiModel(
    val text: String
)

data class AttributesUiModel(
    val name: String,
    val type: String,
    val required: Boolean
)
