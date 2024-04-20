package com.example.leozinhoanimes.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leozinhoanimes.database.UsuarioDataBase
import com.example.leozinhoanimes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val database = UsuarioDataBase.getDatabase(this)
        val usuarioDao = database.usuarioDao()

        binding.btnLogin.setOnClickListener {
            val email = binding.editTexEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            val usuario = usuarioDao.getUsuario(email, password)
            if (usuario != null) {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // Usuário não encontrado, exibir mensagem de erro
                Toast.makeText(this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

