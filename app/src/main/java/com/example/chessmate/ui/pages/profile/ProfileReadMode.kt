package com.example.chessmate.ui.pages.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.chessmate.sign_in.AuthUIClient
import com.example.chessmate.sign_in.SignInViewModel
import com.example.chessmate.sign_in.UserData
import com.example.chessmate.ui.utils.ChessMateNavigationType
import kotlinx.coroutines.launch

@Composable
fun ProfileReadMode(
    userData: UserData?,
    authHandler: AuthUIClient? = null,
    modifier: Modifier = Modifier,
    navigationType: ChessMateNavigationType,
    authViewModel: SignInViewModel? = null,
    toggler: () -> Unit
) {
    val lyfescope = rememberCoroutineScope()
    val scroll = rememberScrollState(0)
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (userData?.profilePictureUrl != null) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            } else {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Default Profile picture",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            if (userData?.username != null) {
                Text(
                    text = userData.username,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(
                onClick = toggler,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    modifier = Modifier.size(16.dp),
                    contentDescription = "Edit icon"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Edit")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            lyfescope.launch {
                val signInResult = authHandler?.signOut()
                authViewModel?.onSignInResult(
                    signInResult!!
                )
            }
        }) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                modifier = Modifier.size(16.dp),
                contentDescription = "Exit icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Sign Out")
        }
    }
}

@Preview
@Composable
fun ProfileReadModePreview() {
    ProfileReadMode(
        userData = UserData(
            userId = "1",
            profilePictureUrl = null,
            username = "Andrea",
            email = "andrea.pancio00@gmail.com",
            emailVerified = false,
            provider = null
        ),
        modifier = Modifier,
        authHandler = null,
        navigationType = ChessMateNavigationType.BOTTOM_NAVIGATION,
        toggler = {  }
    )
}

@Preview
@Composable
fun ProfileReadModeTabletPreview() {
    ProfileReadMode(
        userData = UserData(
            userId = "1",
            profilePictureUrl = null,
            username = "Andrea",
            email = "andrea.pancio00@gmail.com",
            emailVerified = false,
            provider = null
        ),
        modifier = Modifier,
        authHandler = null,
        navigationType = ChessMateNavigationType.NAVIGATION_RAIL,
        toggler = { }
    )
}