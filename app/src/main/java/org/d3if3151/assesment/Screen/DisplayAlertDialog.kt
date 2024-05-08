package org.d3if3151.assesment.Screen

import android.app.AlertDialog
import android.content.res.Configuration
import android.view.Display
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.d3if3151.assesment.R
import java.net.CacheRequest

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
){
    if (openDialog){
        AlertDialog(
            text = { Text(text = stringResource(R.string.pesan_hapus))},
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(text = stringResource(R.string.tombol_hapus))
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissRequest() }) {
                    Text(text = stringResource(R.string.tombol_batal))
                }
            },
            onDismissRequest = { onDismissRequest() },
        )
    }
}