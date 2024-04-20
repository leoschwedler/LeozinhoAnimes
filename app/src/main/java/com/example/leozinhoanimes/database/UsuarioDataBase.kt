package com.example.leozinhoanimes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class UsuarioDataBase() : RoomDatabase() {


    abstract fun usuarioDao(): UsuarioDao

    companion object {
        private lateinit var INSTANCE: UsuarioDataBase

        fun getDatabase(context: Context): UsuarioDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(UsuarioDataBase::class) {
                    INSTANCE =
                        Room.databaseBuilder(context, UsuarioDataBase::class.java, "usuario_db")
                            .addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
        private val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                TODO("Not yet implemented")
            }
        }
    }
}