
package net.impjq.account;

import twitter4j.auth.AccessToken;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.impjq.base.BaseHttpServlet;
import net.impjq.database.Sqlite;

public class Register2 extends BaseHttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 5407965005777694140L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // TODO Auto-generated method stub
        super.doGet(req, resp);

        String username = req.getParameter("user_name");
        String password = req.getParameter("user_password");
        String twitterUserName = req.getParameter("user_twitter_user_name");
        String twitterPassword = req.getParameter("user_twitter_password");
        String email = req.getParameter("user_email");

        if (null == username || null == password) {
            sendRegisterHtml();
        } else {

            AccessToken accessToken = getXAuthAccessToken(twitterUserName, twitterPassword);

            boolean result = false;
            if (null != accessToken) {
                result = Sqlite.getInstance().addUser(username, password,
                        accessToken.getToken(),
                        accessToken.getTokenSecret(), email, CONSUMER_KEY, CONSUMER_SECRET);
                if (result) {
                    out.println("Register success.");
                } else {
                    out.println("Register failed,maybe the user already existed,please use another name.");
                }
            } else {
                out.println("Can't get the Twitter Access Token,may be your Twitter UserName/Password is wrong?");
            }

        }

    }

    private void sendRegisterHtml() {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Register</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Register</h3>");

        // if (firstName != null || lastName != null) {
        // out.println("First Name:");
        // out.println(" = " + HTMLFilter.filter(firstName) + "<br>");
        // out.println("Last Name:");
        // out.println(" = " + HTMLFilter.filter(lastName));
        // } else {
        // out.println("No Parameters, Please enter some");
        // }
        out.println("<P>");
        out.print("<form action=\"");
        out.print("Register\" ");
        out.println("method=POST>");
        out.println("User Name:");
        out.println("<input type=text size=20 name=user_name>");
        // out.println("<br>");
        out.println("Password:");
        out.println("<input type=text size=20 name=user_password>");
        // out.println("<br>");
        out.println("Email:");
        out.println("<input type=text size=20 name=user_email>");
        out.println("<br>");
        out.println("Your Twitter User Name:");
        out.println("<input type=text size=20 name=user_twitter_user_name>");
        // out.println("<br>");
        out.println("Your Twitter Password:");
        out.println("<input type=text size=20 name=user_twitter_password>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
