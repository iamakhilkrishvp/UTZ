package cied.in.Utz.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;

/**
 * Created by cied on 31/1/17.
 */
public class ScoringResultFragment extends Fragment {
    TextView spinnerText,text1,text2,text3,criteriaDescriptionText,ACCResultText, blockIndexText, blockNameText,chapterText;
    Spinner spinner;
    ArrayList<Items> criterionDetailList = new ArrayList<Items>();
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> blockDetailsList = new ArrayList<Items>();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> totalCriterionList = new ArrayList<Items>();
    ArrayList<Items> auditChapterComplianceDetails = new ArrayList<Items>();
    ArrayList<Items> auditCriterionComplianceDetails = new ArrayList<Items>();
    ArrayList<Items> versionDetailsForCriterias = new ArrayList<Items>();
    UtzDatabase database;
    SessionManager manager;
    int auditId,subAuditId,selectedPos=0,selectedChapterId,selectedPosition=1,selectedChapterPosition,chapterId;;
    int[] chapterIdsArray,criterionIdArray,blockIdArray;
    String versionName,inspectionYear;
    Typeface typeface;
    int pos = 0;
    ListView listView;
    RelativeLayout spinnerLayout;
    SpinnerAdapter spinnerAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager horizontalLayoutManagaer;
    HorizontalAdapter horizontalAdapter;


