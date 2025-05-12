package service;

import java.io.*;

public class AdminService {
    public void addSong(String path, String songName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(songName + "\n");
            System.out.println("Đã thêm bài hát.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeSong(String path, String songName) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Tệp không tồn tại.");
            return;
        }
        try {
            File temp = new File("temp.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(temp));

            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().equalsIgnoreCase(songName.trim())) {
                    bw.write(line + "\n");
                }
            }

            br.close();
            bw.close();

            if (file.delete() && temp.renameTo(file)) {
                System.out.println("Đã xoá bài hát.");
            } else {
                System.out.println("Không thể cập nhật tệp.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createCategory(String path) {
        File file = new File(path);
        try {
            if (file.createNewFile()) {
                System.out.println("Đã tạo chủ đề mới.");
            } else {
                System.out.println("Chủ đề đã tồn tại.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String path) {
        File file = new File(path);
        if (file.exists() && file.delete()) {
            System.out.println("Đã xoá chủ đề.");
        } else {
            System.out.println("Không thể xoá.");
        }
    }
}