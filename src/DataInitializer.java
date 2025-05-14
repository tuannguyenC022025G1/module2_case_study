import java.io.*;

public class DataInitializer {
    public static void main(String[] args) {
        try {
            createDir("data/topics");
            createDir("data/top100");

            createFileWithContent("data/users.txt", "admin,admin,admin\n");

            createFileWithContent("data/hits.txt", """
                    Có hẹn với thanh xuân
                    Tháng tư là lời nói dối của em
                    Ngày chưa giông bão
                    """);

            createFileWithContent("data/bxh.txt", """
                    Em của ngày hôm qua
                    Hơn cả yêu
                    Nơi này có anh
                    """);

            createFileWithContent("data/topics/nhac_tre.txt", """
                    Một bước yêu vạn dặm đau
                    Anh ơi ở lại
                    Yêu được không
                    """);

            createFileWithContent("data/topics/kpop.txt", """
                    Dynamite
                    Kill This Love
                    Butter
                    """);

            createFileWithContent("data/topics/usuk.txt", """
                    Shape of You
                    Senorita
                    Blinding Lights
                    """);

            createFileWithContent("data/top100/vietnam.txt", """
                    Hết thương cạn nhớ
                    Hồng lâu mộng
                    Hoa nở không màu
                    """);

            createFileWithContent("data/top100/usuk.txt", """
                    Perfect
                    Stay
                    Uptown Funk
                    """);

            createFileWithContent("data/top100/kpop.txt", """
                    Boy With Luv
                    DNA
                    How You Like That
                    """);

            System.out.println("Tạo dữ liệu mẫu thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private static void createFileWithContent(String path, String content) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write(content);
            }
        }
    }
}
