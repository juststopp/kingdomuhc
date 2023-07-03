package fr.juststop.dev.kingdomuhc.utils;

import java.io.File;

public class Utils {

    public static void deleteFolder(File folder) {
        if(folder.exists()) {
            File[] files = folder.listFiles();
            if(files != null) {
                for (File file : files) {
                    if (file.isDirectory()) deleteFolder(file);
                    else file.delete();
                }
            }
        }

        folder.delete();
    }

    public static String generateProgressBar(float progress) {
        int totalBars = 20;
        int filledBars = (int) (progress * totalBars);
        int emptyBars = totalBars - filledBars;

        StringBuilder progressBar = new StringBuilder();
        for (int i = 0; i < filledBars; i++) {
            progressBar.append("§a█");
        }
        for (int i = 0; i < emptyBars; i++) {
            progressBar.append("§7█");
        }

        return progressBar.toString();
    }

}
