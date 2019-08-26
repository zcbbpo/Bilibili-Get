package org.bilibili;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author Jim
 */
public class FFMPEGTest {
    public static void main(String[] args) throws Exception {

        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        System.out.println(ffmpeg);
        ProcessBuilder pb = new ProcessBuilder(ffmpeg, "-i", "/Users/jimcao/test/video/58282288/101670167/video", "-i",
                "/Users/jimcao/test/video/58282288/101670167/audio", "-c:v", "copy", "-c:a", "copy", "-strict", "experimental",
                "/Users/jimcao/test/video/58282288/101670167/output.mp4"
                );
        int result = pb.inheritIO().start().waitFor();
        System.out.println(result);
    }

    private void test() throws Exception {
        FFmpegFrameGrabber audio = new FFmpegFrameGrabber("/Users/jimcao/test/video/58282288/101670167/audio");
        FFmpegFrameGrabber video = new FFmpegFrameGrabber("/Users/jimcao/test/video/58282288/101670167/video");
        audio.start();
        video.start();
        System.out.println(audio.getFormat());
        System.out.println(video.getFormat());

        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("/Users/jimcao/test/video/58282288/101670167/output.mp4", audio.getAudioChannels());
        recorder.setFormat(video.getFormat());
        recorder.setVideoCodec(video.getVideoCodec());
        recorder.setFrameRate(video.getFrameRate());
        recorder.setPixelFormat(video.getPixelFormat());

        recorder.setImageWidth(video.getImageWidth());
        recorder.setImageHeight(video.getImageHeight());
        recorder.setAudioCodec(audio.getAudioCodec());

        recorder.setVideoOption("preset", "medium");
        recorder.setVideoBitrate(400000);

        recorder.start();
        Frame videoFrame = video.grabFrame();
        Frame audioFrame = audio.grabFrame();

        while (videoFrame != null && audioFrame != null) {

            recorder.setTimestamp(videoFrame.timestamp);
            recorder.record(videoFrame);
            recorder.record(audioFrame);
            videoFrame = video.grabFrame();
        }
        recorder.stop();
        audio.stop();
        video.stop();
        recorder.release();
    }
}
