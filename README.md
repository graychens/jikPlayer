# jikPlayer
根据jikplayer0.8.8版本导出的so包，支持rtsp
将so包和demo提取出来 封装成一个库方便使用


#how to use it

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}

implementation 'com.github.graychens:jikPlayer:Tag'

<ijkplayer.graychen.com.videoview.widget.media.IjkVideoView
    android:id="@+id/video_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    
        IjkVideoView  mVideoView = (IjkVideoView) findViewById(R.id.video_view);
        mVideoView.setVideoPath("rtsp://192.168.10.239:50000/video");
        mVideoView.start();

  
  
