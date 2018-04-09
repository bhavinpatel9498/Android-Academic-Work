package m.com.lab7.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

import m.com.lab7.bo.BookBO;

/**
 * Created by Bhavin on 04-Apr-18.
 */

public class BookContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "m.com.lab7.db.BookContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/books";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> STUDENTS_PROJECTION_MAP;

    static final int BOOKS = 1;
    static final int BOOKS_ID = 2;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "books", BOOKS);
        uriMatcher.addURI(PROVIDER_NAME, "books/#", BOOKS_ID);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        /**
         * Add a new student record
         */
        long rowID = db.insert(	DatabaseHelper.TABLE_BOOKS, "", values);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DatabaseHelper.TABLE_BOOKS);

        switch (uriMatcher.match(uri)) {
            case BOOKS:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;

            case BOOKS_ID:
                qb.appendWhere(  BookBO.ID  + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on student names
             */
            sortOrder = BookBO.BOOKNAME;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case BOOKS:
                count = db.delete(DatabaseHelper.TABLE_BOOKS, selection, selectionArgs);
                break;

            case BOOKS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( DatabaseHelper.TABLE_BOOKS, BookBO.ID +  " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case BOOKS:
                count = db.update(DatabaseHelper.TABLE_BOOKS, values, selection, selectionArgs);
                break;

            case BOOKS_ID:
                count = db.update(DatabaseHelper.TABLE_BOOKS, values,
                        BookBO.ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case BOOKS:
                return "vnd.android.cursor.dir/m.com.lab7.db.BookContentProvider";
            /**
             * Get a particular student
             */
            case BOOKS_ID:
                return "vnd.android.cursor.item/m.com.lab7.db.BookContentProvider";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }


}
