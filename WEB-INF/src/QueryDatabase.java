import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryDatabase extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = resp.getWriter();

		DataManager dataManager = DataManager.getInstance();
		Connection connection = dataManager.getConnection();

		String sqlString = "SELECT *  from account";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);
			boolean moreRecords = resultSet.next();

			if (!moreRecords) {
				out.println("no records in db");
				return;
			}

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();

			out.println("columnCount="+columnCount+'\n');
			Vector<String> columnHeads = new Vector();
			for (int i = 1; i <=columnCount; i++) {
				columnHeads.addElement(resultSetMetaData.getColumnName(i));
				out.print(resultSetMetaData.getColumnName(i) + " ");
			}

			do {
				out.println('\n');
				for (String v : columnHeads) {
					String val = resultSet.getString(v);
					out.print(v+"="+val + " ");
				}
				//out.println('\n');

			} while (resultSet.next());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		
		try {
			DataManager.getInstance().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