    public ScoringResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.scoringresult_fragment_layout, container, false);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),
                "font/PANTON-BOLD.OTF");

        listView = (ListView) view.findViewById(R.id.listView);
        text1 = (TextView) view.findViewById(R.id.text1);
        text3 = (TextView) view.findViewById(R.id.text2);
        text2 = (TextView) view.findViewById(R.id.text3);
        chapterText = (TextView) view.findViewById(R.id.chapterNameText);
        criteriaDescriptionText = (TextView) view.findViewById(R.id.criteraDescText);
        ACCResultText = (TextView) view.findViewById(R.id.resultText);
        blockIndexText = (TextView) view.findViewById(R.id.textView3);
        blockNameText = (TextView) view.findViewById(R.id.textView5);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        spinnerLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout13);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        text1.setTypeface(typeface);
        text3.setTypeface(typeface);
        text2.setTypeface(typeface);
        chapterText.setTypeface(typeface);
        criteriaDescriptionText.setTypeface(typeface);
        ACCResultText.setTypeface(typeface);
        blockIndexText.setTypeface(typeface);
        blockNameText.setTypeface(typeface);
        horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        manager = new SessionManager(getActivity());
        database = new UtzDatabase(getActivity());
        final HashMap<String,String> subAuditid = manager.getSubAuditId();
        subAuditId = Integer.parseInt(subAuditid.get(SessionManager.KEY_SUBAUDITID));
        final HashMap<String,String> auditid = manager.getAuditId();
        this.auditId = Integer.parseInt(auditid.get(SessionManager.KEY_AUDITID));
        commonAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails(auditId);  // get commonAudit Details from commonAuditDetailsTable
        versionName = commonAuditDetails.get(0).getVersionName();
        inspectionYear = commonAuditDetails.get(0).getInspectionYear();
        totalCriterionList = (ArrayList<Items>) database.getCriterionTableDetails(versionName,inspectionYear);
        chapterIdsArray = new int[totalCriterionList.size()];
        for (int i =0; i<totalCriterionList.size() ; i++) {
            chapterIdsArray[i] = totalCriterionList.get(i).getChapterId();
        }
        chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(chapterIdsArray);
        blockIdArray = new int[chapterDetails.size()];
        for (int i =0; i<chapterDetails.size() ; i++)
        {

            blockIdArray[i] = chapterDetails.get(i).getBlockId();
        }
        blockDetailsList = (ArrayList<Items>) database.getBlockDetails(blockIdArray);
        spinnerAdapter = new SpinnerAdapter(getActivity(),blockDetailsList);
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
        horizontalAdapter = new HorizontalAdapter(criterionDetailList);  // set criterion details to criterion-recyclerview
        recyclerView.setAdapter(horizontalAdapter);
        chapterText.setText(chapterDetails.get(0).getChapterName());
        criteriaDescriptionText.setText(criterionDetailList.get(0).getDescription()); // set critera description of first postion
        if(criterionDetailList.get(0).getCriterionType().equals("M"))
        {
            criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
        }
        else
        {
            criteriaDescriptionText.setTextColor(Color.parseColor("#000000"));
        }
        if(blockDetailsList.get(0).getBlockName().length()<= 15)
        {
            blockIndexText.setText(blockDetailsList.get(0).getBlockLabel()+": ");
            blockNameText.setText(blockDetailsList.get(0).getBlockName());
        }
        else {
            blockIndexText.setText(blockDetailsList.get(0).getBlockName()+": ");
            String subString = blockDetailsList.get(0).getBlockLabel().substring(0,14)+"...";
            blockNameText.setText(subString);
        }
        auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails
                (subAuditId,criterionDetailList.get(0).getCriterionId()); // get audit criteiondetails of first chapter of particular audit 'subAuditId'
        if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("1")) {
            ACCResultText.setText("COMPLIES");
        }
        else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("0"))
        {
            ACCResultText.setText(" NON COMPLIANCE");
        }
        else
        {
            ACCResultText.setText("NOT APPLICABLE");
        }
        return view;

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


            holder.spinnerText.setText(msg.get(positions).getBlockName());
            holder.blockText.setText(msg.get(positions).getBlockLabel() +" . ");

            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    ArrayList<Items> chapterDetails = new ArrayList<Items>();
                    spinner.setVisibility(View.GONE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow( getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    selectedPosition = position+1;
                    selectedChapterId = msg.get(positions).getChapterId();
                    chapterId = selectedChapterId;
                    if(msg.get(position).getBlockName().length()<= 15)
                    {
                        blockIndexText.setText(msg.get(positions).getBlockLabel()+":");
                        blockNameText.setText(msg.get(positions).getBlockName());

                    }
                    else {
                        blockIndexText.setText(msg.get(positions).getBlockLabel()+": ");
                        String subString = (msg.get(positions).getBlockName()).substring(0,14)+"...";
                        blockNameText.setText(subString);

                    }

                    criterionDetailList = (ArrayList<Items>) database.getCriterionTableDetails
                            (msg.get(position).getBlockId(),versionName, inspectionYear,1);
                    selectedPos=0;
                    HorizontalAdapter horizontalAdapter = new HorizontalAdapter(criterionDetailList);
                    recyclerView.setAdapter(horizontalAdapter);
                    notifyDataSetChanged();
                    chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(criterionDetailList.get(0).getChapterId());
                    chapterText.setText("Ch : "+chapterDetails.get(0).getChapterName());
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
                    if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("1"))
                    {
                        ACCResultText.setText("COMPLIES");
                    }
                    else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("0"))
                    {
                        ACCResultText.setText("NON COMPLIANCE");
                    }
                    else
                    {
                        ACCResultText.setText("NOT APPLICABLE");
                    }
                    notifyDataSetChanged();

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

            holder.criteriaText.setText(""+horizontalList.get(position).getPosition());

            if(selectedPos == position) {
                holder.relativeLayout.setBackground( getResources().getDrawable(R.drawable.spinner_bg));
                holder.criteriaText.setTextColor(Color.parseColor("#ffffff"));
            }
            else {
                holder.relativeLayout.setBackgroundColor(Color.TRANSPARENT);
                holder.criteriaText.setTextColor(Color.parseColor("#015877"));
            }

            holder.criteriaText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int criteriaId = horizontalList.get(position).getCriterionId();
                            chapterDetails = (ArrayList<Items>) database.getChapterTableDetails
                                    (horizontalList.get(position).getChapterId());
                            chapterText.setText("Ch : "+chapterDetails.get(0).getChapterName());
                            recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView,null,position+1);
                            pos = position;
                            notifyItemChanged(selectedPos);
                            selectedPos = pos;
                            notifyItemChanged(selectedPos);
                            criteriaDescriptionText.setText(horizontalList.get(position).getDescription());
                            if(horizontalList.get(position).getCriterionType().equals("M")) {
                                criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
                            }
                            else {
                                criteriaDescriptionText.setTextColor(Color.parseColor("#000000"));
                            }
                            auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(
                                    subAuditId,criteriaId);
                            if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("1")) {
                                ACCResultText.setText("COMPLIES");
                            }
                            else if(auditCriterionComplianceDetails.get(0).getComplianceValue().equals("0")) {
                                ACCResultText.setText("NON COMPLIANCE");
                            }
                            else{
                                ACCResultText.setText("NOT APPLICABLE");
                            }
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
}
