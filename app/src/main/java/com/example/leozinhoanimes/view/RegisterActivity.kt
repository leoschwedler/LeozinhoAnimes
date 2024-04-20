package com.example.leozinhoanimes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leozinhoanimes.database.UsuarioDao
import com.example.leozinhoanimes.database.UsuarioDataBase
import com.example.leozinhoanimes.database.UsuarioEntity
import com.example.leozinhoanimes.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        usuarioDao = UsuarioDataBase.getDatabase(this).usuarioDao()


        binding.btnRegister.setOnClickListener {
            val email = binding.editTexEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                val usuario = UsuarioEntity(email = email, password = password)

                try {
                    usuarioDao.insert(usuario)
                    Toast.makeText(this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT)
                        .show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show()
                    // Trate o erro aqui
                }
            } else {
                // Lidar com campos vazios
            }
        }
    }
}