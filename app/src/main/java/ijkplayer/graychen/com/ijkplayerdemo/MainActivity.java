package ijkplayer.graychen.com.ijkplayerdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ijkplayer.graychen.com.videoview.activities.SettingsActivity;
import ijkplayer.graychen.com.videoview.widget.media.IjkVideoView;
import ijkplayer.graychen.com.videoview.widget.media.VideoBitmapView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private VideoBitmapView mVideoView;
    private VideoBitmapView  mVideoView2;
    private Button button,button2,button3,button4,button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoView =  findViewById(R.id.video_view);
        mVideoView.setType(1);
        mVideoView2 =  findViewById(R.id.video_view2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

//        mVideoView.setVideoPath("rtsp://192.168.10.238:50000/video");
//        mVideoView2.setVideoPath("rtsp://192.168.10.239:50000/video");

        mVideoView.startPlay("rtsp://192.168.10.238:50000/video");
        mVideoView2.startPlay("rtsp://192.168.10.239:50000/video");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsActivity.intentTo(MainActivity.this);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 立即同步
//                mVideoView2.togglePlayer();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.test );
                mVideoView.setBitmap(bmp);

            }
        });
        // 重新播放
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","mVideoView2-isPlaying:"+mVideoView2.isPlaying());

                mVideoView2.stopPlay();
                Log.d("MainActivity","mVideoView2-isPlaying:"+mVideoView2.isPlaying());

                mVideoView2.startPlay("rtsp://192.168.10.239:50000/video");

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mVideoView.togglePlayer();
                Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.test2 );
                mVideoView.setBitmap(bmp);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","mVideoView-isPlaying:"+mVideoView.isPlaying());
                mVideoView.stopPlay();
                Log.d("MainActivity","mVideoView-isPlaying:"+mVideoView.isPlaying());

                mVideoView.startPlay("rtsp://192.168.10.238:50000/video");
//                mVideoView.start();

            }
        });



    }

    private boolean mBackPressed;

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();

//        if (mBackPressed ) {
//            mVideoView.stopPlay();
//        } else {
//            mVideoView.enterBackground();
//        }
//        IjkMediaPlayer.native_profileEnd();
    }
}
