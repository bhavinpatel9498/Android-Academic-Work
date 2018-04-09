package m.com.lab7.bo;

import android.content.ContentValues;

/**
 * Created by Bhavin on 04-Apr-18.
 */

public class BookBO {

    private int id;
    private String bookName;
    private String authorName;
    private int rating;

    public static final String ID = "id";
    public static final String BOOKNAME = "book";
    public static final String AUTHORNAME = "author";
    public static final String BOOKRATING = "bookrating";

    public int getId() {
        return id;
    }

    public BookBO(String b, String a, int r)
    {
        this.authorName =a;
        this.bookName = b;
        this.rating = r;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ContentValues contValues(){

        ContentValues values = new ContentValues();
        values.put(BOOKNAME, this.bookName);
        values.put(AUTHORNAME, this.authorName );
        values.put(BOOKRATING, ""+this.rating);

        return values;
    }
}
