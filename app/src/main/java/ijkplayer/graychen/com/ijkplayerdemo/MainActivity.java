package ijkplayer.graychen.com.ijkplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ijkplayer.graychen.com.videoview.activities.SettingsActivity;
import ijkplayer.graychen.com.videoview.widget.media.IjkVideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {
    private IjkVideoView  mVideoView;
    private IjkVideoView  mVideoView2;
    private Button button,button2;
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

        mVideoView.setVideoPath("rtsp://192.168.10.238:50000/video");
        mVideoView2.setVideoPath("rtsp://192.168.10.239:50000/video");
        mVideoView.start();
        mVideoView2.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
//                startActivity(intent);
                SettingsActivity.intentTo(MainActivity.this);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mVideoView.stopPlayback();
//                mVideoView.release(true);
//                mVideoView.stopBackgroundPlay();
                mVideoView2.togglePlayer();

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
