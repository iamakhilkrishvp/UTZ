package cied.in.Utz.SharedPreferance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.HashMap;

import cied.in.Utz.LoginActivity;


/**
 * Created by cied on 24/8/16.
 */
public class SessionManager {

    SharedPreferences pref;
    SharedPreferences prefid;

    Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "CastorPref";

    static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ACCESS_TOKEN = "access_token";
//    public static final String KEY_ISLOGIN = "IsloggedIn";
    public static final String KEY_LOGIN_STATUS = "logginStatus";
    public static final String KEY_USERTYPE= "user_type";
    public static final String KEY_USERID = "user_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_AUDITID = "auditId";
    public static final String KEY_SUBAUDITID = "subAuditId";
    public static final String KEY_POSITION = "position";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_CID = "cid";


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.commit();
    }

    public void createLoginSession(String access_token, String admin_id, String user_type, String username, String email){

        editor.putBoolean(IS_LOGIN, true);

        Log.e("admin_id " , admin_id);
        editor.putString(KEY_ACCESS_TOKEN, access_token);
        editor.putString(KEY_USERTYPE, user_type);
        editor.putString(KEY_USERID, admin_id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_USER_EMAIL, email);


        editor.commit();
    }
    public void setAuditId(String auditId)
    {
        Log.e("audit id " , ""+auditId);
        editor.putString(KEY_AUDITID,auditId);
        editor.commit();
    }

    public void setUserLogin(String userLogin)
    {
        editor.putString(KEY_LOGIN_STATUS, userLogin);

        editor.commit();
    }

   /* public void setLogin(String userLogin)
    {
        editor.putString(KEY_ISLOGIN, userLogin);

        editor.commit();
    }*/
    public void setcriterionId(String criterionId)
    {
        Log.e("audit id " , ""+criterionId);
        editor.putString(KEY_CID,criterionId);
        editor.commit();
    }
    public void setSubAuditId(String asubAditId)
    {
        Log.e("asubAditId  " , ""+asubAditId);
        editor.putString(KEY_SUBAUDITID,asubAditId);
        editor.commit();
    }
    public void setPosition(String position)
    {
        Log.e("asubAditId  " , ""+position);
        editor.putString(KEY_POSITION,position);
        editor.commit();
    }

    /**
     * Check login method wil check user login status If false it will redirect
     * user to login page Else won't do anything
     * */
    public boolean checkLogin() {

        boolean status = false;
        if (this.isLoggedIn()) {

            status = true;
        } else {
            status = false;

        }

        return status;
    }

    public boolean login() {
        return this.isLoggedIn();

    }

    public HashMap<String, String> getUserDetails() {

        HashMap<String, String> session = new HashMap<String, String>();
        session.put(KEY_ACCESS_TOKEN, pref.getString(KEY_ACCESS_TOKEN, null));
        session.put(KEY_USERTYPE, pref.getString(KEY_USERTYPE, null));
        session.put(KEY_USERID, pref.getString(KEY_USERID, null));
        session.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));
        session.put(KEY_USER_EMAIL, pref.getString(KEY_USER_EMAIL, null));

        return session;
    }
    public HashMap<String, String> getAuditId() {

        HashMap<String, String> session = new HashMap<String, String>();
        session.put(KEY_AUDITID, pref.getString(KEY_AUDITID, null));

        return session;
    }
    public HashMap<String, String> getSubAuditId() {

        HashMap<String, String> session = new HashMap<String, String>();
        session.put(KEY_SUBAUDITID, pref.getString(KEY_SUBAUDITID, null));

        return session;
    }
    public HashMap<String, String> getPosition() {

        HashMap<String, String> session = new HashMap<String, String>();
        session.put(KEY_POSITION, pref.getString(KEY_POSITION, "1"));

        return session;
    }
    public HashMap<String, String> getUserLogin() {

        HashMap<String, String> session = new HashMap<String, String>();

        session.put(KEY_LOGIN_STATUS, pref.getString(KEY_LOGIN_STATUS, "false"));

        return session;
    }
    /*public HashMap<String, String> getLogin() {

        HashMap<String, String> session = new HashMap<String, String>();

        session.put(KEY_ISLOGIN, pref.getString(KEY_ISLOGIN, "false"));

        return session;
    }*/

    public void logoutUser() {

        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

}