package com.patriciafiona.plantyshop.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patriciafiona.plantyshop.ui.theme.*
import java.time.format.TextStyle

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    inputValue: MutableState<String>,
    placeholder: String,
    isSingleLine: Boolean = true,
    keyboardOptions: KeyboardOptions,
){
    BasicTextField(
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        singleLine = isSingleLine,
        keyboardOptions = keyboardOptions,
        decorationBox = {innerTextField ->
            Box {
                if (inputValue.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = androidx.compose.ui.text.TextStyle(
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontFamily = Montserrat
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun TextPasswordInput(
    modifier: Modifier = Modifier,
    inputValue: MutableState<String>,
    title: String,
    placeholder: String,
    isSingleLine: Boolean = true,
    passwordVisible: MutableState<Boolean>
){
    TextField(
        modifier = modifier,
        value = inputValue.value,
        onValueChange = { inputValue.value = it },
        label = {
            Text(text = title)
        },
        singleLine = isSingleLine,
        placeholder = {
            Text(text = placeholder)
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (passwordVisible.value) "Hide password" else "Show password"

            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ){
                Icon(imageVector  = image, description)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            backgroundColor = lightGray03,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}