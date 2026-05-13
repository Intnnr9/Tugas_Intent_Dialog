package com.nim2430511066.restrasiseminasrcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormRegistrasiScreen(
                        onSubmit = { nama, nim, prodi, email, telepon ->
                            val intent = Intent(this, HasilActivity::class.java).apply {
                                putExtra("NAMA", nama)
                                putExtra("NIM", nim)
                                putExtra("PRODI", prodi)
                                putExtra("EMAIL", email)
                                putExtra("TELEPON", telepon)
                            }
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FormRegistrasiScreen(
    onSubmit: (String, String, String, String, String) -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var prodi by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telepon by remember { mutableStateOf("") }

    var showWarningDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Form Registrasi Seminar Mahasiswa", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = nama, onValueChange = { nama = it }, label = { Text("Nama Mahasiswa") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = nim, onValueChange = { nim = it }, label = { Text("NIM") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = prodi, onValueChange = { prodi = it }, label = { Text("Program Studi") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telepon, onValueChange = { telepon = it }, label = { Text("Nomor Telepon") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nama.isBlank() || nim.isBlank() || prodi.isBlank() || email.isBlank() || telepon.isBlank()) {
                    showWarningDialog = true
                } else {
                    showConfirmDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Daftar Seminar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { showResetDialog = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
        ) {
            Text("Reset Form")
        }
    }

    if (showWarningDialog) {
        AlertDialog(
            onDismissRequest = { showWarningDialog = false },
            title = { Text("Peringatan") },
            text = { Text("Semua data termasuk Nomor Telepon harus diisi!") },
            confirmButton = { TextButton(onClick = { showWarningDialog = false }) { Text("OK") } }
        )
    }

    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Konfirmasi") },
            text = { Text("Apakah data registrasi seminar akan dikirim?") },
            confirmButton = {
                TextButton(onClick = {
                    showConfirmDialog = false
                    onSubmit(nama, nim, prodi, email, telepon)
                }) { Text("Ya") }
            },
            dismissButton = { TextButton(onClick = { showConfirmDialog = false }) { Text("Batal") } }
        )
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Hapus Data") },
            text = { Text("Apakah Anda yakin ingin menghapus semua inputan?") },
            confirmButton = {
                TextButton(onClick = {
                    nama = ""; nim = ""; prodi = ""; email = ""; telepon = ""
                    showResetDialog = false
                }) { Text("Ya, Reset") }
            },
            dismissButton = { TextButton(onClick = { showResetDialog = false }) { Text("Batal") } }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFormRegistrasiScreen() {
    MaterialTheme {
        FormRegistrasiScreen(onSubmit = { _, _, _, _, _ -> })
    }
}