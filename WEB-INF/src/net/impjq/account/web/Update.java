
package net.impjq.account.web;

import twitter4j.auth.AccessToken;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.impjq.account.AccountInfo;
import net.impjq.base.BaseHttpServlet;
import net.impjq.database.Sqlite;
import net.impjq.util.Utils;

public class Update extends BaseHttpServlet {
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
            sendUpdateHtml();
        } else {
            AccountInfo accountInfo = getAccountInfo(username);

            if (accountInfo.isPasswordMatched(password)) {// Right password
                boolean isTwitterAccountAvailable = !Utils.isEmpty(twitterUserName)
                        && !Utils.isEmpty(twitterPassword);

                AccessToken accessToken = null;
                if (isTwitterAccountAvailable) {
                    accessToken = getXAuthAccessToken(twitterUserName, twitterPassword);
                }

                String token = "";
                String tokenSecret = "";
                if (null != accessToken) {
                    token = accessToken.getToken();
                    tokenSecret = accessToken.getTokenSecret();
                } else {
                    out.println("Can't get the Twitter Access Token,may be your Twitter UserName/Password is wrong?");
                    token = accountInfo.getTwitterAccessToken();
                    tokenSecret = accountInfo.getTwitterAccessTokenSecret();
                }

                if (Utils.isEmpty(email)) {
                    email = accountInfo.getEmail();
                }

                boolean result = false;
                if (null != accessToken) {
                    result = Sqlite.getInstance().updateUser(username, password,
                            token,
                            tokenSecret, email, CONSUMER_KEY, CONSUMER_SECRET);
                    if (result) {
                        out.println("Update success.");
                    } else {
                        out.println("Update failed.");
                    }
                } else {
                    out.println("Can't get the Twitter Access Token,maybe your Twitter UserName/Password is wrong?");
                }

            } else {
                out.println("Your UserName or Password is not correct,please check it and try again.");
            }

        }

    }

    private void sendUpdateHtml() {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Update</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Update</h3>");

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
        out.print("Update\" ");
        out.println("method=POST>");
        out.println("User Name:");
        out.println("<input type=text size=20 name=user_name>");
        // out.println("<br>");
        out.println("Password:");
        out.println("<input type=text size=20 name=user_password>");
        // out.println("<br>");
        out.println("Your new Email:");
        out.println("<input type=text size=20 name=user_email>");
        out.println("<br>");
        out.println("Your new Twitter User Name:");
        out.println("<input type=text size=20 name=user_twitter_user_name>");
        // out.println("<br>");
        out.println("Your new Twitter Password:");
        out.println("<input type=text size=20 name=user_twitter_password>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}