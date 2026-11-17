import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URLConnection;

public class Task4TagFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть URL: ");
        String urlString = scanner.nextLine();

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String html = "";
            String line;
            while ((line = reader.readLine()) != null) {
                html += line + "\n";
            }
            reader.close();

            Map<String, Integer> tags = new HashMap<>();
            Pattern pattern = Pattern.compile("<\\s*([a-zA-Z0-9]+)[^>]*>");
            Matcher matcher = pattern.matcher(html);

            while (matcher.find()) {
                String tag = matcher.group(1).toLowerCase();
                if (tags.containsKey(tag)) {
                    tags.put(tag, tags.get(tag) + 1);
                } else {
                    tags.put(tag, 1);
                }
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(tags.entrySet());

            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    int countDiff = a.getValue().compareTo(b.getValue());
                    if (countDiff != 0) {
                        return countDiff;
                    }
                    return a.getKey().compareTo(b.getKey());
                }
            });

            System.out.println("\n--- За частотою ---");
            for (Map.Entry<String, Integer> entry : list) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return a.getKey().compareTo(b.getKey());
                }
            });

            System.out.println("\n--- В лексикографічному порядку ---");
            for (Map.Entry<String, Integer> entry : list) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}