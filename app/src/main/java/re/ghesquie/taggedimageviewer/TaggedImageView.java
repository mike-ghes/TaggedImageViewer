package re.ghesquie.taggedimageviewer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by mike on 25/11/2016.
 */

public class TaggedImageView extends ImageView {

    public ArrayList<TagItem> tags;
    private Paint p;

    public TaggedImageView(Context context) {
        this(context, null, 0);
    }

    public TaggedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaggedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        tags = new ArrayList<TagItem>();

        p = new Paint();
        p.setColor(Color.argb(128, 255, 255, 255));
        p.setStrokeWidth(10);
        p.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Matrix m = this.getImageMatrix();
        for (TagItem tag : tags) {
            RectF r = new RectF(tag.pos_l, tag.pos_t, tag.pos_r, tag.pos_b);
            m.mapRect(r);
            canvas.drawRect(r, p);
        }
    }
}
