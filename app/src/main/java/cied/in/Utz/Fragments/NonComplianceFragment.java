package cied.in.Utz.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
 * Created by cied on 24/2/17.
 */
public class NonComplianceFragment extends Fragment {
    TextView spinnerText,text1,text2,text3,criteriaDescriptionText,ACCResultText,
            blockNameText,chapterText,messageText;
    Spinner spinner;
    ArrayList<Items> mandatoryCriterionDetailList = new ArrayList<Items>();
    ArrayList<Items> additionaCriterionDetailList = new ArrayList<Items>();

    ArrayList<Items> spinnerValues = new ArrayList<Items>();
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> totalCriterionList = new ArrayList<Items>();
    ArrayList<Items> NCCriteriaForMand = new ArrayList<Items>();
    ArrayList<Items> NCCriteriaForAdditional = new ArrayList<Items>();
    ArrayList<Items> auditCriterionComplianceDetails = new ArrayList<Items>();
    ArrayList<Items> nonComplianceMandatoryCriterias = new ArrayList<Items>();
    ArrayList<Items> nonComplianceAdditionalCriterias = new ArrayList<Items>();
    UtzDatabase database;
    EditText commentText,suggestionText;
    SessionManager manager;
    int auditId,subAuditId,selectedPos=0,selectedChapterId,selectedPosition=1,selectedChapterPosition,chapterId;;
    int[] additionalCriterionArray,criterionIdArray,mandCriterionArray,mandCriterias,additionalCriteria;
    String versionName,inspectionYear;
    Typeface typeface;
    int pos = 0;
    ListView listView;
    HorizontalAdapter horizontalAdapter;
    RelativeLayout spinnerLayout,relativeLayout,biggerLayout;
    RecyclerView recyclerView;
    SpinnerAdapter spinnerAdapter;
    LinearLayoutManager horizontalLayoutManagaer;

