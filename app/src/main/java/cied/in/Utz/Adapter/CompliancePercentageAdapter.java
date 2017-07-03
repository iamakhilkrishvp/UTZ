package cied.in.Utz.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;

/**
 * Created by cied on 4/2/17.
 */
public class CompliancePercentageAdapter extends RecyclerView.Adapter<CompliancePercentageAdapter.MyViewHolder> {

    private List<Items> blockDetails;
    Context mcontext;
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> criterionDetailsForMandatory = new ArrayList<Items>();
    ArrayList<Items> criterionDetailsForNormal = new ArrayList<Items>();
    ArrayList<Items> criterionDetails = new ArrayList<Items>();
    ArrayList<Items> accDetails = new ArrayList<Items>();
    ArrayList<Items> totalCriterionList = new ArrayList<Items>();
    String versionName,inspectionYear;
    SessionManager manager;
    PieChart mandatoryPieChart,ordinaryPieChart;
    int subAuditId, compliesCriteriaCountMandatory, nonCompliesCriteriaCountMandatory, notApplicableCriteriaCountMandatory,
        totalMandatoryCriteriaCount,totalNormalCriteriaCount,compliesCriteriaCountNormal,nonCompliesCriteriaCountNormal,
            notApplicableCriteriaCountNormal,compliesPercentageMandatory,nonCompliesPercentageMandatory,
            notApplicablePercentageMandatory,compliesPercentageNormal,nonCompliesPercentageNormal,
            notApplicablePercentageNormal;

    public  int[] COLORS,criteriaIdArray,criteriaIdArrayMandatory,criteriaIdArrayNormal;
    UtzDatabase database;

