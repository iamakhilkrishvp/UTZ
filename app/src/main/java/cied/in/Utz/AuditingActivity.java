package cied.in.Utz;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cied.in.Utz.Adapter.FileAttachmentAdapter;
import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.Model.Utility;
import cied.in.Utz.SharedPreferance.SessionManager;

public class AuditingActivity extends AppCompatActivity implements View.OnClickListener{


    ArrayList<Items> criterionDetailList = new ArrayList<Items>();
    ArrayList<Items> totalCriterionList = new ArrayList<Items>();
    ArrayList<Items> blockDetailsList = new ArrayList<Items>();
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> versionDetails = new ArrayList<Items>();
    ArrayList<Items> versionDetailsForCriterias = new ArrayList<Items>();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> auditChapterComplianceDetails = new ArrayList<Items>();
    ArrayList<Items> auditCriterionComplianceDetails = new ArrayList<Items>();
    ArrayList<Items> attachmentFileDetails = new ArrayList<Items>();
    SessionManager manager;
    CardView suggestionCard,uploadFileCard;
    int currentPosition = 0,compliesBtnCount = 0,majorBtnCount = 0, NCBtnCount = 0,NACount=0,prevPosition = 0;
    ImageView icon4,icon1,icon2,icon3;
    UtzDatabase database;
    LinearLayoutManager horizontalLayoutManagaer;
    RecyclerView recyclerView,fileRecyclerview;
    TextView criteriaText,textView,criteriaDescriptionText, complianceText, notApllicableText, majorNCText,
            minorNCText, uploadFileButton,chapterNameText,chapterIndexText,nextChapterButton,chapterText;
    EditText auditobservationText,auditCommentText;
    EditText strengthAreaText,criticalImprovementText,otherImprovementText,recommenendationText;
    ImageView backIcon;
    Spinner spinner;
    int criterianId;
    int selectedPos=0,selectedChapterId,selectedPosition=1,selectedChapterPosition,chapterId;
    int[] chapterIdsArray, blockIdArray;
    ScrollView notesScrollView,scrollView;
    HorizontalAdapter horizontalAdapter;
    RelativeLayout compliesLayout,notApplicableLayout, nonCompliesLayout,majorNCLayout,spinnerLayout,uploadFileLayout;
    int pos = 0,auditId,subAuditId,chapterPosition = 1,criteriaId,accId,flag = 0;
    Typeface typeface;
    AlertDialog dialog;
    int lastPosition = 0;
    String versionName,inspectionYear;
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backIcon = (ImageView) toolbar.findViewById(R.id.imageView4) ;
        icon4 = (ImageView) findViewById(R.id.imageView8) ;
        icon1 = (ImageView) findViewById(R.id.imageView5) ;
        icon2 = (ImageView) findViewById(R.id.imageView6) ;
        icon3 = (ImageView) findViewById(R.id.imageView7) ;
        spinnerLayout = (RelativeLayout) toolbar.findViewById(R.id.relativeLayout4);
        chapterNameText = (TextView) toolbar.findViewById(R.id.textView5);
        chapterIndexText = (TextView) toolbar.findViewById(R.id.textView3);
        backIcon = (ImageView) toolbar.findViewById(R.id.backIcom);
        spinner = (Spinner) toolbar.findViewById(R.id.spinner);
        complianceText = (TextView) findViewById(R.id.resultText);
        notApllicableText = (TextView) findViewById(R.id.MNCText);
        majorNCText = (TextView) findViewById(R.id.MNCText);
        chapterText = (TextView) findViewById(R.id.chapterNameText);
        minorNCText = (TextView) findViewById(R.id.textView10);
        uploadFileButton = (TextView) findViewById(R.id.textView13);
        nextChapterButton = (TextView) findViewById(R.id.textView20);
        criteriaText = (TextView) findViewById(R.id.textView6);
        textView = (TextView) findViewById(R.id.text1);
        criteriaDescriptionText = (TextView) findViewById(R.id.textView7);
        auditCommentText = (EditText) findViewById(R.id.editText1) ;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.topLayout);
        strengthAreaText = (EditText) findViewById(R.id.strengthAreaText) ;
        criticalImprovementText = (EditText) findViewById(R.id.criticalImpText) ;
        otherImprovementText = (EditText) findViewById(R.id.otherImpText) ;
        recommenendationText = (EditText) findViewById(R.id.recomendationText) ;
        uploadFileLayout = (RelativeLayout) findViewById(R.id.uploadFileLayout);

        auditobservationText = (EditText) findViewById(R.id.editText);
        compliesLayout = (RelativeLayout) findViewById(R.id.relativeLayout5);