    public NonComplianceFragment() {
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
        View view = inflater.inflate(R.layout.non_compliance_fragment, container, false);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),
                "font/PANTON-BOLD.OTF");

        listView = (ListView) view.findViewById(R.id.listView);
        text1 = (TextView) view.findViewById(R.id.text1);
        text3 = (TextView) view.findViewById(R.id.text2);
        text2 = (TextView) view.findViewById(R.id.text3);
        messageText = (TextView) view.findViewById(R.id.textView90);
        chapterText = (TextView) view.findViewById(R.id.chapterNameText);
        criteriaDescriptionText = (TextView) view.findViewById(R.id.criteraDescText);
        ACCResultText = (TextView) view.findViewById(R.id.resultText);
        blockNameText = (TextView) view.findViewById(R.id.textView39);
        spinner = (Spinner) view.findViewById(R.id.spinner2);
        spinnerLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout13);
        biggerLayout = (RelativeLayout) view.findViewById(R.id.bigger);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        commentText = (EditText) view.findViewById(R.id.editText1);
        suggestionText = (EditText) view.findViewById(R.id.editText);

        text1.setTypeface(typeface);
        text3.setTypeface(typeface);
        text2.setTypeface(typeface);
        messageText.setTypeface(typeface);
        chapterText.setTypeface(typeface);
        criteriaDescriptionText.setTypeface(typeface);
        ACCResultText.setTypeface(typeface);
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

        auditCriterionComplianceDetails = (ArrayList<Items>) database.
                getAuditCriterionComplianceDetails(subAuditId,"0");
        if(auditCriterionComplianceDetails.size() == 0){
            relativeLayout.setVisibility(View.VISIBLE);
            biggerLayout.setVisibility(View.GONE);
        }
        else {
            relativeLayout.setVisibility(View.GONE);
            biggerLayout.setVisibility(View.VISIBLE);
            Log.e("non comp...size....",""+auditCriterionComplianceDetails.size());
            criterionIdArray = new int[auditCriterionComplianceDetails.size()];

            for (int i=0; i<auditCriterionComplianceDetails.size() ; i++) {
                criterionIdArray[i] = auditCriterionComplianceDetails.get(i).getCriterionId();
            }
            mandatoryCriterionDetailList = (ArrayList<Items>) database.
                    getCriterionTableDetailsResult(criterionIdArray,"M",inspectionYear);
            Log.e("mandatory....",""+mandatoryCriterionDetailList.size());

            additionaCriterionDetailList = (ArrayList<Items>) database.
                    getCriterionTableDetailsResult(criterionIdArray,"O",inspectionYear);

            horizontalAdapter = new HorizontalAdapter(mandatoryCriterionDetailList);
            recyclerView.setAdapter(horizontalAdapter);
            auditCriterionComplianceDetails = (ArrayList<Items>) database.getAuditCriterionComplianceDetails(
                    subAuditId,  mandatoryCriterionDetailList.get(0).getCriterionId());
            suggestionText.setText(auditCriterionComplianceDetails.get(0).getCriterionSuggestion());
            commentText.setText(auditCriterionComplianceDetails.get(0).getComment());
            chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(
                    mandatoryCriterionDetailList.get(0).getChapterId());
            chapterText.setText(chapterDetails.get(0).getChapterName());
            criteriaDescriptionText.setText(mandatoryCriterionDetailList.get(0).getDescription());
            criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
            ACCResultText.setText("NON COMPLIANCE");
            blockNameText.setText("Mandatory Criterias");

        }

        spinnerValues.clear();
        spinnerValues.add(new Items("Mandatory Criterias"));
        spinnerValues.add(new Items("Additional Criterias"));
        spinnerAdapter = new SpinnerAdapter(getActivity(),spinnerValues);
        spinner.setAdapter(spinnerAdapter);
        spinnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockNameText.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
                spinner.performClick();
            }
        });

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

            TextView spinnerText;
            RelativeLayout relativeLayout;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final int positions = position;
            ViewHolder holder;
            holder = new ViewHolder();
            if (view == null) {

                view = inflater.inflate(R.layout.custom_result_spinner, null);

                holder.spinnerText = (TextView) view.findViewById(R.id.textView11);
                holder.relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);
                holder.spinnerText.setTypeface(typeface);

                view.setTag(holder);

            } else {
                holder = (ViewHolder) view.getTag();
            }


            holder.spinnerText.setText(msg.get(positions).getVersionName());
            final ViewHolder finalHolder = holder;
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    ArrayList<Items> chapterDetails = new ArrayList<Items>();
                    spinner.setVisibility(View.INVISIBLE);
                    blockNameText.setVisibility(View.VISIBLE);
                    InputMethodManager inputManager = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow( getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    selectedPosition = position+1;
                    if(msg.get(positions).getVersionName().equals("Mandatory Criterias")){

                        finalHolder.spinnerText.setText("Mandatory Criterias");
                        selectedPos=0;
                        horizontalAdapter = new HorizontalAdapter(mandatoryCriterionDetailList);
                        recyclerView.setAdapter(horizontalAdapter);
                        notifyDataSetChanged();
                        chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(
                                mandatoryCriterionDetailList.get(0).getChapterId());
                        chapterText.setText(chapterDetails.get(0).getChapterName());
                        criteriaDescriptionText.setText(mandatoryCriterionDetailList.get(0).getDescription());
                        criteriaDescriptionText.setTextColor(Color.parseColor("#ff0000"));
                        blockNameText.setText("Mandatory Criterias");

                    }

                    else{
                        finalHolder.spinnerText.setText("Additional Criterias");
                        selectedPos=0;
                        horizontalAdapter = new HorizontalAdapter(additionaCriterionDetailList);
                        recyclerView.setAdapter(horizontalAdapter);
                        notifyDataSetChanged();
                        chapterDetails = (ArrayList<Items>) database.getChapterTableDetails(
                                additionaCriterionDetailList.get(0).getChapterId());
                        chapterText.setText(chapterDetails.get(0).getChapterName());
                        criteriaDescriptionText.setText(additionaCriterionDetailList.get(0).getDescription());
                        criteriaDescriptionText.setTextColor(Color.parseColor("#000000"));
                        blockNameText.setText("Additional Criterias");

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
                            suggestionText.setText(auditCriterionComplianceDetails.get(0).getCriterionSuggestion());
                            commentText.setText(auditCriterionComplianceDetails.get(0).getComment());
                            ACCResultText.setText("NON COMPLIANCE");

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
