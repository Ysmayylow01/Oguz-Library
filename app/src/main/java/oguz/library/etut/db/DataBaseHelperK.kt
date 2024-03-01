package oguz.library.etut.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHelperK(private val context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME1, null, DATABASE_VERSION
) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val query3 = "CREATE TABLE " + TABLE_NAME1 +
                " (" + THEME_ID + " STRING PRIMARY KEY, " +
                THEME_TITLE3 + " TEXT, " +
                THEME_ITME + " TEXT, " +
                THEME_IMAGE + " TEXT, " +
                THEME_SUBTITLE + " TEXT, " +
                THEME_PDF + " TEXT, " +
                UNIQUE_ID3 + " TEXT);"
        sqLiteDatabase.execSQL(query3)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1)
    }

    fun addScanRecord3(
        unique_id: String?,
        title: String?,
        image_id: String?,
        subtitle: String?,
        pdf: String?
    ): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(THEME_ID, unique_id)
        cv.put(THEME_TITLE3, title)
        cv.put(THEME_IMAGE, image_id)
        cv.put(THEME_SUBTITLE, subtitle)
        cv.put(THEME_PDF, pdf)
        val result = db.insert(TABLE_NAME1, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
        }
        db.close()
        return result
    }

    fun readAllData33(): Cursor? {
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME1
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun deleteItem33(get_ID: String) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " WHERE " + THEME_ID + " ='" + get_ID + "'")
    }

    companion object {
        private const val DATABASE_NAME1 = "FavoriteRecord.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME1 = "my_favorite11"
        private const val THEME_ID = "_id3"
        private const val THEME_TITLE3 = "theme_title"
        private const val THEME_ITME = "theme_time"
        private const val THEME_IMAGE = "theme_image"
        private const val THEME_SUBTITLE = "theme_subtitle"
        private const val THEME_PDF = "theme_pdf"
        private const val UNIQUE_ID3 = "unique_id"
    }
}