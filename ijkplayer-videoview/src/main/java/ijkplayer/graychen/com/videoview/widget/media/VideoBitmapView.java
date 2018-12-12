package ijkplayer.graychen.com.videoview.widget.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import ijkplayer.graychen.com.videoview.R;

public class VideoBitmapView extends FrameLayout {
    private IjkVideoView ijkVideoView;
    private ImageView imageView;
    private int mType; //  0 ：RTSP流 使用ijkVideoView  1：JPEG流 使用imageView 。默认为RTSP流
    private boolean isPlaying = false;
    public VideoBitmapView( Context context) {
        super(context,null);

    }

    public VideoBitmapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video_bitmap, this,true);
        ijkVideoView = view.findViewById(R.id.video);
        imageView = view.findViewById(R.id.image);
    }

    public void startPlay(String path){
        if (mType==0) {
            ijkVideoView.setVideoPath(path);
            ijkVideoView.start();
        }
    }

    public boolean isPlaying() {
        if (mType==0) {
            return  ijkVideoView.isPlaying();
        } else {
            return isPlaying;
        }
    }
    public void togglePlayer(){
        if (mType ==0) {
            ijkVideoView.togglePlayer();
        }
    }
    public void startPlay(Bitmap bitmap){
        if (mType==1) {
            isPlaying = true;
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();
        }
    }
    public void stopPlay() {
        if (mType==0) {

            ijkVideoView.stopPlayback();
            ijkVideoView.release(true);
            ijkVideoView.stopBackgroundPlay();
        } else {
            isPlaying = false;
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
