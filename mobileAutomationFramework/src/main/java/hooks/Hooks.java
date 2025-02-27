package hooks;

import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import utils.VideoManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Hooks {
    private final AppiumConfig appiumConfig = AppiumConfig.getInstance();
    private final AppiumDriver driver = appiumConfig.getAppiumDriver();
    @Autowired
    VideoManager videoManager;

    @AfterStep
    public void quit(Scenario scenario) throws IOException {
        String screenshotDir = "target/screenshots";
        if (!Files.exists(Paths.get(screenshotDir))) {
            try {
                Files.createDirectory(Paths.get(screenshotDir));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(scrFile.toPath(), new File(screenshotDir + File.separator + scenario.getName() + ".png").toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
           this.videoManager.stopRecording(scenario.getName(), driver);
        }
    }
}
