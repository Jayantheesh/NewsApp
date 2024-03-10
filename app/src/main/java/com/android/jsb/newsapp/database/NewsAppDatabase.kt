package com.android.jsb.newsapp.database


//@Database(entities = [NewsAPIResultsEntity::class], version = 1)
//abstract class NewsAppDatabase : RoomDatabase() {
//
//    abstract val dao : NewsDBDataDao
//    companion object {
//        private const val DATABASE_NAME = "news_app_database.db"
//
//        @Volatile
//        private var INSTANCE : NewsAppDatabase? = null
//        fun getAppDataBase(context : Context) : NewsAppDatabase {
//            val temp = INSTANCE
//            if (temp != null) {
//                return temp
//            }
//            synchronized(this) {
//                val database = Room.databaseBuilder(context.applicationContext,
//                    NewsAppDatabase::class.java, DATABASE_NAME).build()
//                INSTANCE = database
//                return database
//            }
//        }
//    }
//}