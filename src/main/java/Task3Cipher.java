import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Task3Cipher {

    public static class EncryptedWriter extends FilterWriter {
        private int key;

        public EncryptedWriter(Writer out, int key) {
            super(out);
            this.key = key;
        }

        public void write(int c) throws IOException {
            super.write(c + key);
        }
    }

    public static class DecryptedReader extends FilterReader {
        private int key;

        public DecryptedReader(Reader in, int key) {
            super(in);
            this.key = key;
        }

        public int read() throws IOException {
            int c = super.read();
            if (c == -1) {
                return -1;
            }
            return c - key;
        }
    }

    public static void main(String[] args) {
        String text = "Simple Java IO Test";
        int key = 3;
        File file = new File("secret.txt");

        try {
            FileWriter fileWriter = new FileWriter(file);
            EncryptedWriter writer = new EncryptedWriter(fileWriter, key);
            
            for (int i = 0; i < text.length(); i++) {
                writer.write(text.charAt(i));
            }
            writer.close();
            System.out.println("Дані зашифровано.");
            
        } catch (Exception e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }

        try {
            FileReader fileReader = new FileReader(file);
            DecryptedReader reader = new DecryptedReader(fileReader, key);
            
            String result = "";
            int c;
            
            while ((c = reader.read()) != -1) {
                result += (char) c;
            }
            reader.close();
            System.out.println("Розшифровано: " + result);
            
        } catch (Exception e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
    }
}