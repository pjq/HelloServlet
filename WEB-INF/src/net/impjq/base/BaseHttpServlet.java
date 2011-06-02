
package net.impjq.base;

import net.impjq.account.AccountInfo;
import net.impjq.database.Sqlite;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.BasicAuthorization;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseHttpServlet extends HttpServlet {
    public static final String CONSUMER_KEY = "Fa0VtxzeoxV7OuktLMrVLw";
    public static final String CONSUMER_SECRET = "6X8el11OSkrnpeeziXeBNJbf1BBDRmBgFB0LNn7dY";

    /**
     * 
     */
    private static final long serialVersionUID = -7088836496424042711L;
    protected PrintWriter mPrintWriter;
    protected PrintWriter out;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        // TODO Auto-generated method stub
        resp.setContentType("text/html;charset=UTF-8");
        out = resp.getWriter();
        mPrintWriter = out;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doPost(req, resp);
        doGet(req, resp);
    }

    /**
     * Get the AccessToken use XAuth with the twitter userName and password.
     * 
     * @param userName
     * @param password
     */
    public AccessToken getXAuthAccessToken(String userName, String password) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance(new BasicAuthorization(userName, password));
        AccessToken accessToken = null;
        try {
            accessToken = twitter.getOAuthAccessToken();
            String token = accessToken.getToken();
            String secret = accessToken.getTokenSecret();
            String user = accessToken.getScreenName();
            out.println("Access Token=" + token + ",Access Token Secret=" + secret
                    + ",your user name="
                    + user);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.println(e.getMessage());
        }

        return accessToken;
    }

    /**
     * Check whether the password is right.
     * 
     * @param username
     * @param password
     * @return true if the username and password is right.
     */
    protected boolean checkPassword(String password, AccountInfo accountInfo) {
        boolean matched = false;

        if (password.contains(accountInfo.getPassword())) {
            matched = true;
        } else {
            matched = false;
        }

        return matched;
    }

    protected AccountInfo getAccountInfo(String userName) {
        AccountInfo accountInfo = Sqlite.getInstance().queryAccountInfo(userName);
        return accountInfo;
    }

}