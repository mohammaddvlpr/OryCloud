package com.example.orycloud.ui.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController


@Composable
fun SignUpScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    if (state.showSuccessToast) {
        Toast.makeText(context, "register success", Toast.LENGTH_LONG).show()
        viewModel.onToastShowed()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        state.forms.forEach {
            Form(modifier = Modifier.fillMaxWidth(), form = it) {value->
                viewModel.onValueChange(name = it.name , value = value)
            }
        }


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = { viewModel.onRegisterClick() }) {
            Text(text = "Register")
        }
    }

}

@Composable
fun Form(modifier: Modifier = Modifier, form: Form, onValueChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = form.value,
        onValueChange = onValueChange,
        label = { Text(text = form.title) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = findKeyboardType(form.formType)),

        )
}


fun findKeyboardType(formType: FormType): KeyboardType {
    return when (formType) {
        FormType.NUMBER -> KeyboardType.Number
        FormType.TEXT -> KeyboardType.Text
    }
}
