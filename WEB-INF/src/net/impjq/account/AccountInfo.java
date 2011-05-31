
package net.impjq.account;

public class AccountInfo {
    private String mUserName;
    private String mPassword;
    private String mEmail;
    private String mTwitterAccessToken;
    private String mTwitterAccessTokenSecret;
    private String mFacebookAccessToken;
    private String mFacebookAccessTokenSecret;
    private String mDisplayName;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getTwitterAccessToken() {
        return mTwitterAccessToken;
    }

    public void setTwitterAccessToken(String mTwitterAccessToken) {
        this.mTwitterAccessToken = mTwitterAccessToken;
    }

    public String getTwitterAccessTokenSecret() {
        return mTwitterAccessTokenSecret;
    }

    public void setTwitterAccessTokenSecret(String mTwitterAccessTokenSecret) {
        this.mTwitterAccessTokenSecret = mTwitterAccessTokenSecret;
    }

    public String getFacebookAccessToken() {
        return mFacebookAccessToken;
    }

    public void setFacebookAccessToken(String mFacebookAccessToken) {
        this.mFacebookAccessToken = mFacebookAccessToken;
    }

    public String getFacebookAccessTokenSecret() {
        return mFacebookAccessTokenSecret;
    }

    public void setFacebookAccessTokenSecret(String mFacebookAccessTokenSecret) {
        this.mFacebookAccessTokenSecret = mFacebookAccessTokenSecret;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        this.mDisplayName = displayName;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String string = "userName=" + mUserName + ",userEmail=" + mEmail + ",password=" + mPassword
                + ",displayName=" + mDisplayName
                + ",twitterAccessToken=" + mTwitterAccessToken + ",twitterAccessTokenSecret"
                + mTwitterAccessTokenSecret + ",facebookAccessToken=" + mFacebookAccessToken
                + ",facebookAccessTokenSecret=" + mFacebookAccessTokenSecret;
        return string;
    }
}
