package re.ghesquie.taggedimageviewer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mike on 25/11/2016.
 */

public class TaggedImageView extends ImageView implements View.OnTouchListener {

    public ArrayList<TagItem> tags;
    private Paint p;
    public boolean drawTags;
    public RecyclerView listView;

    public TaggedImageView(Context context) {
        this(context, null, 0);
    }

    public TaggedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaggedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        tags = new ArrayList<TagItem>();
        this.setOnTouchListener(this);

        p = new Paint();
        p.setColor(Color.argb(128, 255, 255, 255));
        p.setStrokeWidth(10);
        p.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if (drawTags) {
            Matrix m = this.getImageMatrix();
            for (TagItem tag : tags) {
                RectF r = new RectF(tag.pos_l, tag.pos_t, tag.pos_r, tag.pos_b);
                m.mapRect(r);
                canvas.drawRect(r, p);
            }
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Matrix m = this.getImageMatrix();

        for (int i = 0; i < tags.size(); i++) {
            TagItem tag = tags.get(i);
            RectF r = new RectF(tag.pos_l, tag.pos_t, tag.pos_r, tag.pos_b);
            m.mapRect(r);

            if (r.contains(x,y)) {
                listView.smoothScrollToPosition(i);
                return true;
            }
        }
        return false;
    }
}
