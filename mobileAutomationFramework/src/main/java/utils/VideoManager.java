package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Slf4j
public class VideoManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoManager.class);

    public void startRecording(AppiumDriver driver){
        ((CanRecordScreen) driver).startRecordingScreen();
    }

    public void stopRecording(String scenarioName, AppiumDriver driver) throws IOException {
        String media = ((CanRecordScreen) driver).stopRecordingScreen();
        String videoDir = "target/videos";
        if (!Files.exists(Paths.get(videoDir))) {
            try {
                Files.createDirectory(Paths.get(videoDir));
            } catch (IOException e) {
                LOGGER.error("error creating video directory" + e.toString());
            }
        }

        try (FileOutputStream stream = new FileOutputStream(videoDir + File.separator + scenarioName + ".mp4")) {
            stream.write(Base64.decodeBase64(media));
            stream.flush();
            LOGGER.info("video path: " + videoDir + File.separator + scenarioName + ".mp4");
        } catch (Exception e) {
            LOGGER.error("error during video capture" + e.toString());
        }
    }
}
