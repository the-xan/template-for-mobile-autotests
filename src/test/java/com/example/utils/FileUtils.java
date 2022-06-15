package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class FileUtils {
    public void downloadFileByUrl(String url, File app) {
        try (InputStream inputStream = new URL(url).openStream()) {
            copyInputStreamToFile(inputStream, app);
        } catch (IOException e) {
            throw new RuntimeException("Failed to download apk", e);
        }
    }
}
