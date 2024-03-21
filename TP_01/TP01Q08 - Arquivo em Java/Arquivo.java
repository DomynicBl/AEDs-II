import java.io.*;

public class Arquivo {
    public static void main(String[] args) {
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(reader.readLine());

            RandomAccessFile file = new RandomAccessFile("valores.txt", "rw");
            for (int i = 0; i < n; i++) {
                double value = Double.parseDouble(reader.readLine());
                file.writeBytes(Double.toString(value) + "\n");
            }
            file.close();

            file = new RandomAccessFile("valores.txt", "r");

            long length = file.length();
            long position = length - 1;
            file.seek(position);

            while (position >= 0) {
                file.seek(position);
                char c = (char) file.readByte();
                if (c == '\n') {

                    position--;
                    file.seek(position);
                    c = (char) file.readByte();
                }
                System.out.print(c);
                position--;
            }

            file.close();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
