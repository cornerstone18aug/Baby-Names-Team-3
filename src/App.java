package src;

import java.util.List;

public class App {

  public static void main(String[] args) {
    int year = 1990;
    do {
      String text = Parser.parseString("babynames/baby" + year + ".html", "utf-8");
      Parser p = new Parser();
      List list = Parser.parseRating(text);
      list.forEach(System.out::println);
      year = year + 2;
      new Parser();
    } while (year <= 2008);
  }
}