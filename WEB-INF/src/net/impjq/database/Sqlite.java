
package net.impjq.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlite implements DataBaseInterface {
    public static final String DATABASE_NAME = "servlet.db";

    public static final String TABLE_ACCOUNT_NAME = "account";

    public static String SQL_CREATE_TABLE = "CREATE TABLE [" +
            TABLE_ACCOUNT_NAME + "] ([" +
            Columns.Account.COLUMNS_USER_ID + "]AUTOINC,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_NAME + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_PASSWORD + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_EMAIL + "] TEXT,  [" +
            Columns.Account.ACCOUNT_COLUMNS_USER_CREATE_AT + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_TWITTER_CONSUMER_KEY + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_TWITTER_CONSUMER_SECRET + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_TWITTER_ACCESS_TOKEN + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_TWITTER_ACCESS_TOKEN_SECRET + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_DISPLAY_NAME + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_FACEBOOK_CONSUMER_KEY + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_FACEBOOK_CONSUMER_SECRET + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_FACEBOOK_ACCESS_TOKEN + "] TEXT,[" +
            Columns.Account.ACCOUNT_COLUMNS_USER_FACEBOOK_ACCESS_TOKEN_SECRET + "] TEXT)";

    public static String SQL_CREATE_TABLE_TMP = "CREATE TABLE [table_tmp]([user_id] AUTOINC,[user_name] TEXT,[user_email] TEXT)";

    private static Sqlite mInstance;

    public static Sqlite getInstance() {
        if (null == mInstance) {
            mInstance = new Sqlite();
        }

        return mInstance;
    }

    @Override
    public boolean init() {
        // TODO Auto-generated method stub
        Connection connection = DataManager.getInstance().getConnection();
        Statement statement;
        boolean result = false;
        try {
            statement = connection.createStatement();
            result = statement.execute(SQL_CREATE_TABLE);
            result = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    @Override
    public void query() {
        // TODO Auto-generated method stub

    }

    /**
     * Add user
     * 
     * @param username
     * @param password
     * @param twitterAccessToken
     * @param twitterAccessTokenSecret
     * @return true if add success,false if it is already existed.
     */
    public boolean addUser(String username, String password, String twitterAccessToken,
            String twitterAccessTokenSecret,String email) {
        String addUserSQL = "INSERT INTO " + TABLE_ACCOUNT_NAME + " ("
                + Columns.Account.ACCOUNT_COLUMNS_USER_NAME + ","
                + Columns.Account.ACCOUNT_COLUMNS_USER_PASSWORD + ","
                + Columns.Account.ACCOUNT_COLUMNS_USER_TWITTER_ACCESS_TOKEN + ","
                + Columns.Account.ACCOUNT_COLUMNS_USER_TWITTER_ACCESS_TOKEN_SECRET + ","
                + Columns.Account.ACCOUNT_COLUMNS_USER_EMAIL+ " "
                + ") values('"
                + username + "','"
                + password + "','"
                + twitterAccessToken + "','"
                + twitterAccessTokenSecret + "','"
                + email + "'"
                + ");";

        boolean result = false;
        if (!isUserExist(username)) {
            result = executeSql(addUserSQL);
        }

        return result;
    }

    private boolean isUserExist(String username) {
        String queryUser = "SELECT (" + Columns.Account.ACCOUNT_COLUMNS_USER_NAME + ") from "
                + TABLE_ACCOUNT_NAME + " where ( " + Columns.Account.ACCOUNT_COLUMNS_USER_NAME + " = '" + username + "' )";
        boolean existed = false;

        ResultSet resultSet = executeSqlQuery(queryUser);
        try {
            boolean moreRecords = resultSet.next();
            existed = moreRecords;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            existed = false;
        }

        return existed;
    }

    private boolean executeSql(String sql) {
        Connection connection = DataManager.getInstance().getConnection();
        Statement statement;
        boolean result = false;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            result = true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = false;
        }

        return result;
    }

    private ResultSet executeSqlQuery(String sql) {
        Connection connection = DataManager.getInstance().getConnection();
        Statement statement;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
}
