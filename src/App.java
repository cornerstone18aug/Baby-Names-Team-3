import java.util.List;

public class App {

    public static void main(String[] args) {
        private int year = 1990;
        do {
            String text = Parser.parseString("babynames/baby" + year + ".html", "utf-8");
            year = year + 2;
            List list = Parser.parseRating(text);
            list.forEach(System.out::println);
        } while (year <= 2008);
    }
}