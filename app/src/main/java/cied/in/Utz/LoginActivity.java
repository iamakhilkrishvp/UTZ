package cied.in.Utz;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.roger.catloadinglibrary.CatLoadingView;

import org.json.JSONException;
import org.json.JSONObject;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.URL;
import cied.in.Utz.SharedPreferance.SessionManager;

public class LoginActivity extends AppCompatActivity {
    TextView text1, text2, forgotText, loginText;
    EditText usernameText, passwordText;
    Typeface typeface;
    JSONObject jsonObject;
    SessionManager sessionManager;
    URL url;
    UtzDatabase database;
    String loginUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        forgotText = (TextView) findViewById(R.id.forgotText);
        loginText = (TextView) findViewById(R.id.loginText);
        usernameText = (EditText) findViewById(R.id.userNameText);
        passwordText = (EditText) findViewById(R.id.passwordtext);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");

        text1.setTypeface(typeface);
        text2.setTypeface(typeface);
        forgotText.setTypeface(typeface);
        loginText.setTypeface(typeface);
        usernameText.setTypeface(typeface);
        passwordText.setTypeface(typeface);
        sessionManager = new SessionManager(this);
        database = new UtzDatabase(this);
        database.deleteCriterionTable();
        database.deleteChapterTable();
        database.deleteVersionTable();
        database.deleteBlockTable();
        database.deleteInspectionYearTable();
        database.deletecommonAuditDetailsTable();
        database.deleteSubAuditDetailsTable();
        database.deleteAuditCriterionComplianceTable();
        database.deleteAuditorsTable();
        database.deleteFileAttachmentTable();
        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forgottenPasswordPopup();
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernameText.getText().toString().equals("") || passwordText.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Username or Password incorrect ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", usernameText.getText().toString());
                        jsonObject.put("password", passwordText.getText().toString());
                        loginRequest();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
    public void forgottenPasswordPopup() {
        final Dialog dialogbox = new Dialog(LoginActivity.this);
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialogbox.setContentView(R.layout.forgotten_popup);

        final TextView textView = (TextView) dialogbox.findViewById(R.id.textView42);
        final TextView submitButton= (TextView) dialogbox.findViewById(R.id.textView41);
        final EditText editText = (EditText) dialogbox.findViewById(R.id.editText2);
        submitButton.setTypeface(typeface);
        textView.setTypeface(typeface);
        editText.setTypeface(typeface);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"not a valid username",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username",editText.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    forgottenPasswordRequest(jsonObject);
                }
                dialogbox.dismiss();
            }
        });
        dialogbox.show();
    }

    public void loginRequest() {

        loginUrl = url.loginUrl;
        final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this,R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Signing...");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(loginUrl, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response ..", response.toString());
                        pDialog.dismiss();
                        try {
                            sessionManager.createLoginSession(response.getString("access_token"),
                                    response.getString("user_id"),response.getString("user_type"),
                                    response.getString("name"),response.getString("email"));
                            sessionManager.setUserLogin("true");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    pDialog.dismiss();
                    showInternetDialog();
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof AuthFailureError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext()," Username or Password was incorrect..",Toast.LENGTH_SHORT).show();;
                } else if (error instanceof ServerError) {
                    pDialog.dismiss();
                    Log.e("ServerError>>>>>>>>>", "ServerError.......");
                } else if (error instanceof NetworkError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    pDialog.dismiss();
                    Log.e("ParseError>>>>>>>>>", "ParseError.......");
                            /*finish();
                            startActivity(getIntent());*/
                }else if (error instanceof TimeoutError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Time out",Toast.LENGTH_SHORT).show();
                }
                else {
                    pDialog.dismiss();
                }

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);

    }
    public void forgottenPasswordRequest(JSONObject jsonObject) {

        String forgottenPasswordUrl = url.forgottenPasswordUrl;
        final ProgressDialog pDialog = new ProgressDialog(LoginActivity.this,R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(forgottenPasswordUrl, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response ..", response.toString());
                        pDialog.dismiss();
                        try {
                            confirmPasswordPopup(response.getString("email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof AuthFailureError) {
                    pDialog.dismiss();
                    Log.e("AuthFailureError", "AuthFailureError.......");
                } else if (error instanceof ServerError) {
                    pDialog.dismiss();
                    Log.e("ServerError>>>>>>>>>", "ServerError.......");
                } else if (error instanceof NetworkError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    pDialog.dismiss();
                    Log.e("ParseError>>>>>>>>>", "ParseError.......");
                            /*finish();
                            startActivity(getIntent());*/
                }else if (error instanceof TimeoutError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Time out",Toast.LENGTH_SHORT).show();
                }

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);

    }
    private void showInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        String message = "<font color=#000000>Sorry. we couldn't connect to internet, You<b> won't be logged in </b> without internet !!!</font> ";
        builder.setTitle("No internet Connection!!!");
        builder.setMessage(Html.fromHtml(message));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void confirmPasswordPopup(String emailId) {
        final Dialog dialogbox = new Dialog(LoginActivity.this);
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialogbox.setContentView(R.layout.confim_popup);
        dialogbox.setCancelable(false);

        final TextView textView = (TextView) dialogbox.findViewById(R.id.textView51);
        final TextView submitButton= (TextView) dialogbox.findViewById(R.id.textView53);
        final TextView messageText= (TextView) dialogbox.findViewById(R.id.textView52);
        submitButton.setTypeface(typeface);
        textView.setTypeface(typeface);
        messageText.setTypeface(typeface);
        String message = "<font color=#40000000>We have sent you a password reset link.Please check your email  </font><font color=#015877> "+emailId+"</font><font color=#40000000>  for further instructions.Have a great day! </font>";
        messageText.setText(Html.fromHtml(message));
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogbox.dismiss();
            }
        });

        dialogbox.show();

    }





}
