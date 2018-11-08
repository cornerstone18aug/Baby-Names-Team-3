package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

  static List<Rating> ratingList;

  public Parser() {
    ratingList = new ArrayList<>();
  }

  public static String parseString(String path, String charset) {
    StringBuilder sb = new StringBuilder();
    String s = null;

    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(new FileInputStream(path), charset))) {
      while ((s = br.readLine()) != null) {
        sb.append(s);
      }
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  public static List<Rating> parseRating(String text) {
    Rating ratingObject;
    Pattern pattern = Pattern.compile(
        "<tr align=\"right\"><td>(?<rating>\\d{1,4})</td><td>(?<nameMan>\\w+)</td><td>(?<nameWoman>\\w+)</td>");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      String d = matcher.group("rating");
      int rating = Integer.parseInt(d);
      String nameMan = matcher.group("nameMan");
      String nameWoman = matcher.group("nameWoman");
      ratingObject = new Rating(rating, nameMan, nameWoman);
      ratingList.add(ratingObject);
    }
    // Sort the ArrayList
    sortArrayList(ratingList);
    // Create the txt files of the years
    createTXTFiles(ratingList);
    return ratingList;
  }

  public static List<Rating> sortArrayList(List<Rating> ratings) {
    Collections.sort(ratings, new Comparator<Rating>() {
      public int compare(Rating obj1, Rating obj2) {
        return (obj1.getNameWoman().compareTo(obj2.getNameWoman()));
      }
    });
    return ratings;
  }

  public static boolean createTXTFiles(List<Rating> ratings) {
    FileWriter flwriter = null;
    int year = 1990;
    do {
      try {
        flwriter = new FileWriter("babynames/txt/baby" + year + ".txt");
        BufferedWriter bfwriter = new BufferedWriter(flwriter);
        bfwriter.write(year + "\n");
        for (Rating r : ratings) {
          bfwriter.write(r.getNameMan() + " " + r.getRating() + "\n");
          bfwriter.write(r.getNameWoman() + " " + r.getRating() + "\n");
        }
        bfwriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (flwriter != null) {
          try {
            flwriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      year = year + 2;
    } while (year <= 2008);
    return true;
  }
}


