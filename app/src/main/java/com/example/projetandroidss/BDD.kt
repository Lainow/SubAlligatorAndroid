package com.example.projetandroidss

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projetandroidss.dao.*
import com.example.projetandroidss.entities.*
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

@Database(
    entities = [Aptitude::class, ContainSkill::class, Content::class, Level::class, Skill::class, Formation::class,
               Initiator::class, Session::class, Participation::class, Status::class, Student::class, TrainingManager::class],
    version = 6
)

abstract class BDD : RoomDatabase() {

    companion object {
        private var instance: BDD? = null
        private val MIGRATION_1_6 = object : Migration(1, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        fun getInstance(context: Context): BDD {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    BDD::class.java,
                    "my-database"
                ).addMigrations(MIGRATION_1_6).build()
            }
            return instance as BDD
        }
    }
    fun onCreate(db: SupportSQLiteDatabase) {
        Log.d("Room","Création de la base")
        Thread {
            with(instance!!) {
            }
        }.start()
    }
    fun onOpen(db: SupportSQLiteDatabase) {
        Log.d("Room","Ouverture de la base")
    }
    fun onDestructiveMigration(db: SupportSQLiteDatabase) {
        Log.e("Room", "Migration destructive")
    }
    fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS level")
        onCreate(db as SupportSQLiteDatabase)
    }
    abstract fun aptitudeDao(): AptitudeDao

    abstract fun containSkillDao(): ContainSkillDao

    abstract fun contentDao(): ContentDao

    abstract fun levelDao(): LevelDao

    abstract fun skillDao(): SkillDao

    abstract fun formationDao(): FormationDao

    abstract fun initiatorDao(): InitiatorDao

    abstract fun sessionDao(): SessionDao

    abstract fun participationDao(): ParticipationDao

    abstract fun statusDao(): StatusDao

    abstract fun studentDao(): StudentDao

    abstract fun trainingManagerDao(): TrainingManagerDao



}