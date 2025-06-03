package com.coutinho.estereofinance.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coutinho.estereofinance.R
import com.coutinho.estereofinance.data.entity.User
import com.coutinho.estereofinance.data.utils.SessionManager
import com.coutinho.estereofinance.data.utils.PasswordRequirements
import com.coutinho.estereofinance.data.utils.validatePassword
import com.coutinho.estereofinance.ui.theme.*
import com.coutinho.estereofinance.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun RequirementItem(text: String, satisfied: Boolean) {
    val color = if (satisfied) Color(0xFF4CAF50) else Vermilion
    val icon = if (satisfied) "✔" else "✖"

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = icon,
            color = color,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            color = color
        )
    }
}

@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val requirements = validatePassword(password)

    /******/
    val messageReqsError = context.getString(R.string.register_password_requeriments)
    val messageSucc = context.getString(R.string.register_successfully)
    val messageFail = context.getString(R.string.register_failed)


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.register_title),
                style = MaterialTheme.typography.displaySmall.copy(
                    color = Pumpkin,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 42.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it.trim() },
                label = {
                    Text(
                        stringResource(R.string.register_name_label),
                        color = FrenchGray
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Xanthous),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Xanthous,
                    unfocusedTextColor = Xanthous,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Pumpkin,
                    focusedIndicatorColor = Pumpkin,
                    unfocusedIndicatorColor = FrenchGray,
                    focusedLabelColor = Xanthous,
                    unfocusedLabelColor = FrenchGray,
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        stringResource(R.string.register_email_label),
                        color = FrenchGray
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Xanthous),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Xanthous,
                    unfocusedTextColor = Xanthous,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Pumpkin,
                    focusedIndicatorColor = Pumpkin,
                    unfocusedIndicatorColor = FrenchGray,
                    focusedLabelColor = Xanthous,
                    unfocusedLabelColor = FrenchGray,
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        stringResource(R.string.login_password_label),
                        color = FrenchGray
                    )
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Xanthous),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Xanthous,
                    unfocusedTextColor = Xanthous,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Pumpkin,
                    focusedIndicatorColor = Pumpkin,
                    unfocusedIndicatorColor = FrenchGray,
                    focusedLabelColor = Xanthous,
                    unfocusedLabelColor = FrenchGray,
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            RequirementItem("At least 8 characters", requirements.length)
            RequirementItem("One uppercase letter", requirements.hasUpper)
            RequirementItem("One lowercase letter", requirements.hasLower)
            RequirementItem("One number", requirements.hasDigit)
            RequirementItem("One special character", requirements.hasSpecial)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        val valid = with(requirements) {
                            length && hasUpper && hasLower && hasDigit && hasSpecial
                        }
                        if (!valid) {

                            snackbarHostState.showSnackbar(messageReqsError)
                            return@launch
                        }
                        val user = User(name = name, email = email)
                        userViewModel.register(
                            user = user,
                            password = password,
                            onSuccess = {
                                scope.launch {
                                    SessionManager(context).saveUserId(it)
                                    snackbarHostState.showSnackbar(messageSucc)
                                }
                            },
                            onError = {
                                scope.launch {
                                    snackbarHostState.showSnackbar(messageFail)
                                }
                            }
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Pumpkin),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.register_button_text),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.align(Alignment.Start),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.register_already_have_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onLoginClick) {
                    Text(
                        text = stringResource(R.string.login_title),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
