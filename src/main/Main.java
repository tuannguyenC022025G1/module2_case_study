package main;
import model.User;
import service.AuthService;
import service.MusicService;
import service.AdminService;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthService authService = new AuthService();
        MusicService musicService = new MusicService();
        AdminService adminService = new AdminService();
        User currentUser = null;

        while (currentUser == null) {
            System.out.println("1. Đăng nhập\n2. Đăng ký");
            String choice = sc.nextLine();
            System.out.print("Tên đăng nhập: ");
            String user = sc.nextLine();
            System.out.print("Mật khẩu: ");
            String pass = sc.nextLine();

            if (choice.equals("1")) {
                currentUser = authService.login(user, pass);
                if (currentUser == null) System.out.println("Sai thông tin đăng nhập.");
            } else {
                boolean success = authService.register(user, pass);
                if (success) System.out.println("Đăng ký thành công.");
                else System.out.println("Tên người dùng đã tồn tại.");
            }
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Nhạc hit");
            System.out.println("2. Bảng xếp hạng");
            System.out.println("3. Nhạc theo chủ đề");
            System.out.println("4. Top 100");
            System.out.println("5. Tìm kiếm");
            if (currentUser.isAdmin()) System.out.println("6. Quản lý (Admin)");
            System.out.println("0. Thoát");
            String menu = sc.nextLine();

            switch (menu) {
                case "1" -> musicService.showSongsInCategory("data/hits.txt");
                case "2" -> musicService.showSongsInCategory("data/bxh.txt");
                case "3" -> {
                    System.out.println("Chọn topic: nhac_tre, kpop, usuk...");
                    String topic = sc.nextLine();
                    musicService.showSongsInCategory("data/topics/" + topic + ".txt");
                }
                case "4" -> {
                    System.out.println("Chọn top: vietnam, usuk, kpop...");
                    String top = sc.nextLine();
                    musicService.showSongsInCategory("data/top100/" + top + ".txt");
                }
                case "5" -> {
                    System.out.print("Nhập tên bài hát: ");
                    String keyword = sc.nextLine();
                    musicService.searchSong(keyword);
                }
                case "6" -> {
                    if (!currentUser.isAdmin()) break;
                    System.out.println("1. Thêm bài hát");
                    System.out.println("2. Xoá bài hát");
                    System.out.println("3. Tạo chủ đề");
                    System.out.println("4. Xoá chủ đề");
                    String aChoice = sc.nextLine();
                    System.out.print("Đường dẫn tệp: ");
                    String path = sc.nextLine();
                    switch (aChoice) {
                        case "1" -> {
                            System.out.print("Tên bài hát: ");
                            adminService.addSong(path, sc.nextLine());
                        }
                        case "2" -> {
                            System.out.print("Tên bài hát: ");
                            adminService.removeSong(path, sc.nextLine());
                        }
                        case "3" -> adminService.createCategory(path);
                        case "4" -> adminService.deleteCategory(path);
                    }
                }
                case "0" -> {
                    System.out.println("Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}