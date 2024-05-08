package org.d3if3151.assesment.Screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3151.assesment.model.Absen
import org.d3if3151.assesment.navigation.Screen
import org.d3if3151.assesment.R
import org.d3if3151.assesment.database.AbsenDb
import org.d3if3151.assesment.util.SettingsDataStore
import org.d3if3151.assesment.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    val dataStore = SettingsDataStore(LocalContext.current)
    val showList by dataStore.layoutFlow.collectAsState(true)
    val context = LocalContext.current
    val db = AbsenDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()
    var searchText by remember { mutableStateOf("")}
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    Row {
                        IconButton(onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                dataStore.saveLayout(!showList)
                            }
                        }) {
                            Icon(
                                painter = painterResource(
                                    if (showList) R.drawable.baseline_grid_view_24
                                    else R.drawable.baseline_list_24
                                ),
                                contentDescription = stringResource(
                                    if (showList) R.string.grid
                                    else R.string.list
                                ),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        IconButton(onClick = {
                            navController.navigate(Screen.About.route)
                        }) {
                            Icon(imageVector = Icons.Outlined.Info, contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.FormBaru.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_catatan),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
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
                value = searchText,
                onValueChange = { searchText = it },
                                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                label = { Text(text = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
//            OutlinedTextField(
//                value = nama,
//                onValueChange = {
//                    nama = it
//                    namaError = false
//                    isError = false
//                },
//                supportingText = { ErrorHint(namaError) },
//
//                label = { Text(text = stringResource(id = R.string.nama)) },
//                singleLine = true,
//                isError = isError,
//                trailingIcon = { Icon(imageVector = Icons.Outlined.Person, contentDescription = stringResource(
//                    id = R.string.nama
//                ))},
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Text,
//                    imeAction = ImeAction.Next
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//            )
//            OutlinedTextField(
//                value = nim,
//                onValueChange = {
//                    nim = it
//                    nimError = false
//                    isError = false
//                },
//                supportingText = { ErrorHint(nimError) },
//                label = { Text(text = stringResource(id = R.string.NIM)) },
//                singleLine = true,
//                isError = isError,
//                trailingIcon = { Icon(imageVector = Icons.Outlined.Call, contentDescription = stringResource(
//                    id = R.string.NIM
//                ))},
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Number,
//                    imeAction = ImeAction.Done
//                ),
//                modifier = Modifier
//                    .padding(bottom = 10.dp)
//                    .fillMaxWidth()
//            )
//            Row(
//                modifier = Modifier
//                    .padding(top = 6.dp, bottom = 10.dp)
//                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
//            ) {
//                radioOptions.forEach{ text -> GenderOption(
//                    label = text,
//                    isSelected = status == text,
//                    modifier = Modifier
//                        .selectable(
//                            selected = status == text,
//                            onClick = { status = text },
//                            role = Role.RadioButton
//                        )
//                        .weight(1f)
//                        .padding(15.dp)
//                )
//                }
//            }
//            Button(
//                onClick = {
//                    if (nama.isNotEmpty() && nim.isNotEmpty() && status.isNotEmpty()) {
//                        viewModel.addAbsen(nama, nim, status)
//                        nama = ""
//                        nim = ""
//                    } else {
//                        isError = true
//                        namaError = true
//                        nimError = true
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(text = stringResource(id = R.string.absen))
//            }
            val filteredData = if (searchText.isEmpty()) {
                data // Show all data if search query is empty
            } else {
                data.filter { absen ->
                    absen.nama.contains(searchText, ignoreCase = true) || absen.NIM.contains(
                        searchText,
                        ignoreCase = true
                    ) || absen.status.contains(searchText, ignoreCase = true)
                }
            }

            if (filteredData.isEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(R.drawable.da), contentDescription = ""
                    )
                    Text(text = stringResource(id = R.string.list_kosong))
                }
            } else {
                if (showList) {
                    LazyColumn(
                        contentPadding = PaddingValues(bottom = 84.dp)

                    ) {
                        items(filteredData) {
                            DataAbsen(absen = it) {
                                navController.navigate(Screen.FormUbah.withId(it.id))
                            }
                        }
                    }
                } else {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = StaggeredGridCells.Fixed(2),
                        verticalItemSpacing = 8.dp,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(8.dp,8.dp,8.dp,84.dp)
                    ){
                        items(filteredData) {
                            GridItem(absen = it) {
                                navController.navigate(Screen.FormUbah.withId(it.id))
                            }
                        }
                    }
                }
            }
        }
    }
}
//List Item


@Composable
fun DataAbsen(absen: Absen, onClick: () -> Unit){
    var showDialog by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .shadow(elevation = 10.dp, spotColor = Color.Transparent)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
            text = absen.nama,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
            Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = absen.NIM,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = 10.dp)
        )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = when (absen.status) {
                    stringResource(id = R.string.masuk) -> stringResource(id = R.string.masuk)
                    stringResource(id = R.string.izin) -> stringResource(id = R.string.izin)
                    stringResource(id = R.string.alpha) -> stringResource(id = R.string.alpha)
                    else -> absen.status
                },
                modifier = Modifier.padding(start = 10.dp, bottom = 0.dp)
            )
            Text(
                text = absen.time,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 10.dp),
                fontWeight = FontWeight.Bold,

            )
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            IconButton(onClick = {  }) {
                Icon(Icons.Default.Share, contentDescription = stringResource(id = R.string.izin))
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.masuk))
            }
        }
    }

//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 18.dp)
//            .background(MaterialTheme.colorScheme.primaryContainer)
//            .shadow(elevation = 10.dp, spotColor = Color.Transparent)
//            .clickable { onClick() },
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        Text(
//            text = absen.nama,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
//        )
//        Text(
//            text = absen.NIM,
//            maxLines = 2,
//            overflow = TextOverflow.Ellipsis,
//            modifier = Modifier.padding(start = 10.dp)
//        )
//        Row (
//            verticalAlignment = Alignment.CenterVertically
//        ){
//            Text(
//                text = when (absen.status) {
//                    stringResource(id = R.string.masuk) -> stringResource(id = R.string.masuk)
//                    stringResource(id = R.string.izin) -> stringResource(id = R.string.izin)
//                    stringResource(id = R.string.alpha) -> stringResource(id = R.string.alpha)
//                    else -> absen.status
//                },
//                modifier = Modifier.padding(start = 10.dp, bottom = 0.dp)
//            )
//            Button(
//                onClick = {
//                    shareData(
//                        context = context,
//                        message = context.getString(R.string.bagikan_template,
//                            absen.nama, absen.NIM, absen.status)
//                    )
//                },
//                modifier = Modifier
//                    .padding(start = 0.dp)
//                    .scale(0.8f)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(10.dp)
//                ) {
//                    Icon(imageVector = Icons.Filled.Share, contentDescription = "", Modifier.scale(1f))
//                    Text(text = stringResource(id = R.string.bagikan), fontSize = 12.sp)
//                }
//            }
//
//        }
//    }
}

@Composable
fun GridItem(absen: Absen, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = absen.nama,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = absen.NIM,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = absen.status)
            Text(
                text = absen.time,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                )
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
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}



