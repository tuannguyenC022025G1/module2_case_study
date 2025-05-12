package service;

import java.io.*;
import java.util.*;

public class MusicService {
    private final String dataDir = "data";

    public void showSongsInCategory(String path) {
        File file = new File(path);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                System.out.println("Danh sách bài hát:");
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("- " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Chưa có dữ liệu cho mục này.");
        }
    }

    public void searchSong(String keyword) {
        List<File> allFiles = listAllSongFiles(new File(dataDir));
        boolean found = false;
        for (File file : allFiles) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.toLowerCase().contains(keyword.toLowerCase())) {
                        System.out.println("Đang phát nhạc: " + line);
                        found = true;
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!found) System.out.println("Bài hát chưa được cập nhật.");
    }

    private List<File> listAllSongFiles(File dir) {
        List<File> result = new ArrayList<>();
        if (dir.isDirectory()) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    result.addAll(listAllSongFiles(file));
                } else if (file.getName().endsWith(".txt")) {
                    result.add(file);
                }
            }
        }
        return result;
    }
}
