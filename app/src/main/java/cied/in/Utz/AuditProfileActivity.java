package cied.in.Utz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cied.in.Utz.Model.URL;
import cied.in.Utz.SharedPreferance.SessionManager;
import de.hdodenhof.circleimageview.CircleImageView;

public class AuditProfileActivity extends AppCompatActivity {

    EditText educationText,experienceText, emailText,
            addressText,landlineText,mobileText,certificationBodyText;
    TextView nameText,submitButton,certificationBoardText,headerText;
    TextView text1,text2,text3,text4,text5,text6,text7,text8;
    String getProfileUrl,postProfileUrl,accessToken;
    RelativeLayout editLayout;
    URL url;
    int auditorId;
    SessionManager manager;
    Typeface typeface;
    CircleImageView imageView;
    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backIcon = (ImageView) toolbar.findViewById(R.id.imageView7);
        nameText = (TextView) findViewById(R.id.auditNameText);
        educationText = (EditText) findViewById(R.id.educationText);
        experienceText = (EditText) findViewById(R.id.experienceText);
        emailText = (EditText) findViewById(R.id.emailText);
        certificationBodyText = (EditText) findViewById(R.id.certificationBodyText);
        certificationBoardText = (TextView) findViewById(R.id.certificationBoardText);
        addressText = (EditText) findViewById(R.id.addressText);
        landlineText = (EditText) findViewById(R.id.landLineText);
        mobileText = (EditText) findViewById(R.id.mobileNumberText);
        editLayout = (RelativeLayout) toolbar.findViewById(R.id.editlayout);
        headerText = (TextView) toolbar.findViewById(R.id.textView14) ;
        imageView = (CircleImageView) findViewById(R.id.profileImage);
        submitButton = (TextView) findViewById(R.id.submitBtn);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        text8 = (TextView) toolbar.findViewById(R.id.text2);

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        nameText.setTypeface(typeface);
        educationText.setTypeface(typeface);
        experienceText.setTypeface(typeface);
        emailText.setTypeface(typeface);
        certificationBoardText.setTypeface(typeface);
        certificationBodyText.setTypeface(typeface);
        addressText.setTypeface(typeface);
        landlineText.setTypeface(typeface);
        mobileText.setTypeface(typeface);
        text1.setTypeface(typeface);
        text2.setTypeface(typeface);
        text3.setTypeface(typeface);
        text4.setTypeface(typeface);
        text5.setTypeface(typeface);
        text6.setTypeface(typeface);
        text7.setTypeface(typeface);
        text8.setTypeface(typeface);

        educationText.setFocusable(false);
        experienceText.setFocusable(false);
        emailText.setFocusable(false);
        certificationBodyText.setFocusable(false);
        addressText.setFocusable(false);
        landlineText.setFocusable(false);
        mobileText.setFocusable(false);

        manager = new SessionManager(this);
        final HashMap<String,String> userDetails = manager.getUserDetails();
        accessToken = userDetails.get(SessionManager.KEY_ACCESS_TOKEN);
        auditorId  = Integer.parseInt(userDetails.get(SessionManager.KEY_USERID));

        getProfileDetails();
        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                headerText.setText("Edit Profile");
                submitButton.setVisibility(View.VISIBLE);
                educationText.setFocusable(true);
                educationText.setFocusableInTouchMode(true);
                educationText.setClickable(true);
                experienceText.setFocusable(true);
                experienceText.setFocusableInTouchMode(true);
                experienceText.setClickable(true);
                emailText.setFocusable(true);
                emailText.setFocusableInTouchMode(true);
                emailText.setClickable(true);
               /* certificationBodyText.setFocusable(true);
                certificationBodyText.setFocusableInTouchMode(true);
                certificationBodyText.setClickable(true);*/
                addressText.setFocusable(true);
                addressText.setFocusableInTouchMode(true);
                addressText.setClickable(true);
                landlineText.setFocusable(true);
                landlineText.setFocusableInTouchMode(true);
                landlineText.setClickable(true);
                mobileText.setFocusable(true);
                mobileText.setFocusableInTouchMode(true);
                mobileText.setClickable(true);
                addressText.requestFocus();
                addressText.requestFocusFromTouch();
                addressText.setSelection(addressText.length());

            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {

                    jsonObject.put("education",educationText.getText().toString());
                    jsonObject.put("experience",experienceText.getText().toString());
                    jsonObject.put("training_details", emailText.getText().toString());
                    jsonObject.put("certification_body_name",certificationBodyText.getText().toString());
                    jsonObject.put("address",addressText.getText().toString());
                    jsonObject.put("landline",landlineText.getText().toString());
                    jsonObject.put("mobile",mobileText.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("jsonObject" ,jsonObject.toString());
                postProfileRequest(jsonObject);
            }
        });
    }

    public void getProfileDetails() {
        getProfileUrl = url.getProfileUrl +auditorId+"/get/";
        final ProgressDialog pDialog = new ProgressDialog(AuditProfileActivity.this,R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Fetching profile details..");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,getProfileUrl,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                int auditid;
                pDialog.dismiss();
                Log.e("get profile Response ",response.toString());
                try {

                    nameText.setText(response.getString("auditor_username"));
                    educationText.setText(response.getString("education"));
                    experienceText.setText(response.getString("experience"));
                    emailText.setText(response.getString("email"));
                    certificationBoardText.setText(response.getString("certification_board"));
                    certificationBodyText.setText(response.getString("certification_body_name"));
                    addressText.setText(response.getString("address"));
                    landlineText.setText(response.getString("landline"));
                    mobileText.setText(response.getString("mobile"));
                   /* Picasso.with(AuditProfileActivty.this)
                            .load("http://4.bp.blogspot.com/-8RE5IWXFmmA/T9TQNJr6f1I/AAAAAAAABDw/FC7jbgZocys/s1600/fb-profile.jpg")
                            .into(imageView);*/

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
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("access-token", accessToken);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);

    }

    public void postProfileRequest(JSONObject jsonObject) {

        postProfileUrl = url.getProfileUrl +auditorId+"/edit/";
        final ProgressDialog pDialog = new ProgressDialog(AuditProfileActivity.this,R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(postProfileUrl, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("response ..", response.toString());
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Your profile has been successfully updated ",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AuditProfileActivity.this, HomePageActivity.class);
                        startActivity(intent);
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
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put(ec2-54-161-85-113.compute-1.amazonaws.com"Content-Type", "application/json");
                headers.put("access-token", accessToken);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);

    }


}
