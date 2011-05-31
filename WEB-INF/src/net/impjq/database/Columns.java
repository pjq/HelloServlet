
package net.impjq.database;

public class Columns {

    class BaseColumns {
        public static final String COLUMNS_USER_ID = "user_id";
    }

    class Account extends BaseColumns {
        public static final String ACCOUNT_COLUMNS_USER_NAME = "user_name";
        public static final String ACCOUNT_COLUMNS_USER_DISPLAY_NAME = "user_display_name";
        public static final String ACCOUNT_COLUMNS_USER_PASSWORD = "user_password";
        public static final String ACCOUNT_COLUMNS_USER_EMAIL = "user_email";
        public static final String ACCOUNT_COLUMNS_USER_CREATE_AT = "user_create_at";
        public static final String ACCOUNT_COLUMNS_USER_TWITTER_CONSUMER_KEY = "user_twitter_consumer_key";
        public static final String ACCOUNT_COLUMNS_USER_TWITTER_CONSUMER_SECRET = "user_twitter_consumer_secret";
        public static final String ACCOUNT_COLUMNS_USER_TWITTER_ACCESS_TOKEN = "user_twitter_access_token";
        public static final String ACCOUNT_COLUMNS_USER_TWITTER_ACCESS_TOKEN_SECRET = "user_twitter_access_token_secret";
        public static final String ACCOUNT_COLUMNS_USER_FACEBOOK_CONSUMER_KEY = "user_facebook_consumer_key";
        public static final String ACCOUNT_COLUMNS_USER_FACEBOOK_CONSUMER_SECRET = "user_facebook_consumer_secret";
        public static final String ACCOUNT_COLUMNS_USER_FACEBOOK_ACCESS_TOKEN = "user_facebook_access_token";
        public static final String ACCOUNT_COLUMNS_USER_FACEBOOK_ACCESS_TOKEN_SECRET = "user_facebook_access_token_secret";
    }

}
