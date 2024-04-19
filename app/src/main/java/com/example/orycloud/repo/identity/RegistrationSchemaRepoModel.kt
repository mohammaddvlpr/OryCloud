package com.example.orycloud.repo.identity

import com.google.gson.annotations.SerializedName

data class RegistrationSchemaRepoModel(
    @SerializedName("ui")
    val registrationUiRepoModel: RegistrationUiRepoModel,
    @SerializedName("id")
    val flowId: String
)

data class RegistrationUiRepoModel(
    @SerializedName("nodes")
    val nodes: List<NodeRepoModel>,
    @SerializedName("action")
    val action: String,
)

data class NodeRepoModel(
    @SerializedName("type")
    val nodeType: String,
    @SerializedName("attributes")
    val attributes: Attributes,
    @SerializedName("meta")
    val metaRepoModel: MetaRepoModel?
)

data class MetaRepoModel(
    @SerializedName("label")
    val label: LabelRepoModel
)

data class LabelRepoModel(
    @SerializedName("text")
    val text: String
)

data class Attributes(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("required")
    val required: Boolean
)
