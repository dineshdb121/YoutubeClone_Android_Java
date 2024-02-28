package devkng.com.blogbite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes13.dex */
public class DBHelper extends SQLiteOpenHelper {
    private static final String dbname = "accounts";

    public DBHelper(Context context) {
        super(context, dbname, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table accounts_list ( id integer primary key autoincrement, username String , pass String)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS accounts_list");
        onCreate(sqLiteDatabase);
    }

    public String add_data(String username, String pass) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("pass", pass);
        float result = (float) db.insert("accounts_list", null, cv);
        if (result == -1.0f) {
            return "Failed";
        }
        return "Successfully inserted";
    }

    public Cursor read_data() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from accounts_list order by id desc", null);
        return cursor;
    }
}