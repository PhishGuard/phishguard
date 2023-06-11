package com.example.phishguard.navigation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.phishguard.MainViewModel
import com.example.phishguard.navigation.NavRoutes



@Composable
fun Login(
    navController: NavHostController,
    viewModel: MainViewModel
){
    var enteredUsername by remember { mutableStateOf("") }
    var enteredPassword by remember { mutableStateOf("") }

    val onUsernameChange = { text : String ->
        enteredUsername = text
    }

    val onPasswordChange = { text : String ->
        enteredPassword = text
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        CustomTextField(
            title = "Username",
            textState = enteredUsername,
            onTextChange = onUsernameChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Password",
            textState = enteredPassword,
            onTextChange = onPasswordChange,
            keyboardType = KeyboardType.Password
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                if (enteredUsername.isNotEmpty() && enteredPassword.isNotEmpty()) {
                    viewModel.sendLogin(username = enteredUsername, password = enteredPassword)
                    navController.navigate(NavRoutes.Home.route)
                }
            }) {
                Text("Login")
            }
        }
    }
}



@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}

