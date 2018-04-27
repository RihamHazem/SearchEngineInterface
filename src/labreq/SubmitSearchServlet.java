package labreq;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "SubmitSearchServlet", urlPatterns = "/SubmitSearchServlet")
public class SubmitSearchServlet extends HttpServlet {
    private DB db=new DB();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statement = request.getParameter("msg").trim();
        if(!SearchServlet.trieStatementSearch.search(statement)) {
            System.out.println("new statement.");
            SearchServlet.trieStatementSearch.insert(statement);
            // TODO: Save the statement in the database also
            db.insertStatement(statement);
        }

        ///////////////////////////////////
        String[] userInput = statement.split("\\s+");
        StringBuilder suggestedWord = new StringBuilder();
        int cnt = 0;
        for (String ui: userInput) {
            String res = SearchServlet.dictionaryTrie.didYouMean(ui);
            if (res == null) {
                cnt++;
                suggestedWord.append(ui);
                suggestedWord.append(" ");
            } else {
                suggestedWord.append(res);
                suggestedWord.append(" ");
            }
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        if (userInput.length == cnt) {// all the statement is true
            System.out.println("it's correct");
            out.print(""); // send empty string to indicate that there's no do you mean section in the page
//            searchEngine(request, response, "");
        }
        else {
            System.out.println("Did you Mean: " + suggestedWord);
            out.print(suggestedWord.toString());
//            searchEngine(request, response, suggestedWord.toString());
        }
        out.close();
        //////////////////////////////////////////////////////////
    }

    private void searchEngine (HttpServletRequest request, HttpServletResponse response, String didYouMean) throws IOException {
        //Getting original and stemmed arrays
        db.getTitle("getTitle");
        String query=request.getParameter("searchQuery");
        ArrayList<String> originalQuery=new ArrayList<>();
        ArrayList<String> stemmedQuery=new ArrayList<>();
        String[] arr = query.split(" ");
        for (int i=0;i<arr.length;i++) {
            originalQuery.add(arr[i]);
            System.out.println(arr[i]);
        }
        Stemmer stm=new Stemmer();
        stemmedQuery=stm.work(originalQuery);
        ///////////////////////////////////////
        System.out.println("call qp");
        QueryProcessor QP=new QueryProcessor();
        Map<String,Double> result= QP.work(stemmedQuery);
        GetMetaData mt=new GetMetaData();
        //System.out.println(mt.work(mt.connectToUrl("https://stackexchange.com/legal/terms-of-service")));
       /* ArrayList<String> res=new ArrayList<>();
        for(String s1:result){
            res.add(s1);
        }
        Ranker r=new Ranker();
        Map<String,Double> mp = new HashMap<String,Double>();
        try {
            mp=r.run(res,stemmedQuery,originalQuery);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        PrintWriter out=response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println(" <head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Search Engine/"+query+"</title>");
        out.println("<link rel=\"icon\" href=\"images/icon2.png\">");
        out.println("<link rel=\"stylesheet\" href=\"css/animate.css\">");
        out.println("<script src=\"javascript/file.js\"></script>");
        out.println("<script src=\"javascript/jquery-1.11.3.min.js\"></script>");
        out.println("<script src=\"javascript/plugins.js\"></script>");
        out.println("<script src=\"javascript/file.js\"></script>");
        out.println(" <script type=\"text/javascript\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">");
        out.println(" <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>");
        out.println(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");
        out.println("<meta name=\"description\" content=\"particles.js is a lightweight JavaScript library for creating particles.\">");
        out.println("<meta name=\"author\" content=\"Vincent Garreau\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">");
        out.println("<link rel=\"stylesheet\" media=\"screen\" href=\"css/s.css\">");
        out.println("</head>");
        out.println("<body>");

        out.println("<!-- particles.js container -->");
        out.println("<div id=\"particles-js\">");
        out.println("<div id=\"login\">");
        out.println(" <form class=\"w3-center\" action=\"/search\" method=\"get\">");
        out.println("  <h1 class=\"w3-wide  w3-center sw3-mobile \" id=\"intro\"> Search the Web!</h1>");
        out.println("<div id=\"image\">");
        out.println("<img src=\"images/icon1.png\">");
        out.println("</div>");
        out.println(" <div class=\" w3-container w3-center\">");
        out.println("<input class=\"w3-input w3-round-large w3-mobile \" align=\"middle\"name=\"searchLang\" id=\"searchLang\" type=\"text\" placeholder=\"Search...\">");
        out.println(" </div>");
        out.println("<button type=\"submit\" class=\"w3-button w3-round-large w3-mobile  \" id=\"btn\"> Submit</button>");
        out.println(" <div id=\"loader\"></div>");
        out.println("</form>");

        out.println("</div>");
        out.println("</div>");

        if (didYouMean.length() != 0) {
            System.out.println("There's do you mean");
            out.println("<p><strong>Did You Mean: " + didYouMean + "</strong></p>");
        }

        int i=0;
        out.println(result.size());
        for(Map.Entry<String,Double>m: result.entrySet())
//pagination here
        //for(int i=0;i<mp.size()/10;i++)
        {
            //for(int j=i*10;j<(i*10)+10;j++)
            // {
            out.println("<div class=\"panel panel-default animated fadeIn\">");

            out.println("<div class=\"panel-heading\">");
            out.println("  <span class=\"titles\">"+db.getTitle(m.getKey())+"</span>");//Title
            out.println(" <br>");
            out.println("<a class=\"links\" href=\""+m.getKey()+"\">"+m.getKey()+"</a> &nbsp; ");//Link
            out.println(" </div>");

            //out.println(" <div class=\"panel-body\"><span class=\"titles\">Description</span>: ");
            out.println("  <span class=\"snippet\">"+db.getDesc(m.getKey())+"\"</span>");
            out.println(" <br>");
                /*out.println("  <span class=\"snippet\">blablabalalalalalablablaalalalablablababalalalalaa\"</span>");
                out.println(" <br>");
                out.println("  <span class=\"snippet\">blablabalalalalalablablaalalalablablababalalalalaa\"</span>");
                out.println(" <br>");
                out.println("  <span class=\"snippet\">blablabalalalalalablablalalalablablabaabalalalalaa\"</span>");
                out.println(" <br>");*/
            out.println("<span class=\"queryWords\">word1, word2, ...., word_i</span>");
            out.println(" </div> ");
            out.println("</div>");
            out.println(" <br>");
            //}
            out.println("<p> NEW PAGINATION!</p> ");
            //i++;
        }
        out.println("<!-- scripts -->");
        out.println("<script src=\"javascript/particles.js\"></script>");
        out.println("<script src=\"javascript/app.js\"></script>");

        out.println("<div id=\"pagination\">");
        out.println(" <div class=\"w3-bar w3-center w3-mobile\">");
        out.println("</div>");
        out.println(" </div>");
        out.println(" <br> <br>");
        out.println("</body>");
        out.println("</html>");
    }
}
