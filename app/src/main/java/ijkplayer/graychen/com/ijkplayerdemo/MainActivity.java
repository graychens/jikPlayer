package ijkplayer.graychen.com.ijkplayerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ijkplayer.graychen.com.videoview.widget.media.IjkVideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IjkVideoView  mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        // prefer mVideoPath
        mVideoView.setVideoPath("rtsp://192.168.10.239:50000/video");

        mVideoView.start();
    }
}
