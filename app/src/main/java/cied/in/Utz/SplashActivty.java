package cied.in.Utz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import cied.in.Utz.SharedPreferance.SessionManager;

public class SplashActivty extends AppCompatActivity {

    SessionManager manager;
    String userlogin = "false";
    TextView text1,text2;
    private static int SPLASH_TIME_OUT = 2000;
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);

        manager = new SessionManager(this);
        HashMap<String, String> user = manager.getUserLogin();;
        userlogin = user.get(SessionManager.KEY_LOGIN_STATUS);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2) ;
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        text1.setTypeface(typeface);
        text2.setTypeface(typeface);
        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {
                if(userlogin.equals("true"))
                {
                    Intent i = new Intent(SplashActivty.this, HomePageActivity.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(SplashActivty.this, LoginActivity.class);
                    startActivity(i);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);


    }

}
