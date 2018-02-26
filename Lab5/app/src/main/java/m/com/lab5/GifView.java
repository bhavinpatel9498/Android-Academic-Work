package m.com.lab5;

import java.io.InputStream;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

public class GifView extends View {
    public Movie mov;
    public long mst;
    private int gifId;

    public GifView(Context ctx)
    {
        super(ctx);
        initializeView();
    }

    public GifView(Context ctx, AttributeSet attr)
    {
        super(ctx, attr);
        initializeView();
    }

    public GifView(Context ctx, AttributeSet attr, int styl)
    {
        super(ctx, attr, styl);
        initializeView();
    }

    private void initializeView()
    {
        InputStream input = getContext().getResources().openRawResource(+ R.drawable.car);
        mov = Movie.decodeStream(input);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long t = android.os.SystemClock.uptimeMillis();
        if (mst == 0)
        {
            mst = t;
        }
        if (mov != null)
        {
            int rTime = (int) ((t - mst) % mov.duration());
            mov.setTime(rTime);
            canvas.scale(1.9f, 1.21f);
            mov.draw(canvas, 0f, 0f);
            this.invalidate();
        }
    }

    public void setGIFResource(int res)
    {
        this.gifId = res;
        initializeView();
    }

    public int getGIFResource()
    {
        return this.gifId;
    }
}
