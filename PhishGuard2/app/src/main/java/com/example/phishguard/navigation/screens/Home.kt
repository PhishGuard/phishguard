package com.example.phishguard.navigation.screens


import android.graphics.Bitmap
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.phishguard.MainViewModel
import com.example.phishguard.R
import com.example.phishguard.RoomDatabase
import com.example.phishguard.ScreenshotRepository
import com.example.phishguard.navigation.BottomNavigationBar
import com.example.phishguard.navigation.NavRoutes
import com.example.phishguard.navigation.TopBar
import com.example.phishguard.network.makeAPICall


@Composable
fun Home(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    var searchQuery by remember { mutableStateOf("") }
    val imageBitmapState = remember { mutableStateOf<ImageBitmap?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val onSearchQueryChange = { text: String ->
        searchQuery = text
    }


    Scaffold(
        topBar = {
            TopBar(
                title = "Home",
                buttonIcon = Icons.Filled.Home,
                onButtonClicked = { }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
            ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Row(horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)){
                    CustomTextField(
                        title = "Website Link",
                        textState = searchQuery,
                        onTextChange = onSearchQueryChange,
                        keyboardType = KeyboardType.Text
                    )
                }


                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Button(onClick = {
                        if (searchQuery.isNotEmpty()) {
                            isLoading.value = true
                            makeAPICall(
                                "http://172.28.16.71:5000/newScreenshot",
                                searchQuery
                            ) { bitmap ->
                                isLoading.value = false
                                imageBitmapState.value = bitmap.asImageBitmap()
                            }
                        }
                    }) {
                        Text("Add Phishing Page")
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    if (isLoading.value) {
                        Text("Loading...")
                    } else {
                        imageBitmapState.value?.let { imageBitmap ->
                            Image(
                                bitmap = imageBitmap,
                                contentDescription = "Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Fit
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(64.dp))
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
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
    )
}




