package com.example.orycloud.ui.register


data class Form(
    val name: String,
    val formType: FormType,
    val title: String,
    val value: String = String(),
    val required: Boolean
)

data class RegisterScreenState(
    val forms: List<Form> = listOf(),
    val showSuccessToast: Boolean = false
)

