package com.example.projetandroidss

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projetandroidss.entities.*

@Database(
    entities = [Aptitude::class, ContainSkill::class, Content::class, Level::class, Skill::class, Formation::class,
               Initiator::class, Session::class, Participation::class, Status::class, Student::class, TrainingManager::class],
    version = 1
)

abstract class BDD : RoomDatabase() {
    //...
    companion object {
        private var instance: BDD? = null
        fun getInstance(context: Context): BDD {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context,
                    BDD::class.java, "modules.sqlite"
                ).build()
            return instance!!
        }
    }
}