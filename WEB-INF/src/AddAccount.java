import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAccount extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		DataManager dataManager = DataManager.getInstance();

		long id = System.currentTimeMillis() % 3;

		boolean result = dataManager.addAccount("user" + id, "123", "user" + id
				+ "@gmail.com", "user" + id);
		out.println("Result=" + result);
	}

}
