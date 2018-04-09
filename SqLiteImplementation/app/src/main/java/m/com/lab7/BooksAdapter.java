package m.com.lab7;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Bhavin on 04-Apr-18.
 */

public class BooksAdapter extends ArrayAdapter {

    private final Activity classContext;
    private final Integer[] ratingList;
    private final String[] bookNameList;
    private final String[] authorList;

    public BooksAdapter(Activity actCtx, String[] booksName, String[] authorName, Integer[] rating)
    {
        super(actCtx, R.layout.booklayout, booksName);

        this.classContext = actCtx;
        this.ratingList = rating;
        this.bookNameList = booksName;
        this.authorList = authorName;
    }

    public View getView(int pos, View view, ViewGroup parentView)
    {
        LayoutInflater layoutInflater = this.classContext.getLayoutInflater();

        View listViewElement = layoutInflater.inflate(R.layout.booklayout, null, true);


        RatingBar rating = (RatingBar) listViewElement.findViewById(R.id.ratingBar);
        TextView bookName = (TextView) listViewElement.findViewById(R.id.textView);
        TextView author = (TextView) listViewElement.findViewById(R.id.textView2);


        rating.setRating(Float.parseFloat(""+ratingList[pos]));
        bookName.setText(bookNameList[pos]);
        author.setText(authorList[pos]);

        return listViewElement;

    }
}
