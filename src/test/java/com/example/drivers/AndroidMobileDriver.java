package com.example.drivers;

import com.codeborne.selenide.WebDriverProvider;
import com.example.utils.FileUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class AndroidMobileDriver implements WebDriverProvider {
    private final String DEVICE_NAME = "Pixel 4 API 30";
    private static final String LOCAL_HOST = "http://localhost:4723";

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);

        options.clearDeviceLogsOnStart();


        // options.setCapability("noReset", "true");
        // options.setCapability("fullReset", "false");

        options.setPlatformName("Android");
        options.setDeviceName(DEVICE_NAME);
        options.setPlatformVersion("11.0");

        //options.setCapability("appium:language","ru");
        //options.setCapability("appium:locale","RU");

        options.setLanguage("en");
        options.setLocale("En");

        options.setApp(app.getAbsolutePath());
        options.setAppPackage("org.wikipedia.alpha");
        options.setAppActivity("org.wikipedia.main.MainActivity");

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    public static URL getAppiumServerUrl() {
        try {
            return new URL(LOCAL_HOST + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File getApp() {
        final String
                APP_PATH = "src/test/resources/apk/app-alpha-universal-release.apk",
                APP_URL = "https://github.com/wikimedia/apps-android-wikipedia/" +
                        "releases/download/latest/app-alpha-universal-release.apk?raw=true";

        File app = new File(APP_PATH);
        if (!app.exists()) {
            FileUtils fileUtils = new FileUtils();
            fileUtils.downloadFileByUrl(APP_URL, app);
        }
        return app;
    }
}
