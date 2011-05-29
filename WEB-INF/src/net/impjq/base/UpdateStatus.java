package net.impjq.base;

import javax.servlet.http.HttpServlet;

/**
 * Update Status interface
 * @author pjq
 *
 */
public abstract class UpdateStatus extends HttpServlet {
	public abstract void updateStatus(String userName,String password,String message);
}
