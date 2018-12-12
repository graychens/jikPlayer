package ijkplayer.graychen.com.videoview.widget.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ijkplayer.graychen.com.videoview.R;

public class VideoBitmapView extends FrameLayout {
    private IjkVideoView ijkVideoView;
    private ImageView imageView;
    private int mType; //  0 ：RTSP流 使用ijkVideoView  1：JPEG流 使用imageView 。默认为RTSP流
    private boolean receiveData = false; // 是否接收JPEG流数据

    public VideoBitmapView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public VideoBitmapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public VideoBitmapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VideoBitmapView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {

        ijkVideoView = new IjkVideoView(context);
        imageView = new ImageView(context);
        imageView.setVisibility(GONE);
        FrameLayout.LayoutParams layoutParams_txt = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);
        addView(imageView, layoutParams_txt);
        addView(ijkVideoView, layoutParams_txt);
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_video_bitmap, this,true);
//        ijkVideoView = view.findViewById(R.id.video);
//        imageView = view.findViewById(R.id.image);
    }

    /**
     * 播放直播流
     * 如果为图片流则表示开始接收数据
     * @param path
     */
    public void startPlay(String path){
        if (mType==0) {
            ijkVideoView.setVideoPath(path);
            ijkVideoView.start();
        } else {
            receiveData = true;
        }
    }

    /**
     * 图片流才有的方法，设置图片
     * @param bitmap
     */
    public void setBitmap(Bitmap bitmap){
        if (mType==1 && receiveData) {
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();
        }
    }
    /**
     * 视频流才调用的方法 立即同步视频
     */
    public void togglePlayer(){
        if (mType ==0) {
            ijkVideoView.togglePlayer();
        }
    }

    /**
     * 停止播放
     */
    public void stopPlay() {
        if (mType==0) {
            ijkVideoView.stopPlayback();
            ijkVideoView.release(true);
            ijkVideoView.stopBackgroundPlay();
        } else {
            receiveData = false;
        }
    }

    /**
     * 是否正在播放/接受数据
     * @return
     */
    public boolean isPlaying() {
        if (mType==0) {
            return  ijkVideoView.isPlaying();
        } else { // 不接受数据说明没有播放
            return receiveData;
        }
    }
    public void setType (int type) {
        this.mType = type;
        if (type==1) {
            imageView.setVisibility(VISIBLE);
            ijkVideoView.stopPlayback();
            ijkVideoView.release(true);
            ijkVideoView.stopBackgroundPlay();
            ijkVideoView.setVisibility(GONE);
        } else {
            imageView.setVisibility(GONE);
            ijkVideoView.setVisibility(VISIBLE);
        }
    }



}
