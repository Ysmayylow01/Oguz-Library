package oguz.library.etut.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME1 = "FavoriteRecord.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME1 = "my_favorite11";
    private static final String THEME_ID = "_id3";
    private static final String THEME_TITLE3 = "theme_title";
    private static final String THEME_ITME = "theme_time";
    private static final String THEME_IMAGE = "theme_image";
    private static final String THEME_SUBTITLE = "theme_subtitle";
    private static final String UNIQUE_ID3 = "unique_id";

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME1, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query3 = "CREATE TABLE " + TABLE_NAME1 +
                " (" + THEME_ID + " STRING PRIMARY KEY, " +
                THEME_TITLE3 + " TEXT, " +
                THEME_ITME + " TEXT, " +
                THEME_IMAGE + " TEXT, " +
                THEME_SUBTITLE + " TEXT, " +
                UNIQUE_ID3 + " TEXT);";
        sqLiteDatabase.execSQL(query3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
    }

    public long addScanRecord3(String unique_id, String title, String image_id, String subtitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(THEME_ID, unique_id);
        cv.put(THEME_TITLE3, title);
        cv.put(THEME_IMAGE, image_id);
        cv.put(THEME_SUBTITLE, subtitle);
        long result = db.insert(TABLE_NAME1, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {

        }
        db.close();
        return result;
    }

    public Cursor readAllData3() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME1;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

    public void deleteItem3(String get_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME1 + " WHERE " + THEME_ID + " ='" + get_ID + "'");

    }
}
