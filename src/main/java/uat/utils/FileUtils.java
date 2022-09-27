package uat.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static uat.configs.Config.SCREENSHOT_PATH;

@Slf4j
public class FileUtils {
    private FileUtils() {
    }

    public static String getFile(String scrFilename, File directory) {
        if (!directory.exists()) {
            directory.mkdir();
        }
        return SCREENSHOT_PATH + scrFilename + ".png";
    }

    public static void writeFileToPath(byte[] byteArray, Path path) {
        try {
            Files.write(path, byteArray);
        } catch (IOException ioe) {
            log.error("Exception on copying screenshot file! " + ioe);
        }
    }

    public static void writeFile(String scrFilename, byte[] byteArray) {
        File directory = new File(SCREENSHOT_PATH);
        String filePath = getFile(scrFilename, directory);
        Path path = Paths.get(filePath);
        writeFileToPath(byteArray, path);
    }
}
