package ijkplayer.graychen.com.videoview.widget.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public class MyView extends android.support.v7.widget.AppCompatImageView {

    Bitmap map;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void setBitMap(Bitmap bitmap){
        this.map = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1. 创建缓冲画布
        Canvas bufferCanvas = new Canvas();
        //2. 创建缓存Bitmap
//        Bitmap bufferBitmap = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Bitmap bufferBitmap = this.map;
        //3. 将缓冲位图设置给缓冲画布
        bufferCanvas.setBitmap(bufferBitmap);
        //4. 在缓冲画布上绘制真实的位图
        bufferCanvas.drawBitmap(bufferBitmap, 0, 0, null);
        //5. 用屏幕的画布绘制缓冲位图
        canvas.drawBitmap(bufferBitmap,0,0, null);

    }
}
