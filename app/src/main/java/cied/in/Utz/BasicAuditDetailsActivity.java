package cied.in.Utz;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.Model.URL;
import cied.in.Utz.SharedPreferance.SessionManager;

public class BasicAuditDetailsActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9,
            text10, text11, text12, text13, text14, text15, text16, text17, text18, text19,
            text20, text21, text22, text23, text24, text25, text26,text27,text28,text29;
    TextView syncButton, submitButton, recordInspectionButton, resultButton, auditNameText;
    EditText farmNameText, auditorsText, yearText, leagalStatusText, statusText, dateText, totalAreaText,
            addressText, districtText, phoneNumberText, cityText, countryText, identificationNumberText,
            typeOfOperatorText, permenentMaleWorkersText, permenentFemaleWorkersText, totalProductionText, temporaryMaleWorkersText,
            temporaryFemaleWorkersText, typeOfProcessText, isCertifiedBeforeText, personInChargeText, otherCertificationBoradText,
            areaUnderUtzText,leadershipPercentageText;
    RelativeLayout editLayout, recordInspectionLayout, syncLayout, submitLayout, resultLayout,
            submitDisableLayout, resultDisableLayout;
    Typeface typeface, tf;
    UtzDatabase database;
    SessionManager manager;
    int auditId, subAuditId;
    int iteration = 0;
    private static int SPLASH_TIME_OUT = 2000;
    String accessToken;
    int[] chapterIdsArray,criterionIdArray;
    ImageView backIcon;
    String versionName,inspectionYear;
    String auditorsName, auditorId, isSubmit = "false";
    JSONArray chapterDetailsJsonArray = new JSONArray();
    JSONArray auditDetailsJsonArray = new JSONArray();
    JSONArray criterionDetailsJsonArray = new JSONArray();
    JSONArray filesDetailsJsonArray = new JSONArray();
    JSONArray deletedFilesDetailsJsonArray = new JSONArray();
    JSONObject syncJsonObject = new JSONObject();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> auditorsDetails = new ArrayList<Items>();
    ArrayList<Items> subAuditDetails = new ArrayList<Items>();
    ArrayList<Items> criterionDetails = new ArrayList<Items>();
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> additionalCriteriaDetails = new ArrayList<Items>();
    ArrayList<Items> auditCriterionComplianceDetails = new ArrayList<Items>();
    ArrayList<Items> fileAttachmentDetails = new ArrayList<Items>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_audit_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        text1 = (TextView) findViewById(R.id.text1);
        text10 = (TextView) findViewById(R.id.text10);
        text2 = (TextView) findViewById(R.id.text2);
        text11 = (TextView) findViewById(R.id.text11);
        text3 = (TextView) findViewById(R.id.text3);
        text12 = (TextView) findViewById(R.id.text12);
        text4 = (TextView) findViewById(R.id.text4);
        text13 = (TextView) findViewById(R.id.text13);
        text5 = (TextView) findViewById(R.id.text5);
        text14 = (TextView) findViewById(R.id.text14);
        text6 = (TextView) findViewById(R.id.text6);
        text15 = (TextView) findViewById(R.id.text15);
        text7 = (TextView) findViewById(R.id.text7);
        text16 = (TextView) findViewById(R.id.text16);
        text8 = (TextView) findViewById(R.id.text8);
        text17 = (TextView) findViewById(R.id.text17);
        text9 = (TextView) findViewById(R.id.text9);
        text18 = (TextView) findViewById(R.id.text18);
        text19 = (TextView) findViewById(R.id.text19);
        text22 = (TextView) findViewById(R.id.text22);
        text20 = (TextView) findViewById(R.id.text20);
        text23 = (TextView) findViewById(R.id.text23);
        text21 = (TextView) findViewById(R.id.text21);
        text24 = (TextView) findViewById(R.id.text24);
        text25 = (TextView) findViewById(R.id.text25);
        text26 = (TextView) findViewById(R.id.text26);
        text27 = (TextView) findViewById(R.id.text27);
        text28 = (TextView) findViewById(R.id.text28);
        text29 = (TextView) findViewById(R.id.text29);

        backIcon = (ImageView) toolbar.findViewById(R.id.imageView7);
        syncButton = (TextView) findViewById(R.id.syncBtn);
        auditNameText = (TextView) toolbar.findViewById(R.id.textView14);
        submitButton = (TextView) findViewById(R.id.submitBtn);
        recordInspectionButton = (TextView) findViewById(R.id.inspectionBtn);
        resultButton = (TextView) findViewById(R.id.resultBtn);
        editLayout = (RelativeLayout) findViewById(R.id.editlayout);
        submitDisableLayout = (RelativeLayout) findViewById(R.id.submitDisableLayout);
        resultDisableLayout = (RelativeLayout) findViewById(R.id.resultDisableLayout);

        farmNameText = (EditText) findViewById(R.id.farmNameText);
        auditorsText = (EditText) findViewById(R.id.auditorsText);
        yearText = (EditText) findViewById(R.id.yearText);
        leagalStatusText = (EditText) findViewById(R.id.auditTypeText);
        statusText = (EditText) findViewById(R.id.statusText);
        dateText = (EditText) findViewById(R.id.dateText);
        totalAreaText = (EditText) findViewById(R.id.farmAreaText);
        addressText = (EditText) findViewById(R.id.addressText);
        districtText = (EditText) findViewById(R.id.districtText);
        phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);
        cityText = (EditText) findViewById(R.id.tehsilText);
        countryText = (EditText) findViewById(R.id.villageText);
        identificationNumberText = (EditText) findViewById(R.id.idNumberText);
        typeOfOperatorText = (EditText) findViewById(R.id.CFAText);
        permenentMaleWorkersText = (EditText) findViewById(R.id.totalPdctionAreaText);
        permenentFemaleWorkersText = (EditText) findViewById(R.id.areaUnderCastorText);
        totalProductionText = (EditText) findViewById(R.id.productionCapacityText);
        temporaryMaleWorkersText = (EditText) findViewById(R.id.yearsInCastorPdctText);
        temporaryFemaleWorkersText = (EditText) findViewById(R.id.ownLandAreaText);
        typeOfProcessText = (EditText) findViewById(R.id.leasedStatusText);
        isCertifiedBeforeText = (EditText) findViewById(R.id.leasedAreaText);
        personInChargeText = (EditText) findViewById(R.id.personInChargeText);
        otherCertificationBoradText = (EditText) findViewById(R.id.numberOfSuppliersText);
        areaUnderUtzText = (EditText) findViewById(R.id.numberOfShiftText);
        leadershipPercentageText = (EditText) findViewById(R.id.leadershipText);

        recordInspectionLayout = (RelativeLayout) findViewById(R.id.relativeLayout10);
        resultLayout = (RelativeLayout) findViewById(R.id.resultLayout);
        syncLayout = (RelativeLayout) findViewById(R.id.relativeLayout9);
        submitLayout = (RelativeLayout) findViewById(R.id.relativeLayout11);

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        tf = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/Roboto-Regular.ttf");
        database = new UtzDatabase(this);
        manager = new SessionManager(this);
        syncButton.setTypeface(typeface);
        text1.setTypeface(typeface);
        submitButton.setTypeface(typeface);
        text2.setTypeface(typeface);
        recordInspectionButton.setTypeface(typeface);
        text3.setTypeface(typeface);
        resultButton.setTypeface(typeface);
        text10.setTypeface(typeface);
        auditNameText.setTypeface(typeface);
        text10.setTypeface(typeface);


        text2.setTypeface(tf);
        text7.setTypeface(tf);
        text12.setTypeface(tf);
        text26.setTypeface(tf);
        text3.setTypeface(tf);
        text8.setTypeface(tf);
        text13.setTypeface(tf);
        text4.setTypeface(tf);
        text9.setTypeface(tf);
        text14.setTypeface(tf);
        text5.setTypeface(tf);
        text10.setTypeface(tf);
        text15.setTypeface(tf);
        text6.setTypeface(tf);
        text11.setTypeface(tf);
        text16.setTypeface(tf);
        text17.setTypeface(tf);
        text20.setTypeface(tf);
        text23.setTypeface(tf);
        text18.setTypeface(tf);
        text21.setTypeface(tf);
        text24.setTypeface(tf);
        text19.setTypeface(tf);
        text22.setTypeface(tf);
        text25.setTypeface(tf);
        text27.setTypeface(tf);
        text28.setTypeface(tf);
        text29.setTypeface(tf);

        farmNameText.setTypeface(tf);
        statusText.setTypeface(tf);
        districtText.setTypeface(tf);
        auditorsText.setTypeface(tf);
        dateText.setTypeface(tf);
        phoneNumberText.setTypeface(tf);
        yearText.setTypeface(tf);
        totalAreaText.setTypeface(tf);
        cityText.setTypeface(tf);
        leagalStatusText.setTypeface(tf);
        addressText.setTypeface(tf);
        countryText.setTypeface(tf);
        identificationNumberText.setTypeface(tf);
        totalProductionText.setTypeface(tf);
        typeOfProcessText.setTypeface(tf);
        typeOfOperatorText.setTypeface(tf);
        isCertifiedBeforeText.setTypeface(tf);
        temporaryMaleWorkersText.setTypeface(tf);
        permenentMaleWorkersText.setTypeface(tf);
        temporaryFemaleWorkersText.setTypeface(tf);
        personInChargeText.setTypeface(tf);
        permenentFemaleWorkersText.setTypeface(tf);
        otherCertificationBoradText.setTypeface(tf);
        areaUnderUtzText.setTypeface(tf);
        leadershipPercentageText.setTypeface(tf);

        farmNameText.setKeyListener(null);
        statusText.setKeyListener(null);
        districtText.setKeyListener(null);
        cityText.setKeyListener(null);
        auditorsText.setKeyListener(null);
        dateText.setKeyListener(null);
        phoneNumberText.setKeyListener(null);
        countryText.setKeyListener(null);
        yearText.setKeyListener(null);
        totalAreaText.setKeyListener(null);
        leagalStatusText.setKeyListener(null);
        addressText.setKeyListener(null);
        identificationNumberText.setKeyListener(null);
        totalProductionText.setKeyListener(null);
        typeOfOperatorText.setKeyListener(null);
        dateText.setKeyListener(null);
        isCertifiedBeforeText.setKeyListener(null);
        temporaryFemaleWorkersText.setKeyListener(null);
        permenentMaleWorkersText.setKeyListener(null);
        personInChargeText.setKeyListener(null);
        permenentFemaleWorkersText.setKeyListener(null);
        otherCertificationBoradText.setKeyListener(null);
        areaUnderUtzText.setKeyListener(null);
        leadershipPercentageText.setKeyListener(null);

        final HashMap<String, String> userDetails = manager.getUserDetails();
        final HashMap<String, String> userId = manager.getAuditId();
        auditId = Integer.parseInt(userId.get(SessionManager.KEY_AUDITID));
        accessToken = userDetails.get(SessionManager.KEY_ACCESS_TOKEN);
        auditorId = userDetails.get(SessionManager.KEY_USERID);

        Log.e("audit id...",""+auditId);
        commonAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails(auditId);
        if(commonAuditDetails.get(0).getCategory().equals("groupaudit")){
            Bundle extras = getIntent().getExtras();
            subAuditId = Integer.parseInt(extras.getString("SUB_AUDIT_ID"));
            Log.e("sub audit id ...",""+subAuditId);
            subAuditDetails = (ArrayList<Items>) database.getSubAuditDetailsTable(subAuditId);

        }
        else {
            subAuditDetails = (ArrayList<Items>) database.getSubAuditDetailsTable(auditId);
            subAuditId = subAuditDetails.get(0).getSubAuditId();
        }
        auditorsDetails = (ArrayList<Items>) database.getAuditorstable(auditId);
        versionName = commonAuditDetails.get(0).getVersionName();
        inspectionYear = commonAuditDetails.get(0).getInspectionYear();
        StringBuffer sbf = new StringBuffer();
        if (auditorsDetails.size() > 0) {

            sbf.append(auditorsDetails.get(0).getAuditorName());
            for (int i = 1; i < auditorsDetails.size(); i++) {
                sbf.append(",").append(auditorsDetails.get(i).getAuditorName());
            }

        }
        auditNameText.setText(commonAuditDetails.get(0).getAuditName());
        dateText.setText(commonAuditDetails.get(0).getStartDate() + " - " +
                commonAuditDetails.get(0).getEndDate());
        yearText.setText(commonAuditDetails.get(0).getYear());

        farmNameText.setText(subAuditDetails.get(0).getFarmName());
        statusText.setText(subAuditDetails.get(0).getStatus());
        phoneNumberText.setText(subAuditDetails.get(0).getPhoneNumber());
       // leagalStatusText.setText(commonAuditDetails.get(0).getLeagalStatus());  // =============================== change common to group
        identificationNumberText.setText(subAuditDetails.get(0).getIdentificationNumber());
        totalAreaText.setText(subAuditDetails.get(0).getTotalArea());
        countryText.setText(subAuditDetails.get(0).getCountry());
        typeOfOperatorText.setText(subAuditDetails.get(0).getTypeOfOperator());
        addressText.setText(subAuditDetails.get(0).getAddress());
        districtText.setText(subAuditDetails.get(0).getDistrict());
        totalProductionText.setText(subAuditDetails.get(0).getTotalProduction());
        permenentMaleWorkersText.setText(subAuditDetails.get(0).getPermenentMaleWorkers());
        permenentFemaleWorkersText.setText(subAuditDetails.get(0).getPermenentFemaleWorkers());
        cityText.setText(subAuditDetails.get(0).getCity());
        temporaryMaleWorkersText.setText(subAuditDetails.get(0).getTempMaleWorkers());
        temporaryFemaleWorkersText.setText(subAuditDetails.get(0).getTempFemaleWorkers());
        typeOfProcessText.setText(subAuditDetails.get(0).getTypeOfProcess());
        otherCertificationBoradText.setText(subAuditDetails.get(0).getOtherCertificationBoards());
        isCertifiedBeforeText.setText(subAuditDetails.get(0).getIsCertifiedBefore());
        areaUnderUtzText.setText(subAuditDetails.get(0).getAreaUnderUtz());
        leadershipPercentageText.setText(subAuditDetails.get(0).getTotalWorkersLivingatPeakSeason());
        personInChargeText.setText(subAuditDetails.get(0).getPersonInCharge());
        auditorsText.setText(sbf.toString());


        submitDisableLayout.setVisibility(View.VISIBLE);
        resultDisableLayout.setVisibility(View.VISIBLE);

        // ======== create acc ================
        criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails(versionName,inspectionYear);
       /* criterionIdArray = new int[criterionDetails.size()];
        for (int i =0; i<criterionDetails.size() ; i++) {
            criterionIdArray[i] = criterionDetails.get(i).getCriterionId();
        }
        additionalCriteriaDetails  =(ArrayList<Items>) database.getAuditCriterionComplianceDetails(criterionIdArray,"1");
*/

        auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(subAuditId);
        if(auditCriterionComplianceDetails.size() == 0)
        {
            for (int i=0 ; i<criterionDetails.size(); i++)
            {

                chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(criterionDetails.get(i).getChapterId());
                database.addtoAuditCriterionComplianceTable(new Items(auditId,subAuditId,chapterDetails.get(0).getBlockId(),
                        criterionDetails.get(i).getChapterId(),criterionDetails.get(i).getCriterionId(),
                        "-2","","","0"));
            }
        }
        recordInspectionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AuditingActivity.class);
                intent.putExtra("SUBAUDITID", subAuditDetails.get(0).getSubAuditId());
                startActivity(intent);
            }
        });
        resultLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (searchComplianceValue("-2")) {
                    Toast.makeText(BasicAuditDetailsActivity.this, "Audit not complete", Toast.LENGTH_SHORT).show();
                }
                else {
                    manager.setSubAuditId("" +subAuditId);
                    Intent intent = new Intent(getApplicationContext(), ResultSheetActivity.class);
                    startActivity(intent);
                }
            }
        });
        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditAuditDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("STARTDATE", commonAuditDetails.get(0).getStartDate());
                intent.putExtra("ENDDATE", commonAuditDetails.get(0).getEndDate());
                startActivity(intent);
            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                startActivity(intent);
            }
        });

        syncLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSubmit = "false";

                fileUpload(isSubmit);

            }
        });
        submitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (searchComplianceValue("-2")) {
                    Toast.makeText(BasicAuditDetailsActivity.this, "Audit not completed", Toast.LENGTH_SHORT).show();
                } else {
                    isSubmit = "true";
                    fileUpload(isSubmit);
                }
            }
        });
        isSubmit = "false";
        fileUpload(isSubmit);


    }
    public boolean searchComplianceValue(String complianceValue) {
        boolean returnValue = false;
        ArrayList<Items> auditCriterionComplianceDetails = new ArrayList<Items>();
        auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(subAuditId);

        for (int i = 0; i < auditCriterionComplianceDetails.size(); i++) {
            if (auditCriterionComplianceDetails.size() != 0 && (auditCriterionComplianceDetails.get(i).getComplianceValue().equals("-2")))

            {
                returnValue = true;
                break;
            } else {
                returnValue = false;
            }
        }
        return returnValue;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivity(intent);
    }
    private void showLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        String message = "<font color=#000000>Sorry. we couldn't connect to internet, Your data <b> won't be synced </b> without internet !!!</font> ";
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

    public Bitmap getImageBitmap(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }
    public String decodeImageBitmap(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return imgString;
    }
    public void fileUpload(final String isSubmit) {
        ArrayList<Items> criterionDetails = new ArrayList<Items>();
        ArrayList<Items> chapterDetails = new ArrayList<Items>();
        Bitmap imageBitmap;
        String encode = null;
        JSONObject jsonObject = null;
        final ProgressDialog pDialog = new ProgressDialog(BasicAuditDetailsActivity.this, R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("File uploading...");
        pDialog.show();

        fileAttachmentDetails = (ArrayList<Items>) database.getFilesAttachmentTable(subAuditId);
        if (fileAttachmentDetails.size() == 0) {
            pDialog.dismiss();
            getSyncDetails(isSubmit);
        } else {
            if (iteration < fileAttachmentDetails.size()) {
                criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails
                        (fileAttachmentDetails.get(iteration).getCriterionId(),versionName,inspectionYear,"");
                Log.e("sssssss",""+criterionDetails.size());
                imageBitmap = getImageBitmap(fileAttachmentDetails.get(iteration).getFilePath());
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("auditor_id", auditorId);
                    jsonObject.put("sub_audit_id", subAuditId);
                    jsonObject.put("criterion_position", criterionDetails.get(0).getPosition());
                    jsonObject.put("audit_file", decodeImageBitmap(imageBitmap));
                    jsonObject.put("file_name", fileAttachmentDetails.get(iteration).getFileName());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("upload json ",jsonObject.toString());
            } else {
                pDialog.dismiss();
            }

            String fileUploadUrl = URL.rootUrl + auditId + "/file/sync";
            JsonObjectRequest req = new JsonObjectRequest(fileUploadUrl, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("response ..", response.toString());
                            pDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Files sync Successfully", Toast.LENGTH_SHORT).show();

                            if (iteration == fileAttachmentDetails.size()-1) {
                                pDialog.dismiss();
                                getSyncDetails(isSubmit);
                            } else {
                                iteration = iteration + 1;
                                fileUpload(isSubmit);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NoConnectionError) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        pDialog.dismiss();
                        Log.e("AuthFailureError", "AuthFailureError.......");
                    } else if (error instanceof ServerError) {
                        pDialog.dismiss();
                        Log.e("ServerError>>>>>>>>>", "ServerError.......");
                    } else if (error instanceof NetworkError) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        pDialog.dismiss();
                        Log.e("ParseError>>>>>>>>>", "ParseError.......");
                            finish();
                            startActivity(getIntent());
                    } else if (error instanceof TimeoutError) {
                        pDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Time out", Toast.LENGTH_SHORT).show();
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
    }
    public void getSyncDetails(String isSubmit)
    {
        int criterionId,chapterId,blockId;
        ArrayList<Items> fileAttachmentDetails = new ArrayList<Items>();
        ArrayList<Items> deletedFileAttachmentDetails = new ArrayList<Items>();
        ArrayList<Items> auditCriterionComplianceDetails = new ArrayList<Items>();
        ArrayList<Items> criterionDetails = new ArrayList<Items>();
        ArrayList<Items> chapterDetails = new ArrayList<Items>();
        ArrayList<Items> blockDetails = new ArrayList<Items>();
        commonAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails(auditId);
        subAuditDetails = (ArrayList<Items>) database.getSubAuditDetailsTable(auditId);
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("start_date", commonAuditDetails.get(0).getStartDate());
            jsonObject1.put("end_date", commonAuditDetails.get(0).getEndDate());
            jsonObject1.put("address", subAuditDetails.get(0).getAddress());
            jsonObject1.put("district", subAuditDetails.get(0).getDistrict());
            jsonObject1.put("phone", subAuditDetails.get(0).getPhoneNumber());
            jsonObject1.put("city", subAuditDetails.get(0).getCity());
            jsonObject1.put("total_area", subAuditDetails.get(0).getTotalArea());
            jsonObject1.put("identification_number", subAuditDetails.get(0).getIdentificationNumber());
            jsonObject1.put("country", subAuditDetails.get(0).getCountry());
            jsonObject1.put("area_under_utz", subAuditDetails.get(0).getAreaUnderUtz());
            jsonObject1.put("permanent_male_workers", subAuditDetails.get(0).getPermenentMaleWorkers());
            jsonObject1.put("total_production", subAuditDetails.get(0).getTotalProduction());
            jsonObject1.put("permanent_female_workers", subAuditDetails.get(0).getPermenentFemaleWorkers());
            jsonObject1.put("temporary_male_workers", subAuditDetails.get(0).getTempMaleWorkers());
            jsonObject1.put("temporary_female_workers", subAuditDetails.get(0).getTempFemaleWorkers());
            jsonObject1.put("type_of_operator", subAuditDetails.get(0).getTypeOfOperator());
            jsonObject1.put("crops", subAuditDetails.get(0).getCrops());
            jsonObject1.put("other_certification_boards", subAuditDetails.get(0).getOtherCertificationBoards());
            jsonObject1.put("is_certified_before", subAuditDetails.get(0).getIsCertifiedBefore());
            jsonObject1.put("type_of_process", subAuditDetails.get(0).getTypeOfProcess());
            jsonObject1.put("legal_status", subAuditDetails.get(0).getLeagalStatus());
            jsonObject1.put("contact_person_name", subAuditDetails.get(0).getPersonInCharge());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        auditDetailsJsonArray.put(jsonObject1);

        auditCriterionComplianceDetails = (ArrayList<Items>) database.
                getAuditCriterionComplianceDetails(subAuditId);
        Log.e("sssssssss.....",""+auditCriterionComplianceDetails.size());
        for (int i = 0; i < auditCriterionComplianceDetails.size(); i++) {

            chapterId = auditCriterionComplianceDetails.get(i).getChapterId();
            criterionId = auditCriterionComplianceDetails.get(i).getCriterionId();
            chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(chapterId);
            criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails(criterionId,versionName,inspectionYear,"");
            blockId = chapterDetails.get(0).getBlockId();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("comment", auditCriterionComplianceDetails.get(i).getComment());
                jsonObject.put("compliance_value", auditCriterionComplianceDetails.get(i).getComplianceValue());
                jsonObject.put("suggestion", auditCriterionComplianceDetails.get(i).getCriterionSuggestion());
                jsonObject.put("block_id", blockId);
                jsonObject.put("last_edited", auditCriterionComplianceDetails.get(i).getLastUpdatedDate());
                jsonObject.put("position", criterionDetails.get(0).getPosition());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            criterionDetailsJsonArray.put(jsonObject);

        }
        deletedFileAttachmentDetails = (ArrayList<Items>) database.getDeletedFilesAttachment(subAuditId);
        for (int i = 0; i < deletedFileAttachmentDetails.size(); i++) {

            criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails
                    (deletedFileAttachmentDetails.get(i).getCriterionId(),versionName,inspectionYear,"");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("criterion_position", criterionDetails.get(0).getPosition());
                jsonObject.put("filename", deletedFileAttachmentDetails.get(i).getFileName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            deletedFilesDetailsJsonArray.put(jsonObject);

        }
        fileAttachmentDetails = (ArrayList<Items>) database.getFilesAttachmentTable(subAuditId);
        for (int i = 0; i < fileAttachmentDetails.size(); i++) {
            criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails
                    (fileAttachmentDetails.get(i).getCriterionId(),versionName,inspectionYear,"");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("criterion_position", criterionDetails.get(0).getPosition());
                jsonObject.put("filename", fileAttachmentDetails.get(i).getFileName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            filesDetailsJsonArray.put(jsonObject);
        }
        try {
            syncJsonObject.put("is_submit", isSubmit);
            syncJsonObject.put("auditor_id", auditorId);
            syncJsonObject.put("sub_audit_id", subAuditId);
            syncJsonObject.put("audit_details", auditDetailsJsonArray);
            syncJsonObject.put("file_name_list", filesDetailsJsonArray);
            syncJsonObject.put("deleted_files", deletedFilesDetailsJsonArray);
            syncJsonObject.put("criterion_data", criterionDetailsJsonArray);

            Log.e("audit data...",criterionDetailsJsonArray.toString());
            Log.e("sync data", syncJsonObject.toString());

            syncRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void syncRequest() {
        String fileUploadUrl = URL.rootUrl + auditId + "/audits/sync";
        Log.e("url", fileUploadUrl);
        final ProgressDialog pDialog = new ProgressDialog(BasicAuditDetailsActivity.this, R.style.AppCompatAlertDialogStyle);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Syncing...");
        pDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(fileUploadUrl, syncJsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("sync response ..", response.toString());
                        String cv;
                        int criteriaId,chapterId,blockId;
                        database.deleteAuditCriterionComplianceTable(subAuditId);
                        fileAttachmentDetails = (ArrayList<Items>) database.getFilesAttachmentTable(subAuditId);
                        for (int i = 0; i < fileAttachmentDetails.size(); i++) {
                            database.updateIsSyncedToFilesAttachmentTable(new Items("true"), "" + fileAttachmentDetails.get(i).getFileId());
                        }
                        JSONArray auditJsonArray = null;
                        try {

                            auditJsonArray = response.getJSONArray("audit_details");
                            JSONObject job = auditJsonArray.getJSONObject(0);
                            database.updateCommonAuditDetails(new Items(job.getString("start_date"), job.getString("end_date")), "" + auditId);
                            database.updateSubAuditDetailsTable(new Items(job.getString("total_area"), job.getString("address"),
                                    job.getString("phone"), job.getString("city"), job.getString("country"), job.getString("identification_number"),
                                    job.getString("permanent_male_workers"), job.getString("permanent_female_workers"), job.getString("type_of_process"),
                                    job.getString("total_production"), job.getString("is_certified_before"), job.getString("temporary_male_workers"),
                                    job.getString("temporary_female_workers"),job.getString("legal_status"), "",
                                    job.getString("district"), job.getString("type_of_operator"), job.getString("other_certification_boards"),
                                    job.getString("area_under_utz"),job.getString("total_workers_living_at_peak_season")), "" + subAuditId);

                            JSONArray accJsonArray = response.getJSONArray("acc_data");

                            for (int i = 0; i < accJsonArray.length(); i++) {

                                JSONObject jsonObject = accJsonArray.getJSONObject(i);
                                chapterId = Integer.parseInt(jsonObject.getString("chapter_id"));
                                criteriaId = Integer.parseInt(jsonObject.getString("criterion_id"));
                                blockId = Integer.parseInt(jsonObject.getString("block_id"));

                                if (jsonObject.getString("compliance_value").equals("-1.0")
                                        || jsonObject.getString("compliance_value").equals("-1")) {
                                    cv = "-1";
                                    database.addtoAuditCriterionComplianceTable(new Items(auditId, subAuditId, blockId, chapterId,
                                            criteriaId, cv, jsonObject.getString("comment"), jsonObject.getString("suggestion"),
                                            jsonObject.getString("last_edited")));
                                } else if (jsonObject.getString("compliance_value").equals("0.0")
                                        || jsonObject.getString("compliance_value").equals("0")) {
                                    cv = "0";
                                    database.addtoAuditCriterionComplianceTable(new Items(auditId, subAuditId, blockId, chapterId,
                                            criteriaId, cv, jsonObject.getString("comment"), jsonObject.getString("suggestion"),
                                            jsonObject.getString("last_edited")));
                                } else if (jsonObject.getString("compliance_value").equals("1.0")
                                        || jsonObject.getString("compliance_value").equals("1")) {
                                    cv = "1";
                                    database.addtoAuditCriterionComplianceTable(new Items(auditId, subAuditId, blockId, chapterId,
                                            criteriaId, cv, jsonObject.getString("comment"), jsonObject.getString("suggestion"),
                                            jsonObject.getString("last_edited")));
                                } else {
                                    cv = "-2";
                                    database.addtoAuditCriterionComplianceTable(new Items(auditId, subAuditId, blockId, chapterId,
                                            criteriaId, cv, jsonObject.getString("comment"), jsonObject.getString("suggestion"),
                                            jsonObject.getString("last_edited")));
                                }
                                if (searchComplianceValue("-2")) {
                                    pDialog.dismiss();
                                    submitDisableLayout.setVisibility(View.VISIBLE);
                                    resultDisableLayout.setVisibility(View.VISIBLE);
                                    submitLayout.setClickable(false);
                                    resultLayout.setClickable(false);

                                } else {
                                    pDialog.dismiss();
                                    submitDisableLayout.setVisibility(View.GONE);
                                    resultDisableLayout.setVisibility(View.GONE);
                                    submitLayout.setClickable(true);
                                    resultLayout.setClickable(true);

                                }
                            }
                                JSONArray fileJsonArray = response.getJSONArray("new_files");
                                if (fileJsonArray.length() == 0) {

                                }
                                else
                                {
                                    for (int j = 0; j < fileJsonArray.length(); j++) {
                                        JSONObject jsonObject1 = fileJsonArray.getJSONObject(j);
                                        criteriaId = Integer.parseInt(jsonObject1.getString("criterion_id"));
                                        auditCriterionComplianceDetails = (ArrayList<Items>) database.
                                                getAuditCriterionComplianceDetails(subAuditId,criteriaId);
                                        database.addtoFilesAttachmentTable(new Items(subAuditId, criteriaId
                                                , auditCriterionComplianceDetails.get(0).getAuditCriterionComplianceId(),
                                                jsonObject1.getString("file_name"), jsonObject1.getString("url"),
                                                "false", "2000-10-10", "false", "true"));
                                    }
                                }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(BasicAuditDetailsActivity.this, "Successfully synced.", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    pDialog.dismiss();
                    showLocationDialog();
                   // Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    pDialog.dismiss();
                    Log.e("AuthFailureError", "AuthFailureError.......");
                } else if (error instanceof ServerError) {
                    pDialog.dismiss();
                    Log.e("ServerError>>>>>>>>>", "ServerError.......");
                } else if (error instanceof NetworkError) {
                    pDialog.dismiss();
                    showLocationDialog();
                    Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    pDialog.dismiss();
                    Log.e("ParseError>>>>>>>>>", "ParseError.......");
                            /*finish();
                            startActivity(getIntent());*/
                } else if (error instanceof TimeoutError) {
                    pDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Time out", Toast.LENGTH_SHORT).show();
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

}
