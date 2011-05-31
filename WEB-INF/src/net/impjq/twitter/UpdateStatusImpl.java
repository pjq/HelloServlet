
package net.impjq.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.impjq.account.AccountInfo;
import net.impjq.base.CommonParamString;
import net.impjq.base.UpdateStatus;
import net.impjq.database.Sqlite;
import net.impjq.util.Utils;

public class UpdateStatusImpl extends UpdateStatus {
    public static final String CONSUMER_KEY = "Fa0VtxzeoxV7OuktLMrVLw";
    public static final String CONSUMER_SECRET = "6X8el11OSkrnpeeziXeBNJbf1BBDRmBgFB0LNn7dY";

    public static final String ACCESS_TOKEN = "52646242-mgwUKKcx9AjEzMmnwHs8aQ6SQeSCa2plg2PU8zeDu";
    public static final String ACCESS_TOKEN_SECRET = "DlyuSJyvugoMcgLnDV98vxJSjWFXEfmxkAZvMOgCHo";

    public static Status updateStatus(PrintWriter out, String message, AccountInfo accountInfo) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(accountInfo.getTwitterAccessToken())
                .setOAuthAccessTokenSecret(accountInfo.getTwitterAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Status status = null;
        try {
            status = twitter.updateStatus(message);

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.println(e.toString());
        }
        return status;
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 4979679200836047678L;

    @Override
    public void updateStatus(String userName, String password, String message) {
        // TODO Auto-generated method stub
        // TwitterAuth.getAccessToken();
        // updateStatus(message);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // resp.setCharacterEncoding("UTF-8");
        // Enumeration<String> params = req.getParameterNames();

        String request = Utils.readFromInputStream(req.getInputStream());

        // String chinese="中文测试，Chinese  Test";
        // out.println(chinese);

        HashMap<String, String> hashMap = parserPostParameters(request);
        int size = hashMap.size();
        out.println("parameters size=" + size);

        Iterator<Entry<String, String>> iterator = hashMap.entrySet()
                .iterator();
        out.println("Your request:");
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();

            out.println(key + "=" + value);
        }

        String userName = hashMap.get(CommonParamString.PARAM_USERNAME);
        String password = hashMap.get(CommonParamString.PARAM_PASSWORD);
        String message = hashMap.get(CommonParamString.PARAM_MESSAGE);

        // updateStatus(userName, password, message);
        if (null == message) {
            message = "You message is null,use this default message";
        }

        AccountInfo accountInfo = Sqlite.getInstance().queryAccountInfo(userName);
        out.print(accountInfo.toString());

        if (null == userName || null == password) {
            out.println("Your username or password is null.\nIf need help,please contact pengjianqing@gmail.com");
            return;
        }

        if (checkPassword(userName, accountInfo)) {
            out.println("Twitter Update Result:");
            Status status = updateStatus(out, message, accountInfo);
            if (null != status) {
                String st = "Update status:" + status.getText() + " success,the status id:"
                        + status.getId();
                out.println(st);
            } else {
                out.println("The response status is null,update failed");
            }
        } else {
            out.println("Your username or password is wrong.\nIf need help,please contact pengjianqing@gmail.com");
        }

        // Enumeration<String> en = req.getParameterNames();
        //
        // while (en.hasMoreElements()) {
        // String name = (String) en.nextElement();
        // String value = req.getParameter(name);
        // out.println(name + " = " + value);
        // }
    }

    /**
     * Check whether the password is right.
     * 
     * @param username
     * @param password
     * @return true if the username and password is right.
     */
    private boolean checkPassword(String password, AccountInfo accountInfo) {
        boolean matched = false;

        if (password.equals(accountInfo.getPassword())) {
            matched = true;
        } else {
            matched = false;
        }

        return matched;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doPost(req, resp);
        doGet(req, resp);
    }

    public String getStream(HttpServletRequest request) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request
                .getInputStream(), "UTF-8"));
        StringBuffer ReString = new StringBuffer();
        String tmp = "";
        while (true) {
            tmp = br.readLine();
            if (tmp == null)
                break;
            else
                ReString.append(tmp);
        }
        return ReString.toString();
    }

    // public String urlDecode(String str) {
    // String result = str;
    // try {
    // result = URLDecoder.decode(str, "UTF-8");
    // } catch (UnsupportedEncodingException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // return result;
    // }

}
