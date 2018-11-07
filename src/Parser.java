import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    List<Rating> ratingList;

    public static String parseString(String path, String charset) {
        StringBuilder sb = new StringBuilder();
        String s = null;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset))) {
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static List<Rating> parseRating(String text) {
        ratingList = new ArrayList<>();
        Rating ratingObject;

        Pattern pattern = Pattern.compile("<tr align=\"right\"><td>(?<rating>\\d{1,4})</td><td>(?<nameMan>\\w+)</td><td>(?<nameWoman>\\w+)</td>");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String d = matcher.group("rating");
            int rating = Integer.parseInt(d);
            String nameMan = matcher.group("nameMan");
            String nameWoman = matcher.group("nameWoman");
            ratingObject = new Rating(rating, nameMan, nameWoman);
            ratingList.add(ratingObject);
            createTXTFiles(ratingList);
        }
        return ratingList;
    }

    public static createTXTFiles(List<Rating> ratings) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        int year = 1990;
        do {
            try {
                fichero = new FileWriter("src/baby" + year + ".txt");
                pw = new PrintWriter(fichero);

                pw.println(year);

                for (int i = 0; i < ratings.size(); i++) {
                    pw.println(ratings.getNameMan() + " " + ratings.getRating());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != fichero)
                        fichero.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } while (year <= 2008);
    }
}
