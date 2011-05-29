package net.impjq.base;

import java.util.HashMap;

import javax.servlet.http.HttpServlet;

/**
 * Update Status interface
 * 
 * @author pjq
 * 
 */
public abstract class UpdateStatus extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -835511669272745216L;

	public abstract void updateStatus(String userName, String password,
			String message);

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
			if (map.length!=2) {
				continue;
			}
			hashMap.put(map[0], map[1]);
		}

		return hashMap;
	}
}
