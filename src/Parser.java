import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

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

        List<Rating> ratingList = new ArrayList<>();

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
        }

        return ratingList;
    }
}
