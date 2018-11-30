package ijkplayer.graychen.com.ijkplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ijkplayer.graychen.com.videoview.activities.SettingsActivity;
import ijkplayer.graychen.com.videoview.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private IjkVideoView  mVideoView;
    private IjkVideoView  mVideoView2;
    private Button button,button2,button3,button4,button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // init player
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView2 = (IjkVideoView) findViewById(R.id.video_view2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);

        mVideoView.setVideoPath("rtsp://192.168.10.238:50000/video");
        mVideoView2.setVideoPath("rtsp://192.168.10.239:50000/video");
        mVideoView2.getVideoPath();
        mVideoView.start();
        mVideoView2.start();
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
                mVideoView2.togglePlayer();

            }
        });
        // 重新播放
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","mVideoView2-isPlaying:"+mVideoView2.isPlaying());

                mVideoView2.stopPlayback();
                Log.d("MainActivity","mVideoView2-isPlaying:"+mVideoView2.isPlaying());

                mVideoView2.setVideoPath("rtsp://192.168.10.239:50000/video");
                mVideoView2.start();

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.togglePlayer();

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","mVideoView-isPlaying:"+mVideoView.isPlaying());
                mVideoView.stopPlayback();
                Log.d("MainActivity","mVideoView-isPlaying:"+mVideoView.isPlaying()+" "+mVideoView.getVideoPath());

                mVideoView.setVideoPath("rtsp://192.168.10.238:50000/video");
                mVideoView.start();

            }
        });

        mVideoView2.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                Log.d("MainActivity","视频2-onError what:"+what+" extra:"+extra);
                return false;
            }
        });
        mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                Log.d("MainActivity","视频1-onError what:"+what+" extra:"+extra);
                return false;
            }
        });

        mVideoView2.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mp) {
                Log.d("MainActivity","视频2-onCompletion");
            }
        });
        mVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer mp) {
                Log.d("MainActivity","视频1-onCompletion");
            }
        });

        mVideoView2.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                Log.d("MainActivity","视频2-onPrepared");

            }
        });
        mVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                Log.d("MainActivity","视频1-onPrepared");

            }
        });

        mVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                Log.d("MainActivity","视频1-onInfo what:"+what+" extra:"+extra);
                return false;
            }
        });
        mVideoView2.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                Log.d("MainActivity","视频2-onInfo what:"+what+" extra:"+extra);
                return false;
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

        if (mBackPressed ) {
            mVideoView.stopPlayback();
            mVideoView.release(true);
            mVideoView.stopBackgroundPlay();
        } else {
            mVideoView.enterBackground();
        }
        IjkMediaPlayer.native_profileEnd();
    }
}
