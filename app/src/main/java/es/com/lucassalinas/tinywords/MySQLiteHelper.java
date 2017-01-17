package es.com.lucassalinas.tinywords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alumno_solvam on 17/11/16.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "tinyDB5";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_USERS_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user TEXT, "+
                "password TEXT, "+
                "score INTEGER)";

        // create books table
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS users");

        // create fresh books table
        this.onCreate(db);
    }
    //---------------------------------------------------------------------

    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */

    // Books table name
    private static final String TABLE_USERS = "users";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_SCORE = "score";

    private static final String[] COLUMNS = {KEY_ID,KEY_USER,KEY_PASSWORD};

    public Users getScore(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor = db.rawQuery("SELECT id, score FROM users WHERE id=" + id, null);

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Users score = new Users();
        score.setId(Integer.parseInt(cursor.getString(0)));
        score.setScore(cursor.getInt(1));

        //log
        Log.d("getScore("+id+")", score.toString());

        // 5. return score
        return score;
    }

    public int updateScore(Users score) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("score", score.getScore()); // get title

        // 3. updating row
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(score.getId()) }); //selection args

        // 4. close
        db.close();

        return i;
    }

    public void addUser(Users user){
        //Log.d("addBook", book.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getUser()); // get title
        values.put(KEY_PASSWORD, user.getPassword()); // get author

        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public boolean compobaruser (String user){
        SQLiteDatabase db = this.getReadableDatabase();


        String[] args = new String[]{user};
        //String sql = "SELECT user FROM users='"+user+"'";
        //Cursor c = db.rawQuery(sql, null);

        Cursor c = db.rawQuery("SELECT * FROM users WHERE user=?", args);

        if(c.moveToFirst())
            return true;
        return false;
    }

    public boolean compobarlogin(String user, String pass){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] args = new String[]{user, pass};

        //Cursor c = db.rawQuery("SELECT * FROM users WHERE user=? and password=?",args);
        String sql = "SELECT * FROM users WHERE user='"+user+"' and password='"+pass+"'";
        Cursor c = db.rawQuery(sql, null);
        if(c.moveToFirst())
            return true;
        return false;
    }

    /*
    public Libros getBook(int id){

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_BOOKS, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[] { String.valueOf(id) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build book object
        Libros book = new Libros();
        book.setId(Integer.parseInt(cursor.getString(0)));
        book.setTitulo(cursor.getString(1));
        book.setAutor(cursor.getString(2));

        Log.d("getBook("+id+")", book.toString());

        // 5. return book
        return book;
    }

    // Get All Books
    public List<Libros> getAllBooks() {
        List<Libros> books = new LinkedList<Libros>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Libros book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Libros();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitulo(cursor.getString(1));
                book.setAutor(cursor.getString(2));

                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        // return books
        return books;
    }

    // Updating single book
    public int updateBook(Libros book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        if(!book.getAutor().equals("") && !book.getTitulo().equals("")){
            values.put("author", book.getAutor());
            values.put("title", book.getTitulo());
        }
        if(!book.getAutor().equals("") && book.getTitulo().equals("")){
            values.put("author", book.getAutor());
        }
        if(!book.getTitulo().equals("") && book.getAutor().equals("")){
            values.put("title", book.getTitulo());
        }

        //values.put("title", book.getTitulo()); // get title
        //values.put("author", book.getAutor()); // get author



        // 3. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args

        // 4. close
        db.close();

        return i;

    }

    // Deleting single book
    public void deleteBook(Libros book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(book.getId()) });

        // 3. close
        db.close();

        Log.d("deleteBook", book.toString());

    }*/
}
