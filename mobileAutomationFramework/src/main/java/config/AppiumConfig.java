package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AppiumConfig {
private static final Logger log = LoggerFactory.getLogger(AppiumConfig.class);
private static AppiumConfig instance;
public static synchronized AppiumConfig getInstance() {
        if (instance == null) {
            instance = new AppiumConfig();
        }
        return instance;
    }
    private final Map<String, AppiumDriver> drivers = new HashMap<>();
@Bean(name = "appiumDriver")
    public AppiumDriver getAppiumDriver() {
        return drivers.computeIfAbsent(Thread.currentThread().getName(), k -> {
            try {
                URL url = getAppiumServerUrl();
                String platform = getPlatformName();
                AppiumDriver driver = createDriver(url, platform);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return driver;
            } catch (MalformedURLException e) {
                log.error("Error creating Appium driver", e);
            }
            return null;
        });
    }

    private URL getAppiumServerUrl() throws MalformedURLException {
        Properties props = getProperties();
        return URI.create(props.getProperty("appiumURL")).toURL();
    }

    private String getPlatformName() {
        String platform = System.getProperty("platformName");
        return (platform == null || platform.isEmpty()) ? getProperties().getProperty("platformName") : platform;
    }

    private AppiumDriver createDriver(URL url, String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return new AndroidDriver(url, getAndroidOptions());
        } else if (platform.equalsIgnoreCase("ios")) {
            return new IOSDriver(url, getIosOptions());
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    private UiAutomator2Options getAndroidOptions() {
        Properties props = getProperties();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAppPackage(props.getProperty("androidAppPackage"));
        options.setAppActivity(props.getProperty("androidAppActivity"));
        options.setPlatformVersion(props.getProperty("platformVersion"));
        options.setDeviceName(props.getProperty("deviceName"));
        options.setAutomationName("UiAutomator2");
        options.setNewCommandTimeout(Duration.ofSeconds(60));
        return options;
    }

    private XCUITestOptions getIosOptions() {
        Properties props = getProperties();
        XCUITestOptions options = new XCUITestOptions();
        options.setBundleId(props.getProperty("iOSBundleId"));
        options.setDeviceName(props.getProperty("iOSDeviceName"));
        options.setUdid(getBootedSimulatorUDID());
        options.setAutomationName("XCUITest");
        options.setNewCommandTimeout(Duration.ofSeconds(60));
        return options;
    }
    public static String getBootedSimulatorUDID() {
        String udid = null;

        try {
            Process process = Runtime.getRuntime().exec(new String[]{"bash", "-c", "xcrun simctl list | grep Booted"});

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                Pattern pattern = Pattern.compile("\\(([^)]+)\\) \\(Booted\\)");

                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        udid = matcher.group(1);
                        break;
                    }
                }
            }
            if (udid == null) {
                log.error("No booted simulators found");
            }
        } catch (IOException e) {
           log.error("Error executing the command to list booted simulators: " + e.getMessage());
        }
        log.info("Booted simulator udid: " + udid);
        return udid;
    }

    public static Properties getProperties() {
        Properties props = new Properties();
        try (InputStream input = AppiumConfig.class.getClassLoader().getResourceAsStream("bootstrap.properties")) {
            if (input == null) {
                log.error("Sorry, unable to find bootstrap.properties");
            }
            props.load(input);
        } catch (IOException ex) {
           log.error("Error loading properties file", ex);
        }
        return props;
    }
}