    public void setSubAuditId(int subAuditId) {
        this.subAuditId = subAuditId;
    }
    public void setVersionName( String versionName) {
        this.versionName = versionName;
    }
    public void setInspectionYear(String inspectionYear){
        this.inspectionYear = inspectionYear;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView text1,text2,chapterNameText,chapterIndexText;
        RelativeLayout relativeLayout;
        public MyViewHolder(View view) {
            super(view);

            text1 = (TextView) view.findViewById(R.id.textView48);
            text2 = (TextView) view.findViewById(R.id.textView49);
            chapterNameText = (TextView) view.findViewById(R.id.textView47);
            chapterIndexText = (TextView) view.findViewById(R.id.textView46);
            mandatoryPieChart = (PieChart) view.findViewById(R.id.chart1) ;
            ordinaryPieChart = (PieChart) view.findViewById(R.id.chart2) ;
            Typeface typeface1 =  Typeface.createFromAsset((mcontext).getAssets(),
                    "font/PANTON-BOLD.OTF");

            manager = new SessionManager(mcontext);
            database = new UtzDatabase(mcontext);
            text1.setTypeface(typeface1);
            text2.setTypeface(typeface1);
            chapterNameText.setTypeface(typeface1);
            chapterIndexText.setTypeface(typeface1);
            COLORS = new int[3];
            COLORS[0] = Color.parseColor("#39B54A");
            COLORS[1] = Color.parseColor("#ED1C24");
            COLORS[2] = Color.parseColor("#C2C2C2");
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public CompliancePercentageAdapter(Context context,List<Items> horizontalList) {
        this.blockDetails = horizontalList;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout_for_compliance_result, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.chapterIndexText.setText("#"+blockDetails.get(position).getBlockLabel());
        holder.chapterNameText.setText(blockDetails.get(position).getBlockName());
        mandatoryPieChart.setUsePercentValues(true);
        mandatoryPieChart.setCenterText("Mandatory \n Criteria");
        mandatoryPieChart.setCenterTextSize(10f);
        mandatoryPieChart.setCenterTextColor(Color.parseColor("#E22727"));

        ordinaryPieChart.setUsePercentValues(true);
        ordinaryPieChart.setCenterText("Additional \n Criteria");
        ordinaryPieChart.setCenterTextSize(10f);
        ordinaryPieChart.setCenterTextColor(Color.parseColor("#000000"));
        criterionDetails = (ArrayList<Items>) database.getCriterionTableDetails(versionName,inspectionYear,blockDetails.
                get(position).getBlockId());
        criteriaIdArray = new int[criterionDetails.size()];
        for (int i =0; i<criterionDetails.size() ; i++) {
            criteriaIdArray[i] = criterionDetails.get(i).getCriterionId();
        }
        criterionDetailsForMandatory = (ArrayList<Items>) database.getCriterionTableDetails(criteriaIdArray,"M",versionName,inspectionYear);
        criterionDetailsForNormal =(ArrayList<Items>) database.getCriterionTableDetails(criteriaIdArray,"O",versionName,inspectionYear);

        totalMandatoryCriteriaCount = criterionDetailsForMandatory.size();
        totalNormalCriteriaCount = criterionDetailsForNormal.size();
        criteriaIdArrayMandatory = new int[criterionDetailsForMandatory.size()];
        criteriaIdArrayNormal = new int[criterionDetailsForNormal.size()];
        for (int i =0; i<criterionDetailsForMandatory.size() ; i++) {
            criteriaIdArrayMandatory[i] = criterionDetailsForMandatory.get(i).getCriterionId();
        }
        for (int i =0; i<criterionDetailsForNormal.size() ; i++) {
            criteriaIdArrayNormal[i] = criterionDetailsForNormal.get(i).getCriterionId();
        }
        compliesCriteriaCountMandatory = getCriteriaCount(criteriaIdArrayMandatory,"1");
        nonCompliesCriteriaCountMandatory = getCriteriaCount(criteriaIdArrayMandatory,"0");
        notApplicableCriteriaCountMandatory = getCriteriaCount(criteriaIdArrayMandatory,"-1");
        /*Log.e("complies   ","   "+compliesCriteriaCountMandatory+"    nonComplies   " +
                ""+nonCompliesCriteriaCountMandatory+" notApplicable   "+notApplicableCriteriaCountMandatory);*/
        compliesCriteriaCountNormal= getCriteriaCount(criteriaIdArrayNormal,"1");
        nonCompliesCriteriaCountNormal = getCriteriaCount(criteriaIdArrayNormal,"0");
        notApplicableCriteriaCountNormal = getCriteriaCount(criteriaIdArrayNormal,"-1");
        if(totalMandatoryCriteriaCount == 0) {

        }
        else{
            compliesPercentageMandatory = getcomplianceValuePercentage
                    (compliesCriteriaCountMandatory,totalMandatoryCriteriaCount);
            nonCompliesPercentageMandatory = getcomplianceValuePercentage
                    (nonCompliesCriteriaCountMandatory,totalMandatoryCriteriaCount);
            notApplicablePercentageMandatory = getcomplianceValuePercentage
                    (notApplicableCriteriaCountMandatory ,totalMandatoryCriteriaCount);
         /*   Log.e("complies Mandatory  ","   "+compliesPercentageMandatory+"    nonCompliesPercentageMandatory " +
                    ""+nonCompliesPercentageMandatory+" notApplicablePercentageMandatory  "+notApplicablePercentageMandatory);*/
            setDataToChart(compliesPercentageMandatory,nonCompliesPercentageMandatory,
                    notApplicablePercentageMandatory);
           /* setDataToChart(mandatoryPieChart,20,30, 50);
            setDataToChart(ordinaryPieChart,20,30, 50);*/
        }

        if(totalNormalCriteriaCount == 0){

        }
        else {
            compliesPercentageNormal = getcomplianceValuePercentage
                    (compliesCriteriaCountNormal,totalNormalCriteriaCount);
            nonCompliesPercentageNormal = getcomplianceValuePercentage
                    (nonCompliesCriteriaCountNormal,totalNormalCriteriaCount);
            notApplicablePercentageNormal = getcomplianceValuePercentage
                    (notApplicableCriteriaCountNormal,totalNormalCriteriaCount);
//            Log.e("notApplicablePercentageNormal",""+notApplicablePercentageNormal);
            setDataToChartNormal(compliesPercentageNormal,nonCompliesPercentageNormal,
                    notApplicablePercentageNormal);
        }
    }

    @Override
    public int getItemCount() {
        return blockDetails.size();
    }

    public int getCriteriaCount(int[] criteriaIdArray,String cv)
    {
        ArrayList<Items> accDetails = new ArrayList<Items>();
        accDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(criteriaIdArray,cv);
        return accDetails.size();
    }
    public void setDataToChart(int cmp,int nonCmp,int notApp){

        ArrayList<Entry> entries = new ArrayList<>();
        if(cmp == 0)
        {
            if(nonCmp == 0 && notApp!=0) {
                COLORS[0] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(notApp, 2));

            }
            else if( nonCmp != 0 && notApp ==0) {
                COLORS[0] = Color.parseColor("#ED1C24");
                entries.add(new Entry(nonCmp, 1));
            }
            else{

                COLORS[0] = Color.parseColor("#ED1C24");
                COLORS[1] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(nonCmp, 0));
                entries.add(new Entry(notApp, 1));
            }
        }
        else if(nonCmp  == 0){

            if(cmp == 0 && notApp!=0) {

                COLORS[0] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(notApp, 0));

            }
            else if( cmp != 0 && notApp ==0) {
                COLORS[0] = Color.parseColor("#39B54A");
                entries.add(new Entry(cmp, 0));
            }
            else{
                COLORS[0] = Color.parseColor("#39B54A");
                COLORS[1] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(cmp, 0));
                entries.add(new Entry(notApp, 1));
            }
        }
        else if(notApp  == 0){
            if(cmp == 0 && nonCmp!=0) {

                COLORS[1] = Color.parseColor("#ED1C24");
                entries.add(new Entry(nonCmp, 0));

            }
            else if( cmp != 0 && nonCmp ==0) {
                COLORS[0] = Color.parseColor("#39B54A");
                entries.add(new Entry(cmp, 0));
            }
            else{
                COLORS[0] = Color.parseColor("#39B54A");
                COLORS[1] = Color.parseColor("#ED1C24");
                entries.add(new Entry(cmp, 0));
                entries.add(new Entry(nonCmp, 1));
            }
        }
        else {
            COLORS[0] = Color.parseColor("#39B54A");
            COLORS[1] = Color.parseColor("#ED1C24");
            COLORS[2] = Color.parseColor("#C2C2C2");
            entries.add(new Entry(cmp, 0));
            entries.add(new Entry(nonCmp, 1));
            entries.add(new Entry(notApp, 2));
        }
        PieDataSet dataset = new PieDataSet(entries, "");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("");
        labels.add("");
        labels.add("");
        PieData data = new PieData(labels, dataset);
        data.setValueTextSize(10f);
        dataset.setColors(COLORS);
        dataset.setValueTextColor(Color.parseColor("#000000"));
        dataset.setHighlightEnabled(true);
        mandatoryPieChart.setDescription("");
        mandatoryPieChart.setData(data);

    }

    public void setDataToChartNormal(int cmp,int nonCmp,int notApp){

        ArrayList<Entry> entries = new ArrayList<>();
        if(cmp == 0)
        {
            if(nonCmp == 0 && notApp!=0) {
                COLORS[0] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(notApp, 2));

            }
            else if( nonCmp != 0 && notApp ==0) {
                COLORS[0] = Color.parseColor("#ED1C24");
                entries.add(new Entry(nonCmp, 1));
            }
            else{

                COLORS[0] = Color.parseColor("#ED1C24");
                COLORS[1] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(nonCmp, 0));
                entries.add(new Entry(notApp, 1));
            }
        }
        else if(nonCmp  == 0){

            if(cmp == 0 && notApp!=0) {

                COLORS[0] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(notApp, 0));

            }
            else if( cmp != 0 && notApp ==0) {
                COLORS[0] = Color.parseColor("#39B54A");
                entries.add(new Entry(cmp, 0));
            }
            else{
                COLORS[0] = Color.parseColor("#39B54A");
                COLORS[1] = Color.parseColor("#C2C2C2");
                entries.add(new Entry(cmp, 0));
                entries.add(new Entry(notApp, 1));
            }
        }
        else if(notApp  == 0){
            if(cmp == 0 && nonCmp!=0) {

                COLORS[1] = Color.parseColor("#ED1C24");
                entries.add(new Entry(nonCmp, 0));

            }
            else if( cmp != 0 && nonCmp ==0) {
                COLORS[0] = Color.parseColor("#39B54A");
                entries.add(new Entry(cmp, 0));
            }
            else{
                COLORS[0] = Color.parseColor("#39B54A");
                COLORS[1] = Color.parseColor("#ED1C24");
                entries.add(new Entry(cmp, 0));
                entries.add(new Entry(nonCmp, 1));
            }
        }
        else {
            COLORS[0] = Color.parseColor("#39B54A");
            COLORS[1] = Color.parseColor("#ED1C24");
            COLORS[2] = Color.parseColor("#C2C2C2");
            entries.add(new Entry(cmp, 0));
            entries.add(new Entry(nonCmp, 1));
            entries.add(new Entry(notApp, 2));
        }
        PieDataSet dataset = new PieDataSet(entries, "");
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("");
        labels.add("");
        labels.add("");
        PieData data = new PieData(labels, dataset);
        data.setValueTextSize(10f);
        dataset.setColors(COLORS);
        dataset.setValueTextColor(Color.parseColor("#000000"));
        dataset.setHighlightEnabled(true);
        ordinaryPieChart.setDescription("");
        ordinaryPieChart.setData(data);

    }
    public int getcomplianceValuePercentage(int a,int b){
        int result;
        result = (a*100)/b;
        return result;
    }
}