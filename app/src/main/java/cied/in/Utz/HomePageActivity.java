package cied.in.Utz;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.roger.catloadinglibrary.CatLoadingView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Fragments.AllAuditFragment;
import cied.in.Utz.Fragments.GroupAuditFragment;
import cied.in.Utz.Fragments.SingleAuditFragment;
import cied.in.Utz.Model.Items;
import cied.in.Utz.Model.URL;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ViewPager viewPager;
    FragmentManager myFragmentManager;
    FragmentTransaction myFragmentTransaction;
    private TabLayout tabLayout;
    Typeface typeface;
    private View navHeader;
    NavigationView navigationView;
    TextView tabOne,tabTwo,tabThree;
    String[] versionNamesArray;
    RecyclerView recyclerView;
    int id = 201,iteration;
    String auditId,getAuditDetailsUrl,accessToken,auditorId,userName,userEmail,userType;
    URL url;
    SessionManager sessionManager;
    UtzDatabase database ;
    ImageView refreshImage;
    TextView userNameText, userInintialText,userEmailText;
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> blockDetails = new ArrayList<Items>();
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> criteriaDetails = new ArrayList<Items>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sessionManager = new SessionManager(this);
        final HashMap<String,String> userDetails = sessionManager.getUserDetails();
        accessToken = userDetails.get(SessionManager.KEY_ACCESS_TOKEN);
        auditorId  = userDetails.get(SessionManager.KEY_USERID);
        userName  = userDetails.get(SessionManager.KEY_USERNAME);
        userEmail  = userDetails.get(SessionManager.KEY_USER_EMAIL);
        userType  = userDetails.get(SessionManager.KEY_USERTYPE);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        database = new UtzDatabase(this);
        refreshImage = (ImageView) toolbar.findViewById(R.id.imageView8);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        userInintialText = (TextView) navHeader.findViewById(R.id.profileInitial);
        userNameText = (TextView) navHeader.findViewById(R.id.profileName);
        userEmailText = (TextView) navHeader.findViewById(R.id.profileEmail);
        userNameText.setText(userName);
        userEmailText.setText(userEmail);
        userInintialText.setText(getUserInitial(userName));

        commonAuditDetails = (ArrayList<Items>)database.getCommonAuditTableDetails();
        if(commonAuditDetails.size() == 0)
        {
            getAuditDetailRequest();
        }
        else
        {
            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons();

        }
        refreshImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshAudit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(),AuditProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

            logoutRequest();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setupTabIcons() {

        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("All");
        tabOne.setTypeface(typeface);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Single");
        tabTwo.setTypeface(typeface);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Group");
        tabThree.setTypeface(typeface);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AllAuditFragment(), "All");
        adapter.addFrag(new SingleAuditFragment(), "Single");
        adapter.addFrag(new GroupAuditFragment(), "Group");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public String getUserInitial(String username) {
        String initial = "";
        String[] parts = username.split(" ");
        if (parts.length == 1)
        {
            initial = parts[0].substring(0,1);
        }
        else {
            initial = parts[0].substring(0,1)+""+ parts[1].substring(0,1);
        }
        return initial;
    }
    public void logoutRequest() {
        sessionManager.logoutUser();
        database.deleteCriterionTable();
        database.deleteChapterTable();
        database.deleteVersionTable();
        database.deleteBlockTable();
        database.deleteInspectionYearTable();
        database.deletecommonAuditDetailsTable();
        database.deleteSubAuditDetailsTable();
       // database.deleteAuditChapterComplianceTable();
        database.deleteAuditCriterionComplianceTable();
        database.deleteAuditorsTable();
        database.deleteFileAttachmentTable();
        String logOutUrl = url.logOutUrl;
        final ProgressDialog pDialog = new ProgressDialog(HomePageActivity.this,R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Signing out.");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,logOutUrl,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                pDialog.dismiss();
                Log.e("get logout Response ",response.toString());
                sessionManager.setUserLogin("false");
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
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
                //headers.put("Content-Type", "application/json");
                headers.put("access-token", accessToken);
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(req);

    }

    public void getAuditDetailRequest() {
        getAuditDetailsUrl = url.auditDetailsUrl +"?auditor_id="+auditorId;
        final ProgressDialog pDialog = new ProgressDialog(HomePageActivity.this,R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,getAuditDetailsUrl,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                int auditid;
                JSONObject mainObject;

                Log.e("get audit Response ",response.toString());


                try {
                    mainObject = response.getJSONObject("audit_data");
                    JSONArray yearArray = mainObject.getJSONArray("inspection_years");
                    for (int i=0; i<yearArray.length(); i++)
                    {
                        JSONObject jsonObject = yearArray.getJSONObject(i);
                        database.addtoInspectionYearTable(new Items(Integer.parseInt(jsonObject.getString
                                ("inspection_year_id")),jsonObject.getString("inspection_year_code")));
                    }

                    JSONArray versionArray = mainObject.getJSONArray("versions");
                    versionNamesArray = new String[versionArray.length()];
                    for (int i =0;i<versionArray.length();i++)
                    {
                        JSONObject jsonObject = versionArray.getJSONObject(i);
                        versionNamesArray[i] = jsonObject.getString("code_version");
                    }

                    JSONArray auditDataArray = mainObject.getJSONArray("audits");
                    for (int i = 0; i<auditDataArray.length(); i++)
                    {
                        JSONObject object = auditDataArray.getJSONObject(i);
                        JSONObject commonJsonObject = object.getJSONObject("common_audit_details");
                        auditid = Integer.parseInt(commonJsonObject.getString("audit_id"));

                        database.addtoCommonAuditDetailsTable(new Items(commonJsonObject.getString("audit_id"),commonJsonObject.getString("audit_name"),
                                commonJsonObject.getString("inspection_year"),commonJsonObject.getString("status"),commonJsonObject.getString("category")
                                ,commonJsonObject.getString("start_date"),commonJsonObject.getString("end_date"),commonJsonObject.getString("year"),
                                commonJsonObject.getString("overall_compliance"),commonJsonObject.getString("code_version")));
                        JSONArray auditorsDetailArray = commonJsonObject.getJSONArray("auditors_details");
                        for (int j = 0;j< auditorsDetailArray.length();j++)
                        {
                            JSONObject object1 = auditorsDetailArray.getJSONObject(j);
                            database.addtoAuditorsTable(new Items(auditid,object1.getString("id"),object1.getString("name")));
                        }
                        JSONArray subAuditArray = object.getJSONArray("sub_audits");
                        for (int k = 0; k<subAuditArray.length(); k++)
                        {
                            JSONObject jsonObject = subAuditArray.getJSONObject(k);


                            database.addtoSubAuditDetailsTable(new Items(auditid,jsonObject.getString("id"),jsonObject.getString("farm_id"),
                                    jsonObject.getString("farm_name"), jsonObject.getString("address"),jsonObject.getString("district"),
                                    jsonObject.getString("phone"),jsonObject.getString("country"), jsonObject.getString("city"),
                                    jsonObject.getString("status"),jsonObject.getString("type_of_operator"),jsonObject.getString("identification_number"),
                                    jsonObject.getString("temporary_female_workers"),jsonObject.getString("crops"),
                                    jsonObject.getString("compliance_percentage"),jsonObject.getString("contact_person_name"), jsonObject.getString
                                    ("other_certification_boards"),jsonObject.getString("type_of_process"),jsonObject.getString("is_certified_before"),
                                    jsonObject.getString("permanent_female_workers"), jsonObject.getString("total_production"), jsonObject.
                                    getString("permanent_male_workers") ,jsonObject.getString("total_area"),jsonObject.getString("legal_status"),
                                    jsonObject.getString("temporary_male_workers"), jsonObject.getString("total_workers_living_at_peak_season"),
                                    jsonObject.getString("area_under_utz")));
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(iteration<versionNamesArray.length)
                {
                    String versionName = versionNamesArray[iteration];
                    if(searchVersion(versionName))
                    {
                        pDialog.dismiss();
                    }
                    else {
                        pDialog.dismiss();
                        getCodeRequest(versionName);
                    }

                }
                else {

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


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
    public void refreshAudit()
    {
        database.deleteCriterionTable();
        database.deleteChapterTable();
        database.deleteVersionTable();
        database.deleteBlockTable();
        database.deleteInspectionYearTable();
        database.deletecommonAuditDetailsTable();
        database.deleteSubAuditDetailsTable();
      //  database.deleteAuditChapterComplianceTable();
        database.deleteAuditCriterionComplianceTable();
        database.deleteAuditorsTable();
        database.deleteFileAttachmentTable();
        getAuditDetailRequest();


    }
    public void getCodeRequest(final String versionName) {

        getAuditDetailsUrl = url.rootUrl +versionName+"/audit/get-sheet/?auditor_id="+auditorId;
        // getAuditDetailsUrl = url.codeDetailsUrl +versionName+"/audit/get-sheet/?auditor_id="+auditorId;
        final ProgressDialog dialog = new ProgressDialog(HomePageActivity.this,R.style.AppCompatAlertDialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Downloading Code version  "+versionName);
        dialog.show();

        // Log.e("Url",getAuditDetailsUrl);
        JsonArrayRequest req1 = new JsonArrayRequest(getAuditDetailsUrl,
                new Response.Listener<JSONArray>() {

                    int chapterId,criteriaId;
                    String position,description,inspectionYear,type;
                    @Override
                    public void onResponse(JSONArray response) {
                        int blockid,chapterId,criterionId;
                        Log.e("code response", response.toString());
                        for (int i =0;i<response.length(); i++)
                        {

                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                blockid = Integer.parseInt(jsonObject.getString("block_id"));
                                if(searchBlockId(blockid))
                                {
                                    JSONArray jsonArray = jsonObject.getJSONArray("chapter");

                                    for (int j=0; j<jsonArray.length(); j++)
                                    {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                                        chapterId = Integer.parseInt(jsonObject1.getString("chapter_id"));
                                        if(searchChapterId(chapterId))
                                        {
                                            JSONArray jsonArray1 = jsonObject1.getJSONArray("chapter_criteria");

                                            for (int k=0; k<jsonArray1.length(); k++)
                                            {
                                                JSONObject object = jsonArray1.getJSONObject(k);
                                                criterionId = Integer.parseInt(object.getString("id"));
                                                if(searchCriterionId(criterionId))
                                                {
                                                    database.addtoVersionTable(new Items(chapterId,
                                                            criterionId,versionName));
                                                }
                                                else
                                                {
                                                    position = object.getString("position");
                                                    description = object.getString("description");
                                                    JSONArray jsonArray2 = object.getJSONArray("criteria_inspection_year");
                                                    for (int b=0; b<jsonArray2.length(); b++)
                                                    {
                                                        JSONObject jsonObject2 = jsonArray2.getJSONObject(b);
                                                        inspectionYear = jsonObject2.getString("inspection_year");
                                                       type = jsonObject2.getString("type");
                                                        database.addtoAuditCriterionTable(new Items(criterionId,blockid,chapterId,
                                                                position,description,type,inspectionYear,versionName));
                                                    }
                                                        database.addtoVersionTable(new Items(chapterId,criterionId,versionName));
                                                }
                                            }
                                        }
                                        else
                                        {
                                          /*  blockid =  Integer.parseInt(jsonObject.getString("block_id"));
                                            chapterId = Integer.parseInt(jsonObject1.getString("chapter_id"));*/
                                            database.addtoChapterTable(new Items(chapterId,blockid,jsonObject1.getString("chapter_name"),
                                                    "",jsonObject1.getString("chapter_description")));
                                            JSONArray jsonArray1 = jsonObject1.getJSONArray("chapter_criteria");
                                            for (int l=0;l<jsonArray1.length();l++)
                                            {
                                                JSONObject object = jsonArray1.getJSONObject(l);
                                                position = object.getString("position");
                                                description = object.getString("description");
                                                criterionId = Integer.parseInt(object.getString("id"));
                                                JSONArray jsonArray2 = object.getJSONArray("criteria_inspection_year");
                                                for (int b=0; b<jsonArray2.length(); b++)
                                                {
                                                    JSONObject jsonObject2 = jsonArray2.getJSONObject(b);
                                                    inspectionYear = jsonObject2.getString("inspection_year");
                                                    type = jsonObject2.getString("type");
                                                    database.addtoAuditCriterionTable(new Items(criterionId,blockid,chapterId,
                                                            position,description,type,inspectionYear,versionName));
                                                }

                                                database.addtoVersionTable(new Items(chapterId,criterionId,versionName));

                                            }
                                        }

                                    }

                                }
                                else
                                {


                                    database.addtoBlockTable(new Items(blockid,jsonObject.getString("block_label"),
                                            jsonObject.getString("block_name")));

                                    JSONArray jsonArray = jsonObject.getJSONArray("chapter");

                                    for (int a=0; a<jsonArray.length(); a++)
                                    {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(a);

                                        chapterId = Integer.parseInt(jsonObject1.getString("chapter_id"));

                                        database.addtoChapterTable(new Items(chapterId,blockid,jsonObject1.getString("chapter_name"),
                                                "",""));
                                        JSONArray jsonArray1 = jsonObject1.getJSONArray("chapter_criteria");

                                        for (int l=0;l<jsonArray1.length();l++)
                                        {
                                            JSONObject object = jsonArray1.getJSONObject(l);
                                            position = object.getString("position");
                                            description = object.getString("description");
                                            criterionId = Integer.parseInt(object.getString("id"));
                                            JSONArray jsonArray2 = object.getJSONArray("criteria_inspection_year");

                                            for (int b=0; b<jsonArray2.length(); b++)
                                            {
                                                JSONObject jsonObject2 = jsonArray2.getJSONObject(b);
                                                inspectionYear = jsonObject2.getString("inspection_year");
                                                type = jsonObject2.getString("type");
                                                database.addtoAuditCriterionTable(new Items(criterionId,blockid,chapterId,
                                                        position,description,type,inspectionYear,versionName,1));
                                            }
                                            database.addtoVersionTable(new Items(chapterId,criterionId,versionName));

                                        }
                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        iteration = iteration+1;
                        if(iteration<versionNamesArray.length)
                        {

                            String versionName = versionNamesArray[iteration];
                            if(searchVersion(versionName))
                            {
                                dialog.dismiss();
                                viewPager = (ViewPager) findViewById(R.id.viewpager);
                                setupViewPager(viewPager);
                                tabLayout = (TabLayout) findViewById(R.id.tabs);
                                tabLayout.setupWithViewPager(viewPager);
                                setupTabIcons();
                                dialog.dismiss();
                            }
                            else {
                                dialog.dismiss();
                                getCodeRequest(versionNamesArray[iteration]);
                            }

                        }
                        else
                        {
                            viewPager = (ViewPager) findViewById(R.id.viewpager);
                            setupViewPager(viewPager);
                            tabLayout = (TabLayout) findViewById(R.id.tabs);
                            tabLayout.setupWithViewPager(viewPager);
                            setupTabIcons();
                            dialog.dismiss();

                        }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    dialog.dismiss();
                    showInternetDialog();
                    //Toast.makeText(getApplicationContext(),"No internet connection.. ",Toast.LENGTH_SHORT).show();
                }
                else if (error instanceof AuthFailureError) {
                    dialog.dismiss();
                    Log.e("AuthFailureError", "AuthFailureError.......");
                } else if (error instanceof ServerError) {
                    dialog.dismiss();
                    Log.e("ServerError>>>>>>>>>", "ServerError.......");
                } else if (error instanceof NetworkError) {
                    dialog.dismiss();
                    showInternetDialog();
                   // Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    dialog.dismiss();
                    Log.e("ParseError>>>>>>>>>", "ParseError.......");
                            /*finish();
                            startActivity(getIntent());*/
                }else if (error instanceof TimeoutError) {
                    dialog.dismiss();
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
        requestQueue.add(req1);

    }
    public boolean searchChapterId(int chapterId)
    {
        ArrayList<Items> chapterDetails = new ArrayList<Items>();
        boolean returnValue = false;
        chapterDetails = (ArrayList<Items>) database.getChapterTableDetails();
        for (int i =0; i<chapterDetails.size(); i++)
        {
            if(chapterDetails.size()!=0 && chapterDetails.get(i).getChapterId()== chapterId)
            {
                returnValue = true;
                break;
            }
            else
                returnValue = false;
        }
        return returnValue;
    }
    public boolean searchBlockId(int blockid)
    {
        ArrayList<Items> blockDetails = new ArrayList<Items>();
        boolean returnValue = false;
        blockDetails = (ArrayList<Items>) database.getBlockTableDetails();
        for (int i =0; i<blockDetails.size(); i++)
        {
            if(blockDetails.size()!=0 && blockDetails.get(i).getBlockId() == blockid)
            {
                returnValue = true;
                break;
            }
            else
                returnValue = false;
        }
        return returnValue;
    }
    public boolean searchCriterionId(int criterionId)
    {
        ArrayList<Items> criterionDetails = new ArrayList<Items>();
        boolean returnValue = false;
        criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails();
        for (int i =0; i<criterionDetails.size(); i++)
        {
            if(criterionDetails.size()!=0 && criterionDetails.get(i).getCriterionId()== criterionId)
            {
                returnValue = true;
                break;
            }
            else
                returnValue = false;
        }
        return returnValue;
    }
    public boolean searchVersion(String versionName)
    {
        ArrayList<Items> versionDetails = new ArrayList<Items>();
        boolean returnValue = false;
        versionDetails = (ArrayList<Items>) database.getVersionTableDetails();
        for (int i =0; i<versionDetails.size(); i++)
        {
            if(versionDetails.size()!=0 && versionDetails.get(i).getVersionName().equals(versionName))
            {
                returnValue = true;
                break;
            }
            else
                returnValue = false;
        }
        return returnValue;
    }
    private void showInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
        String message = "<font color=#000000>Sorry. we couldn't connect to internet, You<b> won't be login </b> without internet !!!</font> ";
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
        // display dialog
        dialog.show();
    }

}
