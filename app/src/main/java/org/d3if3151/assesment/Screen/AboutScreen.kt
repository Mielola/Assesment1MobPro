package org.d3if3151.assesment.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3151.assesment.R
import org.d3if3151.assesment.model.Profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Halaman About")},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
            )
        },

    ) {
        paddingValues ->  Column(
            modifier = Modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        FotoProfile(profile = Profile(R.drawable.foto))
        Text(text = stringResource(id = R.string.profile),
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center
        )
    }

    }
}

@Composable
fun FotoProfile(profile: Profile){
    Image(
        painter = painterResource(id = profile.imageResId),
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)
            .clip(shape = CircleShape),
        contentScale = ContentScale.Crop
    )
}