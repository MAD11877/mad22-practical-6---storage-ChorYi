package sg.edu.np.mad.mad_exercise6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_USERS = "Users";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOLLOWED = "Followed";

    public DBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE "
                + TABLE_USERS
                +"(" + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User ur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, ur.name);
        values.put(COLUMN_DESCRIPTION, ur.description);
        //values.put(COLUMN_ID, ur.id);
        //values.put(COLUMN_FOLLOWED, (ur.followed));
        if (ur.followed){
            values.put(COLUMN_FOLLOWED, 1);
        }
        else{
            values.put(COLUMN_FOLLOWED, 0);
        }
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers(){

        ArrayList<User> urlist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        while(cursor.moveToNext()){
            User ur = new User();
            ur.name = cursor.getString(0);
            ur.description = cursor.getString(1);
            ur.id = cursor.getInt(2);
            if (cursor.getInt(3)==1){
                ur.followed = true;
            }
            else{
                ur.followed = false;
            }
            urlist.add(ur);
        }
        cursor.close();
        db.close();
        return urlist;
    }

    public void updateUser(User ur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLLOWED, ((ur.followed)?1:0));
        db.update(TABLE_USERS, values, "id=?", new String[]{String.valueOf(ur.id)});
        db.close();
    }
}
