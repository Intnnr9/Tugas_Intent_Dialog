package com.nim2430511066.restrasiseminasrcompose

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class HasilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nama = intent.getStringExtra("NAMA") ?: ""
        val nim = intent.getStringExtra("NIM") ?: ""
        val prodi = intent.getStringExtra("PRODI") ?: ""
        val email = intent.getStringExtra("EMAIL") ?: ""
        val telepon = intent.getStringExtra("TELEPON") ?: ""

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HasilRegistrasiScreen(
                        nama = nama,
                        nim = nim,
                        prodi = prodi,
                        email = email,
                        telepon = telepon,
                        onOpenWebsite = {
                            val browserIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://lms.ummi.ac.id/")
                            )
                            startActivity(browserIntent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HasilRegistrasiScreen(
    nama: String,
    nim: String,
    prodi: String,
    email: String,
    telepon: String,
    onOpenWebsite: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Hasil Registrasi Seminar", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Nama         : $nama")
                Text(text = "NIM          : $nim")
                Text(text = "Prodi        : $prodi")
                Text(text = "Email        : $email")
                Text(text = "No. Telepon  : $telepon")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onOpenWebsite, modifier = Modifier.fillMaxWidth()) {
            Text("Buka Website Prodi")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHasilRegistrasiScreen() {
    MaterialTheme {
        HasilRegistrasiScreen(
            nama = "Nama Contoh",
            nim = "123456",
            prodi = "Teknik Informatika",
            email = "contoh@mail.com",
            telepon = "0812345678", // Ini harus ada agar tidak error
            onOpenWebsite = {}
        )
    }
}