import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Task1MaxWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до файлу: ");
        String filePath = scanner.nextLine();

        String maxLine = "";
        int maxWordCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String cleanLine = line.trim();
                int count = 0;
                
                if (!cleanLine.isEmpty()) {
                    String[] words = cleanLine.split("\\s+");
                    count = words.length;
                }
                
                if (count > maxWordCount) {
                    maxWordCount = count;
                    maxLine = line;
                }
            }
            reader.close();
            
            System.out.println("Кількість слів: " + maxWordCount);
            System.out.println("Знайдений рядок: " + maxLine);
            
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}