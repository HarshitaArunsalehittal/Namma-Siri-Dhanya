package com.example.nammasiridhanya.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammasiridhanya.R
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    val favoriteRecipes = remember {
        mutableStateListOf<String>()
    }

    val auth = FirebaseAuth.getInstance()

    val db = FirebaseFirestore.getInstance()

    var userName by remember {
        mutableStateOf("")
    }

    var userPhone by remember {
        mutableStateOf("")
    }

    var district by remember {
        mutableStateOf("")
    }

    var millet by remember {
        mutableStateOf("")
    }

    var quantity by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        if (auth.currentUser != null) {

            db.collection("users")
                .document(auth.currentUser!!.uid)
                .get()
                .addOnSuccessListener {

                    userName =
                        it.getString("name") ?: ""

                    userPhone =
                        it.getString("phone") ?: ""
                }
        }
    }

    var selectedScreen by remember {
        mutableStateOf("mandi")
    }

    var showProfile by remember {
        mutableStateOf(false)
    }

    var showFavorites by remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(

        drawerState = drawerState,

        drawerContent = {

            ModalDrawerSheet {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Namma Siri Dhanya",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = {
                        Text(stringResource(R.string.profile))
                    },
                    selected = false,
                    onClick = {

                        showProfile = true

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(stringResource(R.string.notifications))
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(stringResource(R.string.saved_recipes))
                    },
                    selected = false,
                    onClick = {

                        showFavorites = true

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text(stringResource(R.string.settings))
                    },
                    selected = false,
                    onClick = {

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Logout")
                    },

                    selected = false,

                    onClick = {

                        FirebaseAuth.getInstance().signOut()

                        navController.navigate("login") {

                            popUpTo("home") {
                                inclusive = true
                            }
                        }

                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }

    ) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {
                        Text(stringResource(R.string.app_name))
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {

                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {

                            Icon(
                                Icons.Default.Menu,
                                contentDescription = null
                            )
                        }
                    },

                    actions = {

                        IconButton(
                            onClick = { }
                        ) {

                            Icon(
                                Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }
                )
            },

            bottomBar = {

                NavigationBar {

                    NavigationBarItem(
                        selected = selectedScreen == "mandi",
                        onClick = {
                            selectedScreen = "mandi"
                        },
                        icon = {
                            Icon(Icons.Default.ShowChart, null)
                        },
                        label = {
                            Text(stringResource(R.string.mandi))
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "recipes",
                        onClick = {
                            selectedScreen = "recipes"
                        },
                        icon = {
                            Icon(Icons.Default.Restaurant, null)
                        },
                        label = {
                            Text(stringResource(R.string.recipes))
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "health",
                        onClick = {
                            selectedScreen = "health"
                        },
                        icon = {
                            Icon(Icons.Default.Favorite, null)
                        },
                        label = {
                            Text(stringResource(R.string.health))
                        }
                    )

                    NavigationBarItem(
                        selected = selectedScreen == "buyer",
                        onClick = {
                            selectedScreen = "buyer"
                        },
                        icon = {
                            Icon(Icons.Default.ShoppingCart, null)
                        },
                        label = {
                            Text(stringResource(R.string.buyer))
                        }
                    )
                }
            }

        ) { innerPadding ->

            Surface(
                modifier = Modifier.padding(innerPadding)
            ) {

                when (selectedScreen) {

                    "mandi" -> {
                        MarketScreen()//navController)
                    }

                    "recipes" -> {
                        RecipeScreen(favoriteRecipes)
                    }

                    "health" -> {
                        HealthScreen()
                    }

                    "buyer" -> {
                        BuyScreen()
                    }
                }
            }
        }

        if (showProfile) {

            AlertDialog(

                onDismissRequest = {
                    showProfile = false
                },

                confirmButton = {

                    Button(
                        onClick = {

                            db.collection("farmers")
                                .document(auth.currentUser!!.uid)
                                .set(

                                    hashMapOf(

                                        "name" to userName,

                                        "phone" to userPhone,

                                        "district" to district,

                                        "millet" to millet,

                                        "quantity" to quantity
                                    )
                                )

                            showProfile = false
                        }
                    ) {

                        Text("Save")
                    }
                },

                dismissButton = {

                    Button(
                        onClick = {
                            showProfile = false
                        }
                    ) {

                        Text("Cancel")
                    }
                },

                title = {
                    Text("Profile")
                },

                text = {

                    Column {

                        OutlinedTextField(
                            value = userName,
                            onValueChange = {
                                userName = it
                            },
                            label = {
                                Text("Name")
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = userPhone,
                            onValueChange = {
                                userPhone = it
                            },
                            label = {
                                Text("Phone")
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = district,
                            onValueChange = {
                                district = it
                            },
                            label = {
                                Text("District")
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = millet,
                            onValueChange = {
                                millet = it
                            },
                            label = {
                                Text("Millets Produced")
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = quantity,
                            onValueChange = {
                                quantity = it
                            },
                            label = {
                                Text("Quantity Available")
                            }
                        )
                    }
                }
            )
        }

        if (showFavorites) {

            AlertDialog(

                onDismissRequest = {
                    showFavorites = false
                },

                confirmButton = {

                    Button(
                        onClick = {
                            showFavorites = false
                        }
                    ) {

                        Text("Close")
                    }
                },

                title = {
                    Text("Saved Recipes")
                },

                text = {

                    Column {

                        favoriteRecipes.forEach {

                            Text("❤️ $it")

                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        if (favoriteRecipes.isEmpty()) {

                            Text("No saved recipes")
                        }
                    }
                }
            )
        }
    }
}