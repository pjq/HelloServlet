package net.impjq.twitter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.impjq.base.UpdateStatus;

public class UpdateStatusImpl extends UpdateStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4979679200836047678L;

	@Override
	public void updateStatus(String userName, String password, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("Twitter Update");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.doPost(req, resp);
		doGet(req, resp);
	}

}
