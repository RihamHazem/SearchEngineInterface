package labreq;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class GetMetaData {
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36";
    public String work(Document doc) {
        Elements metaData = doc.select("meta");

        for (Element m : metaData) {
            String PText = m.text();
            return PText;
        }
        return "";
    }





    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    public Document connectToUrl(String address) {

        Connection connection = Jsoup.connect(address);
        Document htmlDocument;
        try {

            htmlDocument = connection.get();
        } catch (IOException e) {
            System.out.println("Error in internet.");
            return null;
        }
        if(connection.response().statusCode() != 200) {
            return null;
        }
        if(!connection.response().contentType().contains("text/html")) {
            if (connection.response().contentType().contains("application/pdf")) {
                System.out.println("I'm PDF");
            }
            System.out.println("**Failure** Retrieved something other than HTML");
            return null;
        }
        return htmlDocument;

    }

}