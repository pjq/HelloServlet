package net.impjq.base;

import net.impjq.account.AccountInfo;
import net.impjq.database.SqliteManager;
import net.impjq.util.Utils;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.BasicAuthorization;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
	protected HashMap<String, String> mRequestHashMap;
	private String mUserName;
	private String mPassword;
	private AccountInfo mAccountInfo;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		out = resp.getWriter();
		mPrintWriter = out;

		String request = Utils.readFromInputStream(req.getInputStream());

		mRequestHashMap = parserPostParameters(request);

		mUserName = mRequestHashMap.get(CommonParamString.PARAM_USERNAME);
		mPassword = mRequestHashMap.get(CommonParamString.PARAM_PASSWORD);

		Iterator<Entry<String, String>> iterator = mRequestHashMap.entrySet()
				.iterator();
		// out.println("Your request:"+request);
		// out.println("Your request:userName="+mUserName+",password="+mPassword);
		out.println("\nYour request:");
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();

			out.println(key + "=" + value);
		}

		if (isUserNameOrPasswordEmpty()) {
			mUserName = "pjq";
			mPassword = "123";
		}
		// mUserName = "pjq";
		// mPassword = "123";
		if (mPassword.contains("\n")) {
			mPassword = mPassword.replace("\n", "");
		}

		if (mUserName.contains("\n")) {
			mUserName = mUserName.replace("\n", "");
		}

		mAccountInfo = getAccountInfo(mUserName);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
		doGet(req, resp);
	}

	protected HashMap<String, String> getRequestHashMap() {
		return mRequestHashMap;
	}

	protected String getUserName() {
		return mUserName;
	}

	protected String getPassword() {
		return mPassword;
	}

	protected AccountInfo getAccountInfo() {
		return mAccountInfo;
	}

	protected boolean isPasswordMatched() {
		return mAccountInfo.isPasswordMatched(mPassword);
	}

	protected boolean isUserNameOrPasswordEmpty() {
		return Utils.isEmpty(mUserName) || Utils.isEmpty(mPassword);
	}

	/**
	 * Create the twitter instance,you can use it to do what you want.
	 * 
	 * @return Twitter instance.
	 */
	protected Twitter createTwitterInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(
				mAccountInfo.getTwitterConsumerKey()).setOAuthConsumerSecret(
				mAccountInfo.getTwitterConsumerSecret()).setOAuthAccessToken(
				mAccountInfo.getTwitterAccessToken())
				.setOAuthAccessTokenSecret(
						mAccountInfo.getTwitterAccessTokenSecret());
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}

	/**
	 * Get the AccessToken use xAuth with the twitter username and password.
	 * 
	 * @param userName
	 * @param password
	 */
	public AccessToken getXAuthAccessToken(String userName, String password) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY)
				.setOAuthConsumerSecret(CONSUMER_SECRET);

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance(new BasicAuthorization(userName,
				password));
		AccessToken accessToken = null;
		try {
			accessToken = twitter.getOAuthAccessToken();
			String token = accessToken.getToken();
			String secret = accessToken.getTokenSecret();
			String user = accessToken.getScreenName();
			out.println("Access Token=" + token + ",Access Token Secret="
					+ secret + ",your user name=" + user);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e.getMessage());
		}

		return accessToken;
	}

	protected AccountInfo getAccountInfo(String userName) {
		AccountInfo accountInfo = SqliteManager.getInstance().queryAccountInfo(
				userName);
		return accountInfo;
	}

	private String[] parser(String parameters) {
		String params[] = parameters.split("&");

		return params;
	}

	/**
	 * Parser the post parameters.
	 * 
	 * @param parameters
	 * @return
	 */
	protected HashMap<String, String> parserPostParameters(String parameters) {
		String[] params = parser(parameters);

		int length = params.length;

		if (length <= 0) {
			return null;
		}

		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (String p : params) {
			String[] map = p.split("=");
			if (map.length != 2) {
				continue;
			}
			hashMap.put(map[0], urlDecode(map[1]));
		}

		return hashMap;
	}

	public static String urlDecode(String str) {
		String result = str;
		try {
			result = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * PrintWriter the ResponseList
	 * 
	 * @param responseList
	 */
	protected void printResponseList(ResponseList<Status> responseList) {
		for (Status st : responseList) {
			out.println("[" + st.getUser().getName() + "]:" + st.getCreatedAt()
					+ "/" + st.getSource() + '\n' + st.getText());
			out.println("------------------");
		}
	}

}
