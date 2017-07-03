package cied.in.Utz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.SharedPreferance.SessionManager;

public class EditAuditDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView text1,text2,text3, text10,text11,text12,text13,text14,text15,text16,text17,text18,text19,
            text20,text21,text22,text23,text24,text25,text26;
    EditText startDateText, endDateText, productionAreaText,
            addressText,districtText,phoneNumberText,tehsilText, workingHoursText,identificationNumberText,
            permenentMaleText, permenentFemaleText, productionMinedText,productionCapacityText, mineTypeText,
            tempMaleText, tempFemaleText, legalStatusText,personInChargeText;
    Typeface typeface,tf;
    TextView saveButton,headerText;
    String startDate,endDate, productionArea,address,district,phoneNumber,tehsil, workingHours,identificationNumber, permenentMaleWorkers, permenentFemaleWorkers, productionMined,
            productionCapacity, mineType, TempMaleWorkers, TempFemaleWorkers, legalStatus,personInCharge;
    ImageView backIcon;
    int auditId,subAuditId;
    DatePicker startDatePicker,endDatePicker;
    SessionManager manager;
    UtzDatabase database;
    ArrayList<Items> subAuditDetails = new ArrayList<Items>();
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog startDatePickerDialog,endDatePickerDialog;
    static final int DATE_PICKER_ID = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_audit_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text1 = (TextView) findViewById(R.id.text1);    text10 = (TextView) findViewById(R.id.text10);
        text2 = (TextView) findViewById(R.id.text2);    text11 = (TextView) findViewById(R.id.text11);
        text3 = (TextView) findViewById(R.id.text3);    text12 = (TextView) findViewById(R.id.text12);
        text13 = (TextView) findViewById(R.id.text13);  text22 = (TextView) findViewById(R.id.text22);
        text14 = (TextView) findViewById(R.id.text14);  text23 = (TextView) findViewById(R.id.text23);
        text15 = (TextView) findViewById(R.id.text15);  text24 = (TextView) findViewById(R.id.text24);
        text16 = (TextView) findViewById(R.id.text16);  text24 = (TextView) findViewById(R.id.text24);
        text17 = (TextView) findViewById(R.id.text17);  text26 = (TextView) findViewById(R.id.text26);
        text18 = (TextView) findViewById(R.id.text18);  text19 = (TextView) findViewById(R.id.text19);
        text20 = (TextView) findViewById(R.id.text20);  text21 = (TextView) findViewById(R.id.text21);
        text25 = (TextView) findViewById(R.id.text25);  saveButton = (TextView) findViewById(R.id.textView17);

        backIcon = (ImageView) toolbar.findViewById(R.id.imageView7);
        headerText = (TextView) toolbar.findViewById(R.id.textView14);
        startDateText = (EditText) findViewById(R.id.startDateText);
        endDateText = (EditText) findViewById(R.id.endDateText);
        productionAreaText = (EditText) findViewById(R.id.farmAreaText);
        addressText = (EditText) findViewById(R.id.addressText);
        districtText = (EditText) findViewById(R.id.districtText);
        phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);
        tehsilText = (EditText) findViewById(R.id.tehsilText);
        workingHoursText = (EditText) findViewById(R.id.villageText);
        identificationNumberText = (EditText) findViewById(R.id.idNumberText);
        permenentMaleText = (EditText) findViewById(R.id.CFAText);
        permenentFemaleText = (EditText) findViewById(R.id.totalPdctionAreaText);
        productionMinedText = (EditText) findViewById(R.id.areaUnderCastorText);
        productionCapacityText = (EditText) findViewById(R.id.productionCapacityText);
        mineTypeText = (EditText) findViewById(R.id.yearsInCastorPdctText);
        tempMaleText = (EditText) findViewById(R.id.ownLandAreaText);
        tempFemaleText = (EditText) findViewById(R.id.leasedStatusText);
        legalStatusText = (EditText) findViewById(R.id.leasedAreaText);
        personInChargeText = (EditText) findViewById(R.id.personInChargeText);
        startDatePicker = (DatePicker) findViewById(R.id.datePicker);
        endDatePicker = (DatePicker) findViewById(R.id.datePicker2);

        manager = new SessionManager(this);
        database = new UtzDatabase(this);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        tf = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/Roboto-Regular.ttf");
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
        text1.setTypeface(typeface);
        headerText.setTypeface(typeface);
        text10.setTypeface(typeface);
        saveButton.setTypeface(typeface);
        text2.setTypeface(tf);  text12.setTypeface(tf);    text26.setTypeface(tf);
        text3.setTypeface(tf);  text13.setTypeface(tf);
        text14.setTypeface(tf);
        text15.setTypeface(tf);
        text11.setTypeface(tf);   text16.setTypeface(tf);
        text17.setTypeface(tf);  text20.setTypeface(tf);  text23.setTypeface(tf);
        text18.setTypeface(tf);  text21.setTypeface(tf);  text24.setTypeface(tf);
        text19.setTypeface(tf);  text22.setTypeface(tf);  text25.setTypeface(tf);
        districtText.setTypeface(tf);
        phoneNumberText.setTypeface(tf);
        productionAreaText.setTypeface(tf);
        tehsilText.setTypeface(tf);
        addressText.setTypeface(tf);
        workingHoursText.setTypeface(tf);
        identificationNumberText.setTypeface(tf);
        productionCapacityText.setTypeface(tf);
        tempFemaleText.setTypeface(tf);
        permenentMaleText.setTypeface(tf);
        legalStatusText.setTypeface(tf);
        permenentFemaleText.setTypeface(tf);
        tempMaleText.setTypeface(tf);
        personInChargeText.setTypeface(tf);
        productionMinedText.setTypeface(tf);
        startDateText.setKeyListener(null);
        endDateText.setKeyListener(null);

        final HashMap<String,String> userId = manager.getAuditId();
        auditId = Integer.parseInt(userId.get(SessionManager.KEY_AUDITID));
        Intent intent = getIntent();
        startDate = intent.getStringExtra("STARTDATE");
        endDate = intent.getStringExtra("ENDDATE");
        subAuditDetails = (ArrayList<Items>) database.getSubAuditDetailsTable(auditId);
        startDateText.setText(startDate);
        endDateText.setText(endDate);
        subAuditId = subAuditDetails.get(0).getSubAuditId();
        phoneNumberText.setText(subAuditDetails.get(0).getPhoneNumber());
        identificationNumberText.setText(subAuditDetails.get(0).getIdentificationNumber());
        permenentMaleText.setText(subAuditDetails.get(0).getPermenentMaleWorkers());
        permenentFemaleText.setText(subAuditDetails.get(0).getPermenentFemaleWorkers());
        productionMinedText.setText(subAuditDetails.get(0).getTypeOfProcess());
        productionAreaText.setText(subAuditDetails.get(0).getTotalArea());
        addressText.setText(subAuditDetails.get(0).getAddress());
        productionCapacityText.setText(subAuditDetails.get(0).getTotalProduction());
        legalStatusText.setText(subAuditDetails.get(0).getLeagalStatus());
        tempMaleText.setText(subAuditDetails.get(0).getTempMaleWorkers());
        tehsilText.setText(subAuditDetails.get(0).getCity());
        workingHoursText.setText(subAuditDetails.get(0).getFarmName());
        tempFemaleText.setText(subAuditDetails.get(0).getTempFemaleWorkers());
        personInChargeText.setText(subAuditDetails.get(0).getPersonInCharge());
        setDateTimeField();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEditDetails();
            }
        });
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),BasicAuditDetailsActivity.class);
                startActivity(intent1);
            }
        });



    }
    private void setDateTimeField() {
        startDateText.setOnClickListener(this);
        endDateText.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        startDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                startDateText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        endDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                endDateText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    @Override
    public void onClick(View v) {

        if(v == startDateText)
        {
            startDatePickerDialog.show();
        }
        else if(v == endDateText)
        {
            endDatePickerDialog.show();
        }

    }
    public void updateEditDetails()
    {
        startDate = startDateText.getText().toString();
        endDate = endDateText.getText().toString();
        productionArea = productionAreaText.getText().toString();
        address = addressText.getText().toString();
        phoneNumber = phoneNumberText.getText().toString();
        tehsil = tehsilText.getText().toString();
        workingHours = workingHoursText.getText().toString();
        identificationNumber = identificationNumberText.getText().toString();
        permenentMaleWorkers = permenentMaleText.getText().toString();
        permenentFemaleWorkers = permenentFemaleText.getText().toString();
        productionMined = productionMinedText.getText().toString();
        productionCapacity = productionCapacityText.getText().toString();
        mineType = mineTypeText.getText().toString();
        TempMaleWorkers = tempMaleText.getText().toString();
        TempFemaleWorkers = tempFemaleText.getText().toString();
        legalStatus = legalStatusText.getText().toString();
        personInCharge = personInChargeText.getText().toString();
        district = districtText.getText().toString();

        database.updateCommonAuditDetails(new Items(startDate,endDate),""+auditId);
        database.updateSubAuditDetailsTable(new Items(productionArea,address,phoneNumber,tehsil, workingHours,identificationNumber, permenentMaleWorkers, permenentFemaleWorkers, productionMined
                ,productionCapacity, mineType, TempMaleWorkers, TempFemaleWorkers, legalStatus,personInCharge,district),""+subAuditId);
        Intent intent = new Intent(getApplicationContext(),BasicAuditDetailsActivity.class);
        startActivity(intent);

    }

}
