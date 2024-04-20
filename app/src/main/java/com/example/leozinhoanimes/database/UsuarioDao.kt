package com.example.leozinhoanimes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    fun insert(usuario: UsuarioEntity)

    @Query("SELECT * FROM Usuario WHERE email = :email AND password = :password")
    fun getUsuario(email: String, password: String): UsuarioEntity
}