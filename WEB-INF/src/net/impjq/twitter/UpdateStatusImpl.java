package net.impjq.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.impjq.base.CommonParamString;
import net.impjq.base.UpdateStatus;
import net.impjq.util.Utils;

public class UpdateStatusImpl extends UpdateStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4979679200836047678L;

	@Override
	public void updateStatus(String userName, String password, String message) {
		// TODO Auto-generated method stub
		//TwitterAuth.getAccessToken();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("Twitter Update");

		// Enumeration<String> params = req.getParameterNames();

		String request = Utils.readFromInputStream(req.getInputStream());
		// out.println("resquest=" + request);

		HashMap<String, String> hashMap = parserPostParameters(request);
		int size = hashMap.size();
		out.println("parameters size="+size);

		Iterator<Entry<String, String>> iterator = hashMap.entrySet()
				.iterator();

		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();

			out.println(key + "=" + value);
		}
		
		String userName=hashMap.get(CommonParamString.PARAM_USERNAME);
		String password=hashMap.get(CommonParamString.PARAM_PASSWORD);
		String message=hashMap.get(CommonParamString.PARAM_MESSAGE);
		
		
		updateStatus(userName, password, message);

		// Enumeration<String> en = req.getParameterNames();
		//
		// while (en.hasMoreElements()) {
		// String name = (String) en.nextElement();
		// String value = req.getParameter(name);
		// out.println(name + " = " + value);
		// }
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

}
