package com.example.orycloud.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orycloud.repo.identity.IdentityRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val identityRepo: IdentityRepo) : ViewModel() {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state: StateFlow<RegisterScreenState> = _state

    private var flowId: String? = null

    init {
        viewModelScope.launch {
            val result = identityRepo.getSchema()

            if (result != null) {
                buildUiForms(result)
                flowId = result.flowId

            }

        }
    }

    private fun isValidInput(type: String): Boolean {
        return listOf("text", "number").contains(type.lowercase(Locale.ROOT))
    }

    private fun buildUiForms(registrationSchemaUiModel: RegistrationSchemaUiModel) {
        val forms = listOf(
            Form(formType = FormType.TEXT, title = "Email", required = true, name = "traits.email"),
            Form(
                formType = FormType.TEXT,
                title = "Password",
                required = true,
                name = "password"
            )
        ) + registrationSchemaUiModel.registrationUiUiModel.nodes.mapNotNull {
            val isValidInput = isValidInput(it.attributes.type)
            if (it.metaUiModel?.label?.text != null && isValidInput)
                Form(
                    formType = findFormType(it.attributes.type),
                    title = it.metaUiModel.label.text,
                    required = it.attributes.required,
                    name = it.attributes.name
                )
            else
                null

        }



        _state.update { it.copy(forms = forms) }

    }

    private fun findFormType(type: String): FormType {
        return if (type.equals("text", true))
            FormType.TEXT
        else
            FormType.NUMBER
    }

    fun onRegisterClick() {

        flowId?.let {
            viewModelScope.launch {
                val result = identityRepo.registerUser(_state.value.forms, it)
                if (result)
                    _state.update {
                        it.copy(showSuccessToast = true)
                    }
            }
        }
    }

    fun onValueChange(name: String, value: String) {
        updateValueOfInput(name, value)
    }

    private fun updateValueOfInput(name: String, value: String) {
        val forms = _state.value.forms.toMutableList()
        val changedFormIndex = forms.indexOfFirst { it.name == name }
        if (changedFormIndex == -1)
            return

        val newForm = forms[changedFormIndex].copy(value = value)

        forms.set(index = changedFormIndex, element = newForm)

        _state.update {
            it.copy(forms = forms)
        }
    }

    fun onToastShowed() {
        _state.update {
            it.copy(showSuccessToast = false)
        }
    }
}