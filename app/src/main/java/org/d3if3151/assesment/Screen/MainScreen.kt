package org.d3if3151.assesment.Screen

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.d3if3151.assesment.model.Absen
import org.d3if3151.assesment.navigation.Screen
import org.d3if3151.assesment.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    var nama by rememberSaveable {
        mutableStateOf("")
    }
    var namaError by rememberSaveable {
        mutableStateOf(false)
    }
    var isError by rememberSaveable {
        mutableStateOf(false)
    }
    var nim by rememberSaveable {
        mutableStateOf("")
    }
    var nimError by rememberSaveable {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val radioOptions = listOf(
        stringResource(id = R.string.masuk),
        stringResource(id = R.string.izin),
        stringResource(id = R.string.alpha)
    )
    var status by rememberSaveable {
        mutableStateOf(radioOptions[0])
    }
    val viewModel: MainViewModel = viewModel()
    val absen = viewModel.data
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(imageVector = Icons.Outlined.Info, contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.halaman_absen))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = {
                    nama = it
                    namaError = false
                    isError = false
                },
                supportingText = { ErrorHint(namaError) },

                label = { Text(text = stringResource(id = R.string.nama)) },
                singleLine = true,
                isError = isError,
                trailingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = stringResource(
                    id = R.string.nama
                ))},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = nim,
                onValueChange = {
                    nim = it
                    nimError = false
                    isError = false
                },
                supportingText = { ErrorHint(nimError) },
                label = { Text(text = stringResource(id = R.string.NIM)) },
                singleLine = true,
                isError = isError,
                trailingIcon = { Icon(imageVector = Icons.Outlined.Call, contentDescription = stringResource(
                    id = R.string.NIM
                ))},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 10.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            ) {
                radioOptions.forEach{ text -> GenderOption(
                    label = text,
                    isSelected = status == text,
                    modifier = Modifier
                        .selectable(
                            selected = status == text,
                            onClick = { status = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(15.dp)
                )
                }
            }
            Button(
                onClick = {
                    if (nama.isNotEmpty() && nim.isNotEmpty() && status.isNotEmpty()) {
                        viewModel.addAbsen(nama, nim, status)
                        nama = ""
                        nim = ""
                    } else {
                        isError = true
                        namaError = true
                        nimError = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.absen))
            }
            LazyColumn(
                contentPadding = PaddingValues(bottom = 84.dp)
            ) {
                items(absen){
                    DataAbsen(absen = it){
                        val pesan = context.getString(R.string.diklik, it.nama, it.status)
                        Toast.makeText(context, pesan, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun DataAbsen(absen: Absen, onClick: () -> Unit){
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .shadow(elevation = 10.dp, spotColor = Color.Transparent)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = absen.nama,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
        Text(
            text = absen.NIM,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 10.dp)
        )
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = when (absen.status) {
                    stringResource(id = R.string.masuk) -> stringResource(id = R.string.masuk)
                    stringResource(id = R.string.izin) -> stringResource(id = R.string.izin)
                    stringResource(id = R.string.alpha) -> stringResource(id = R.string.alpha)
                    else -> absen.status
                },
                modifier = Modifier.padding(start = 10.dp, bottom = 0.dp)
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template,
                            absen.nama, absen.NIM, absen.status)
                    )
                },
                modifier = Modifier
                    .padding(start = 0.dp)
                    .scale(0.8f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "", Modifier.scale(1f))
                    Text(text = stringResource(id = R.string.bagikan), fontSize = 12.sp)
                }
            }

        }
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Composable
fun GenderOption(label: String, isSelected: Boolean, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}

@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}



