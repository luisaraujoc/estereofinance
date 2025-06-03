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
import com.coutinho.estereofinance.data.utils.SessionManager
import com.coutinho.estereofinance.ui.theme.*
import com.coutinho.estereofinance.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                text = stringResource(R.string.login_title),
                style = MaterialTheme.typography.displaySmall.copy(
                    color = Pumpkin,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 42.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        text = stringResource(R.string.login_email_label),
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
                        text = stringResource(R.string.login_password_label),
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

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        userViewModel.login(email, password) { credentials ->
                            scope.launch {
                                if (credentials != null) {
                                    SessionManager(context).saveUserId(credentials.id)
                                    snackbarHostState.showSnackbar(context.getString(R.string.login_success))
                                    onLoginSuccess() // <-- Função callback passada pela navegação
                                } else {
                                    snackbarHostState.showSnackbar(context.getString(R.string.login_invalid))
                                }
                            }
                        }

                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Pumpkin),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.login_button_text),
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
                    text = stringResource(R.string.login_dont_have_account),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = onSignUpClick) {
                    Text(
                        text = stringResource(R.string.login_signup),
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
