package m.com.lab7;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import m.com.lab7.bo.BookBO;
import m.com.lab7.db.BookContentProvider;
import m.com.lab7.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    /* Cars Name list */
    String booksNameList[] = new String[]{
            "Book1",
            "Book2",
            "Book3",
            "Book4",
            "Book5",
            "Book6",
            "Book7"};

    String booksNameListDB[] = new String[7];

    /* Cars desc list */

    String booksAuthorList[] = new String[]{
            "Author1",
            "Author2",
            "Author3",
            "Author4",
            "Author5",
            "Author6",
            "Author7"};

    String booksAuthorListDB[] = new String[7];

    /* Cars Image list */

    Integer booksRatingList[] = {
            1,
            4,
            5,
            3,
            3,
            3,
            2};

    Integer booksRatingListDB[] = new Integer[7];

    private ListView listView;
    private ListAdapter listAdapter;
    private Spinner drpDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbOperation();

        listView = (ListView)findViewById(R.id.booklayout);
        listAdapter = new BooksAdapter(MainActivity.this, booksNameListDB, booksAuthorListDB, booksRatingListDB);
        listView.setAdapter(listAdapter);

        drpDown = findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerAdpt = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, booksNameListDB);
        drpDown.setAdapter(spinnerAdpt);
        drpDown.setOnItemSelectedListener(this);
        setTabs();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        String [] arg = new String[]{item};
        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        String URL = "content://m.com.lab7.db.BookContentProvider/books";
        Uri books = Uri.parse(URL);
        Cursor c = managedQuery(books, null, "book=?", arg, "book");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(MainActivity.this, "Author   :"+c.getString(c.getColumnIndex(BookBO.AUTHORNAME))
                        +"\nRating   :"+c.getString(c.getColumnIndex( BookBO.BOOKRATING)), Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void dbOperation()
    {
        for(int i =0;i<booksNameList.length;i++)
        {
            BookBO bObj = new BookBO(booksNameList[i], booksAuthorList[i], booksRatingList[i]);
            Uri uri = getContentResolver().insert(BookContentProvider.CONTENT_URI, bObj.contValues());
        }

        String URL = "content://m.com.lab7.db.BookContentProvider/books";
        Uri books = Uri.parse(URL);
        Cursor c = managedQuery(books, null, null, null, "book");
        int index = 0;
        if (c.moveToFirst()) {
            do{
                booksNameListDB[index] = c.getString(c.getColumnIndex(BookBO.BOOKNAME));
                booksRatingListDB[index] = new Integer(c.getString(c.getColumnIndex( BookBO.BOOKRATING)));
                booksAuthorListDB[index] = c.getString(c.getColumnIndex( BookBO.AUTHORNAME));
                //Toast.makeText(MainActivity.this, "Index: "+index+" Name :"+booksNameListDB[index], Toast.LENGTH_SHORT).show();
                index++;
            } while (c.moveToNext() && index<7);
        }
    }

    private void setTabs()
    {
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("TabOne");
        spec.setContent(R.id.booklayout);
        spec.setIndicator("Book List");
        host.addTab(spec);

        TabHost.TabSpec spec1 = host.newTabSpec("TabTwo");
        spec1.setContent(R.id.spinner);
        spec1.setIndicator("Book Dropdown");
        host.addTab(spec1);
    }
}