//        notApplicableLayout = (RelativeLayout) findViewById(R.id.relativeLayout6);
        nonCompliesLayout = (RelativeLayout) findViewById(R.id.relativeLayout7);
        notApplicableLayout = (RelativeLayout) findViewById(R.id.relativeLayout6);
        notesScrollView = (ScrollView) findViewById(R.id.notesScroll);
        scrollView = (ScrollView) findViewById(R.id.scrollView) ;
        suggestionCard = (CardView) findViewById(R.id.thirdCard);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        manager = new SessionManager(this);
        database = new UtzDatabase(this);
        complianceText.setTypeface(typeface);
        notApllicableText.setTypeface(typeface);
        majorNCText.setTypeface(typeface);
        minorNCText.setTypeface(typeface);
        uploadFileButton.setTypeface(typeface);
        criteriaText.setTypeface(typeface);
        textView.setTypeface(typeface);
        criteriaDescriptionText.setTypeface(typeface);
        auditCommentText.setTypeface(typeface);
        auditobservationText.setTypeface(typeface);
        chapterNameText.setTypeface(typeface);
        chapterIndexText.setTypeface(typeface);
        nextChapterButton.setTypeface(typeface);
        strengthAreaText.setTypeface(typeface);
        criticalImprovementText.setTypeface(typeface);
        otherImprovementText.setTypeface(typeface);
        recommenendationText.setTypeface(typeface);
        chapterText.setTypeface(typeface);

        final HashMap<String,String> userId = manager.getAuditId();
        auditId = Integer.parseInt(userId.get(SessionManager.KEY_AUDITID));
        Intent intent = getIntent();
        subAuditId = intent.getIntExtra("SUBAUDITID",0);

        // ===== setting up of  criterion recyclerview  and file attachment recyclerview

        recyclerView = (RecyclerView) findViewById(R.id.criteriaList);
        fileRecyclerview = (RecyclerView) findViewById(R.id.fileRecyclerview);
        horizontalLayoutManagaer
                = new LinearLayoutManager(AuditingActivity.this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager horizontalLayoutManagaerforFile
                = new LinearLayoutManager(AuditingActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        fileRecyclerview.setLayoutManager(horizontalLayoutManagaerforFile);

        // ======= click of auditing answers 4 buttons

        compliesLayout.setOnClickListener(this);
        notApplicableLayout.setOnClickListener(this);
        nonCompliesLayout.setOnClickListener(this);

        commonAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails(auditId);  // get commonAudit Details from commonAuditDetailsTable
        versionName = commonAuditDetails.get(0).getVersionName();
        inspectionYear = commonAuditDetails.get(0).getInspectionYear();
        // ======= get all criteras of that particular versionName and dinspection Year

        totalCriterionList = (ArrayList<Items>) database.getCriterionTableDetails(versionName,inspectionYear);
        chapterIdsArray = new int[totalCriterionList.size()];
        for (int i =0; i<totalCriterionList.size() ; i++)
        {
            chapterIdsArray[i] = totalCriterionList.get(i).getChapterId();
        }
        // ==== setting upof all chapter name and block name in drop down ================

        chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(chapterIdsArray);
        blockIdArray = new int[chapterDetails.size()];
        for (int i =0; i<chapterDetails.size() ; i++)
        {

            blockIdArray[i] = chapterDetails.get(i).getBlockId();
        }
        blockDetailsList = (ArrayList<Items>) database.getBlockDetails(blockIdArray);
        spinnerAdapter = new SpinnerAdapter(AuditingActivity.this,blockDetailsList);
        spinner.setAdapter(spinnerAdapter);
        spinnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                spinner.performClick();
            }
        });

        criterionDetailList = (ArrayList<Items>) database.getCriterionTableDetails(blockDetailsList.
               get(0).getBlockId(),versionName,inspectionYear,1);
        chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(criterionDetailList.get(0).getChapterId());
        criteriaId = criterionDetailList.get(0).getCriterionId();
        horizontalAdapter = new HorizontalAdapter(criterionDetailList);  // set criterion details to criterion-recyclerview
        recyclerView.setAdapter(horizontalAdapter);
        chapterText.setText(chapterDetails.get(0).getChapterName());
        criteriaText.setText(" criteria "+chapterDetails.get(0).getPosition()+"."+criterionDetailList.get(0).getPosition()); // set criteria position
        criteriaDescriptionText.setText(criterionDetailList.get(0).getDescription()); // set critera description of first postion

        auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails
                (subAuditId,criterionDetailList.get(0).getCriterionId()); // get audit criteiondetails of first chapter of particular audit 'subAuditId'

        auditCommentText.setText( auditCriterionComplianceDetails.get(0).getComment()); // set comments to comment text
        auditobservationText.setText( auditCriterionComplianceDetails.get(0).getCriterionSuggestion()); // set criterion suggestion to cuggestion text

        if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("1"))
        {
            icon1.setImageResource(R.drawable.radio_button_selected);
            icon2.setImageResource(R.drawable.radio_button_unselected);
            icon3.setImageResource(R.drawable.radio_button_unselected);
            compliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
            nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            complianceText.setTextColor(Color.parseColor("#ffffff"));
            notApllicableText.setTextColor(Color.parseColor("#015877"));
            minorNCText.setTextColor(Color.parseColor("#015877"));
            suggestionCard.setVisibility(View.GONE);
            NCBtnCount = 0;
            NACount = 0;
        }
        else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("0"))
        {
            icon1.setImageResource(R.drawable.radio_button_unselected);
            icon2.setImageResource(R.drawable.radio_button_unselected);
            icon3.setImageResource(R.drawable.radio_button_selected);
            compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
            notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            complianceText.setTextColor(Color.parseColor("#015877"));
            notApllicableText.setTextColor(Color.parseColor("#ffffff"));
            minorNCText.setTextColor(Color.parseColor("#015877"));
            suggestionCard.setVisibility(View.VISIBLE);

            NACount = 0;
            compliesBtnCount = 0;
        }
        else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("-1"))
        {
            icon1.setImageResource(R.drawable.radio_button_unselected);
            icon2.setImageResource(R.drawable.radio_button_selected);
            icon3.setImageResource(R.drawable.radio_button_unselected);
            compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
            complianceText.setTextColor(Color.parseColor("#015877"));
            notApllicableText.setTextColor(Color.parseColor("#015877"));
            minorNCText.setTextColor(Color.parseColor("#ffffff"));
            suggestionCard.setVisibility(View.VISIBLE);
            NCBtnCount = 0;
            compliesBtnCount = 0;
        }
        else
        {
            icon1.setImageResource(R.drawable.radio_button_unselected);
            icon2.setImageResource(R.drawable.radio_button_unselected);
            icon3.setImageResource(R.drawable.radio_button_unselected);
            compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
            complianceText.setTextColor(Color.parseColor("#015877"));
            notApllicableText.setTextColor(Color.parseColor("#015877"));
            minorNCText.setTextColor(Color.parseColor("#015877"));
            suggestionCard.setVisibility(View.VISIBLE);
            NCBtnCount = 0;
            NACount = 0;
            compliesBtnCount = 0;
        }
        attachmentFileDetails = (ArrayList<Items>) database.getFilesAttachmentTableDetails(subAuditId,
                auditCriterionComplianceDetails.get(0).getAuditCriterionComplianceId());  // get all attachment files detils of ACC of first chapter...
        if(attachmentFileDetails.size() == 0)
        {
            // nothing to do if no attachment files in 'attachmentFileDetails'
        }
        else
        {
            FileAttachmentAdapter adapter = new FileAttachmentAdapter(AuditingActivity.this,attachmentFileDetails); // ad attachment files to adapter
            fileRecyclerview.setAdapter(adapter);
        }

        //  =====  Setting chapter name of length upto 15 digit to spinner...
        if(blockDetailsList.get(0).getBlockName().length()<= 15)
        {
            chapterIndexText.setText(blockDetailsList.get(0).getBlockLabel()+": ");
            chapterNameText.setText(blockDetailsList.get(0).getBlockName());
        }
        else {
            chapterIndexText.setText(blockDetailsList.get(0).getBlockLabel()+": ");
            String subString = blockDetailsList.get(0).getBlockName().substring(0,14)+"...";
            chapterNameText.setText(subString);
        }
        // == set text color red for mandatory criteria

        if(criterionDetailList.get(0).getCriterionType().equals("M"))
        {
            criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
        }
        else
        {
            criteriaDescriptionText.setTextColor(Color.parseColor("#000000"));
        }
        uploadFileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {    // file upload button click .....


                auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails
                        (subAuditId,criterionDetailList.get(0).getCriterionId());

                accId = auditCriterionComplianceDetails.get(0).getAuditCriterionComplianceId();

                selectImage(); //  call selectimage function for getting imagefile from gallery or camera .......

            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // back button click .....
                Intent intent = new Intent(getApplicationContext(),BasicAuditDetailsActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.relativeLayout5:
                if(compliesBtnCount == 0)
                {
                    icon1.setImageResource(R.drawable.radio_button_selected);
                    icon2.setImageResource(R.drawable.radio_button_unselected);
                    icon3.setImageResource(R.drawable.radio_button_unselected);
                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    complianceText.setTextColor(Color.parseColor("#ffffff"));
                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                    minorNCText.setTextColor(Color.parseColor("#015877"));
                    database.updateCompliesValuesToACC(new Items("1",getCurrentDate()),""+auditId,""+subAuditId,""+criteriaId); //  update compliance value to audit criterion compliance table
                  //  suggestionCard.setVisibility(View.GONE);

                }
                else {
                    icon1.setImageResource(R.drawable.radio_button_unselected);
                    icon2.setImageResource(R.drawable.radio_button_unselected);
                    icon3.setImageResource(R.drawable.radio_button_unselected);
                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    complianceText.setTextColor(Color.parseColor("#015877"));
                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                    minorNCText.setTextColor(Color.parseColor("#015877"));
                    database.updateCompliesValuesToACC(new Items("-2",getCurrentDate()),""+auditId,""+subAuditId,""+criteriaId); //  update compliance value to audit criterion compliance table
                    suggestionCard.setVisibility(View.VISIBLE);

                }
                if(compliesBtnCount == 0)
                {
                    compliesBtnCount = 1;
                }
                else {compliesBtnCount = 0;}
                majorBtnCount = 0;
                NCBtnCount = 0;
                NACount=0;
                break;
            case R.id.relativeLayout6:

                if(NACount == 0) {
                    icon1.setImageResource(R.drawable.radio_button_unselected);
                    icon2.setImageResource(R.drawable.radio_button_selected);
                    icon3.setImageResource(R.drawable.radio_button_unselected);
                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    complianceText.setTextColor(Color.parseColor("#015877"));
                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                    minorNCText.setTextColor(Color.parseColor("#ffffff"));
                    database.updateCompliesValuesToACC(new Items("-1",getCurrentDate()),""+auditId,""+subAuditId,""+criteriaId);// update compliance value to audit criterion compliance table
                    suggestionCard.setVisibility(View.VISIBLE);
                }
                else {
                    icon1.setImageResource(R.drawable.radio_button_unselected);
                    icon2.setImageResource(R.drawable.radio_button_unselected);
                    icon3.setImageResource(R.drawable.radio_button_unselected);
                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                    complianceText.setTextColor(Color.parseColor("#015877"));
                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                    minorNCText.setTextColor(Color.parseColor("#015877"));
                    database.updateCompliesValuesToACC(new Items("-2",getCurrentDate()),""+auditId,""+subAuditId,""+criteriaId);// update compliance value to audit criterion compliance table
                    suggestionCard.setVisibility(View.VISIBLE);
                }
                if(NACount == 0)
                {
                    NACount = 1;
                }
                else {NACount = 0;}

                compliesBtnCount = 0;
                majorBtnCount = 0;
                NCBtnCount = 0;
                break;
            case R.id.relativeLayout7:
                if(NCBtnCount == 0) {
                    icon1.setImageResource(R.drawable.radio_button_unselected);
                    icon2.setImageResource(R.drawable.radio_button_unselected);
                    icon3.setImageResource(R.drawable.radio_button_selected);
                    notApplicableLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));
                    compliesLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));
                    nonCompliesLayout.setBackground(getResources().getDrawable(R.drawable.spinner_bg));
                    complianceText.setTextColor(Color.parseColor("#015877"));
                    notApllicableText.setTextColor(Color.parseColor("#ffffff"));
                    minorNCText.setTextColor(Color.parseColor("#015877"));
                    database.updateCompliesValuesToACC(new Items("0", getCurrentDate()), "" + auditId, "" + subAuditId, "" + criteriaId); // update compliance value to audit criterion compliance table
                    suggestionCard.setVisibility(View.VISIBLE);
                }
                else {
                    icon1.setImageResource(R.drawable.radio_button_unselected);
                    icon2.setImageResource(R.drawable.radio_button_unselected);
                    icon3.setImageResource(R.drawable.radio_button_unselected);
                    notApplicableLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));
                    compliesLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));
                    nonCompliesLayout.setBackground(getResources().getDrawable(R.drawable.button_bg));
                    complianceText.setTextColor(Color.parseColor("#015877"));
                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                    minorNCText.setTextColor(Color.parseColor("#015877"));
                    database.updateCompliesValuesToACC(new Items("-2", getCurrentDate()), "" + auditId, "" + subAuditId, "" + criteriaId); // update compliance value to audit criterion compliance table
                    suggestionCard.setVisibility(View.VISIBLE);
                }
                if(NCBtnCount == 0)
                {
                    NCBtnCount = 1;
                }
                else {NCBtnCount = 0;}

                compliesBtnCount = 0;
                NACount=0;
                majorBtnCount = 0;
                break;
        }

    }

    public class SpinnerAdapter extends BaseAdapter {

        Context mContext;
        Activity activity;
        LayoutInflater inflater;
        private List<Items> msg = null;
        private ArrayList<Items> arraylist;
        TextView criteriaTextview;
        public SpinnerAdapter(Activity a, ArrayList<Items> arraylist) {
            mContext = a;
            activity = a;
            this.msg = arraylist;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = new ArrayList<Items>();
            this.arraylist.addAll(msg);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return msg.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemViewType(int position) {

            return position;
        }
        public void setTextview(TextView textview)
        {
            this.criteriaTextview = textview;

        }



        public class ViewHolder {

            TextView spinnerText, blockText;
            RelativeLayout relativeLayout;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final int positions = position;
            ViewHolder holder;
            holder = new ViewHolder();
            if (view == null) {

                view = inflater.inflate(R.layout.custom_spinner, null);

                holder.spinnerText = (TextView) view.findViewById(R.id.textView11);
                holder.blockText = (TextView) view.findViewById(R.id.textView50);
                holder.relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
                holder.spinnerText.setTypeface(typeface);
                holder.blockText.setTypeface(typeface);

                view.setTag(holder);

            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.spinnerText.setText(""+msg.get(positions).getBlockName());
            holder.blockText.setText( ""+blockDetailsList.get(position).getBlockLabel() +" . ");

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prevPosition = 0;currentPosition = 0;
                    ArrayList<Items> chapterDetails = new ArrayList<Items>();
                    spinner.setVisibility(View.GONE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    notesScrollView.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                    scrollView.fullScroll(ScrollView.FOCUS_UP);
                    nextChapterButton.setVisibility(View.INVISIBLE);
                    selectedPosition = position+1;
                    selectedChapterId = msg.get(positions).getChapterId();
                    chapterId = selectedChapterId;
                    if(msg.get(position).getBlockName().length()<= 15)
                    {
                        chapterIndexText.setText(msg.get(positions).getBlockLabel()+":");
                        chapterNameText.setText(msg.get(positions).getBlockName());

                    }
                    else {
                        chapterIndexText.setText(msg.get(positions).getBlockLabel()+":");
                        String subString = (msg.get(positions).getBlockName()).substring(0,14)+"...";
                        chapterNameText.setText(subString);

                    }
                    criterionDetailList = (ArrayList<Items>) database.getCriterionTableDetails
                            (msg.get(position).getBlockId(),versionName, inspectionYear,1);
                    criteriaId = criterionDetailList.get(0).getCriterionId();

                    selectedPos=0;
                    HorizontalAdapter horizontalAdapter = new HorizontalAdapter(criterionDetailList);
                    recyclerView.setAdapter(horizontalAdapter);
                    notifyDataSetChanged();
                    chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(criterionDetailList.get(0).getChapterId());
                    chapterText.setText("Ch : "+chapterDetails.get(0).getChapterName());
                    criteriaText.setText(" criteria "+criterionDetailList.get(0).getPosition());
                    criteriaDescriptionText.setText(criterionDetailList.get(0).getDescription());
                    if(criterionDetailList.get(0).getCriterionType().equals("M"))
                    {
                        criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
                    }
                    else
                    {
                        criteriaDescriptionText.setTextColor(Color.parseColor("#000000"));
                    }
                    auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(
                            subAuditId,criterionDetailList.get(0).getCriterionId());

                    auditCommentText.setText(auditCriterionComplianceDetails.get(0).getComment());
                    auditobservationText.setText(auditCriterionComplianceDetails.get(0).getCriterionSuggestion());
                    if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("1"))
                    {
                        icon1.setImageResource(R.drawable.radio_button_selected);
                        icon2.setImageResource(R.drawable.radio_button_unselected);
                        icon3.setImageResource(R.drawable.radio_button_unselected);
                        compliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                        nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        complianceText.setTextColor(Color.parseColor("#ffffff"));
                        notApllicableText.setTextColor(Color.parseColor("#015877"));
                        minorNCText.setTextColor(Color.parseColor("#015877"));
                        suggestionCard.setVisibility(View.GONE);
                        NACount = 0;
                        NCBtnCount = 0;
                    }
                    else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("0"))
                    {
                        icon1.setImageResource(R.drawable.radio_button_unselected);
                        icon2.setImageResource(R.drawable.radio_button_unselected);
                        icon3.setImageResource(R.drawable.radio_button_selected);
                        compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                        notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        complianceText.setTextColor(Color.parseColor("#015877"));
                        notApllicableText.setTextColor(Color.parseColor("#ffffff"));
                        minorNCText.setTextColor(Color.parseColor("#015877"));
                        suggestionCard.setVisibility(View.VISIBLE);
                        NACount = 0;
                        compliesBtnCount = 0;
                    }
                    else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("-1"))
                    {
                        icon1.setImageResource(R.drawable.radio_button_unselected);
                        icon2.setImageResource(R.drawable.radio_button_selected);
                        icon3.setImageResource(R.drawable.radio_button_unselected);
                        compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                        complianceText.setTextColor(Color.parseColor("#015877"));
                        notApllicableText.setTextColor(Color.parseColor("#015877"));
                        minorNCText.setTextColor(Color.parseColor("#ffffff"));
                        suggestionCard.setVisibility(View.VISIBLE);
                        NCBtnCount = 0;
                        compliesBtnCount = 0;
                    }
                    else
                    {
                        icon1.setImageResource(R.drawable.radio_button_unselected);
                        icon2.setImageResource(R.drawable.radio_button_unselected);
                        icon3.setImageResource(R.drawable.radio_button_unselected);
                        compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                        complianceText.setTextColor(Color.parseColor("#015877"));
                        notApllicableText.setTextColor(Color.parseColor("#015877"));
                        minorNCText.setTextColor(Color.parseColor("#015877"));
                        suggestionCard.setVisibility(View.VISIBLE);
                        NCBtnCount = 0;
                        NACount = 0;
                        compliesBtnCount = 0;
                    }
                    attachmentFileDetails = (ArrayList<Items>) database.getFilesAttachmentTableDetails(subAuditId,
                            auditCriterionComplianceDetails.get(0).getAuditCriterionComplianceId());  // get all attachment files detils of ACC of first chapter...
                    if(attachmentFileDetails.size() == 0)
                    {
                        // nothing to do if no attachment files in 'attachmentFileDetails'
                    }
                    else
                    {
                        FileAttachmentAdapter adapter = new FileAttachmentAdapter(AuditingActivity.this,attachmentFileDetails); // ad attachment files to adapter
                        fileRecyclerview.setAdapter(adapter);
                    }
                    horizontalLayoutManagaer.scrollToPosition(0);

                }
            });
            return view;

        }

    }
    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<Items> horizontalList;
        public class MyViewHolder extends RecyclerView.ViewHolder {

            RelativeLayout relativeLayout;
            TextView criteriaText;

            public MyViewHolder(View view) {
                super(view);

                criteriaText = (TextView) view.findViewById(R.id.textView12);
                relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
                criteriaText.setTypeface(typeface);

            }
        }

        public HorizontalAdapter(List<Items> horizontalList) {
            this.horizontalList = horizontalList;
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_gallery, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.criteriaText.setText(""+horizontalList.get(position).getPosition().trim()); // set criterion position

            spinnerAdapter.setTextview(holder.criteriaText);
            if(selectedPos == position)
            {
                holder.relativeLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                holder.criteriaText.setTextColor(Color.parseColor("#ffffff"));
            }
            else
            {
                holder.relativeLayout.setBackgroundColor(Color.TRANSPARENT);
                holder.criteriaText.setTextColor(Color.parseColor("#015877"));
            }
            holder.criteriaText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            int cid ;String comment,suggestion;
                            comment = auditCommentText.getText().toString();
                            suggestion = auditobservationText.getText().toString();
                            criteriaId = horizontalList.get(position).getCriterionId();
                            chapterDetails = (ArrayList<Items>) database.getChapterTableDetails
                                    (horizontalList.get(position).getChapterId());
                            chapterText.setText("Ch : "+chapterDetails.get(0).getChapterName());
                            notesScrollView.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                            scrollView.fullScroll(ScrollView.FOCUS_UP);
                            InputMethodManager inputManager = (InputMethodManager)
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                            recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView,null,position+1);
                            pos = position;

                            prevPosition = currentPosition;
                            currentPosition = position;
                            cid = horizontalList.get(prevPosition).getCriterionId();
                            notifyItemChanged(selectedPos);
                            selectedPos = pos;
                            notifyItemChanged(selectedPos);
                            criteriaText.setText(" criteria "+horizontalList.get(position).getPosition());
                            criteriaDescriptionText.setText(horizontalList.get(position).getDescription());
                            if(horizontalList.get(position).getCriterionType().equals("M")) {
                                criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
                            }
                            else {
                                criteriaDescriptionText.setTextColor(Color.parseColor("#000000"));
                            }
                            auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(
                                    subAuditId,criteriaId);

                            auditCommentText.setText( auditCriterionComplianceDetails.get(0).getComment());
                            auditobservationText.setText( auditCriterionComplianceDetails.get(0).getCriterionSuggestion());

                            if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("1")) {
                                    icon1.setImageResource(R.drawable.radio_button_selected);
                                    icon2.setImageResource(R.drawable.radio_button_unselected);
                                    icon3.setImageResource(R.drawable.radio_button_unselected);
                                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    complianceText.setTextColor(Color.parseColor("#ffffff"));
                                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                                    minorNCText.setTextColor(Color.parseColor("#015877"));
                                    suggestionCard.setVisibility(View.GONE);
                                    NCBtnCount = 0;
                                    NACount = 0;

                                }
                                else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("0"))
                                {
                                    icon1.setImageResource(R.drawable.radio_button_unselected);
                                    icon2.setImageResource(R.drawable.radio_button_unselected);
                                    icon3.setImageResource(R.drawable.radio_button_selected);
                                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    complianceText.setTextColor(Color.parseColor("#015877"));
                                    notApllicableText.setTextColor(Color.parseColor("#ffffff"));
                                    minorNCText.setTextColor(Color.parseColor("#015877"));
                                    suggestionCard.setVisibility(View.VISIBLE);
                                    NACount = 0;
                                    compliesBtnCount = 0;
                                }
                                else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("-1"))
                                {
                                    icon1.setImageResource(R.drawable.radio_button_unselected);
                                    icon2.setImageResource(R.drawable.radio_button_selected);
                                    icon3.setImageResource(R.drawable.radio_button_unselected);
                                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                                    complianceText.setTextColor(Color.parseColor("#015877"));
                                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                                    minorNCText.setTextColor(Color.parseColor("#ffffff"));
                                    suggestionCard.setVisibility(View.VISIBLE);
                                    NCBtnCount = 0;
                                    compliesBtnCount = 0;
                                }
                                else
                                {
                                    icon1.setImageResource(R.drawable.radio_button_unselected);
                                    icon2.setImageResource(R.drawable.radio_button_unselected);
                                    icon3.setImageResource(R.drawable.radio_button_unselected);
                                    compliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    nonCompliesLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    notApplicableLayout.setBackground( getResources().getDrawable(R.drawable.button_bg));
                                    complianceText.setTextColor(Color.parseColor("#015877"));
                                    notApllicableText.setTextColor(Color.parseColor("#015877"));
                                    minorNCText.setTextColor(Color.parseColor("#015877"));
                                    suggestionCard.setVisibility(View.VISIBLE);
                                    NCBtnCount = 0;
                                    NACount = 0;
                                    compliesBtnCount = 0;
                                }

                            database.updateCommentToACC(new Items(comment,getCurrentDate(),""),""+auditId,""+subAuditId,""+cid);
                            database.updateSuggestionToACC(new Items(suggestion,getCurrentDate(),"",""),""+auditId,""+subAuditId,""+cid);
                            attachmentFileDetails = (ArrayList<Items>) database.getFilesAttachmentTableDetails(subAuditId,
                                    auditCriterionComplianceDetails.get(0).getAuditCriterionComplianceId());
                            FileAttachmentAdapter adapter = new FileAttachmentAdapter(AuditingActivity.this,attachmentFileDetails);
                            fileRecyclerview.setAdapter(adapter);
                           /* auditCommentText.setText("");
                            auditobservationText.setText("");*/
                            //}

                        }
                    },50);
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }
    public String getCurrentDate()
    {
        long utc = System.currentTimeMillis()/1000;
        return ""+utc;
    }

    public void selectImage() {
        final boolean result= Utility.checkPermission(AuditingActivity.this);
        final Dialog dialogbox = new Dialog(AuditingActivity.this);
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialogbox.setContentView(R.layout.file_popup);

        CardView camCard = (CardView) dialogbox.findViewById(R.id.firstCard);
        CardView galleryCard = (CardView) dialogbox.findViewById(R.id.secondCard);
        TextView cameraText = (TextView) dialogbox.findViewById(R.id.textView26);
        TextView galleryText= (TextView) dialogbox.findViewById(R.id.textView27);
        TextView textView = (TextView) dialogbox.findViewById(R.id.textView28);
        cameraText.setTypeface(typeface);
        galleryText.setTypeface(typeface);
        textView.setTypeface(typeface);

        camCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag =1;
                if(result)
                {
                    cameraIntent();
                    dialogbox.dismiss();
                }
                else  dialogbox.dismiss();

            }
        });
        galleryCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                flag = 0;
                if(result)
                {
                    galleryIntent();
                    dialogbox.dismiss();
                }
                else  dialogbox.dismiss();
            }
        });
        dialogbox.show();

    }
    private void galleryIntent()
    {
        Intent photoPickerIntent = new Intent(
                Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent,
                SELECT_FILE);
    }
    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(flag == 1)
                        cameraIntent();
                    else
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    @SuppressWarnings("deprecation")
    private String onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = null;
        if (data != null) {
            selectedImageUri = data.getData();
            String imagePath = getPath(selectedImageUri);
            String fileName = getFileName(imagePath);
            database.addtoFilesAttachmentTable(new Items(subAuditId,criteriaId,accId,fileName,imagePath,"true",getCurrentDate(),"false","false"));
            attachmentFileDetails = (ArrayList<Items>) database.getFilesAttachmentTableDetails(subAuditId,accId);
            FileAttachmentAdapter adapter = new FileAttachmentAdapter(AuditingActivity.this,attachmentFileDetails);
            fileRecyclerview.setAdapter(adapter);
            Toast.makeText(getApplicationContext(),"Upload successfully..",Toast.LENGTH_SHORT).show();

        }
        return getPath(selectedImageUri);
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        Uri tempUri = getImageUri(getApplicationContext(), photo);


        database.addtoFilesAttachmentTable(new Items(subAuditId,criteriaId,accId,getFileName(getRealPathFromURI(tempUri)),
                getRealPathFromURI(tempUri),"true",getCurrentDate(),"false","false"));
        attachmentFileDetails = (ArrayList<Items>) database.getFilesAttachmentTableDetails(subAuditId,accId);
        FileAttachmentAdapter adapter = new FileAttachmentAdapter(AuditingActivity.this,attachmentFileDetails);
        fileRecyclerview.setAdapter(adapter);
        Toast.makeText(getApplicationContext(),"Upload successfully..",Toast.LENGTH_SHORT).show();

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    public String getPath(Uri uri) {

        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = managedQuery(uri, projection, null, null, null);

        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);

    }

    public String getFileName(String filePath)
    {
        String fileName;
        String[] fileArray;
        fileArray = filePath.split("/");
        fileName = fileArray[fileArray.length-1];
        Log.e("file Name",fileName);
        return fileName;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),BasicAuditDetailsActivity.class);
        startActivity(intent);
    }

}
