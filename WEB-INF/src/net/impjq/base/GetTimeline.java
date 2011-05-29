package net.impjq.base;

import javax.servlet.http.HttpServlet;

/**
 * Get the Timeline interface
 * @author pjq
 *
 */
public abstract class GetTimeline extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5510470811990058855L;

	abstract void getTimeline();
}
