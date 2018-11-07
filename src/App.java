import java.util.List;

public class App {

    public static void main(String[] args) {
        String text = Parser.parseString("babynames/baby2008.html", "utf-8");
        List list = Parser.parseRating(text);
        list.forEach(System.out::println);
    }
}