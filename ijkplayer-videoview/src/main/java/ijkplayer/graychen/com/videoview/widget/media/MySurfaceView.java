package ijkplayer.graychen.com.videoview.widget.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mSurfaceHolder;
    //绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    private Bitmap bitmap;

    private Rect mSrcRect, mDestRect;

    // view 的宽高
    private int mTotalWidth, mTotalHeight;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("MySurfaceView","w："+w+" h:"+h+" oldw:"+oldw+" oldh:"+oldh);
        mTotalWidth = w;
        mTotalHeight = h;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        //开启子线程
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }
    public void setBitMap(Bitmap bitmap){
        this.bitmap = bitmap;
        if (mDestRect==null) {
            // 显示的区域宽度为view的宽度
            // 高度如果图片高度大于view的高度，显示view的高度，否则显示图片的高度；
            // 垂直居中显示
            /**
             * int left, view的宽度
             * int top, view高度-bitmap高度 再除以2
             * int right,view的宽度
             * int bottom  view高度-bitmap高度 再除以2 再加上bitmap高度
             */
            mDestRect = new Rect(0,
                    bitmap.getHeight()>mTotalHeight?0:(mTotalHeight-bitmap.getHeight())/2,
                    mTotalWidth,
                    bitmap.getHeight()>mTotalHeight? mTotalHeight:bitmap.getHeight()+(mTotalHeight-bitmap.getHeight())/2);
        }
    }
    @Override
    public void run() {
        while (mIsDrawing){
            drawBitmap();
        }
    }

    //绘图逻辑
    private void drawBitmap() {
        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();

            //绘图 Rect src: 是对图片进行裁截，若是空null则显示整个图片
            //RectF dst：是图片在Canvas画布中显示的区域， 大于src则把src的裁截区放大， 小于src则把src的裁截区缩小。
            mCanvas.drawBitmap(bitmap,null,mDestRect, null);
//            mCanvas.setBitmap(bitmap);
        }catch (Exception e){

        }finally {
            if (mCanvas != null){
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 初始化View
     */
    private void initView(){
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }
}

