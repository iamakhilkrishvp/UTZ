package cied.in.Utz.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import cied.in.Utz.Adapter.CompliancePercentageAdapter;
import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;

/**
 * Created by cied on 31/1/17.
 */
public class ComplianceFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Items> accDetails = new ArrayList<Items>();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> blockDetailsList = new ArrayList<Items>();
    ArrayList<Items> blockDetails = new ArrayList<Items>();
    ArrayList<Items> totalCriterionList = new ArrayList<Items>();
    ArrayList<Items> versionDetails = new ArrayList<Items>();
    String versionName,inspectionYear;
    UtzDatabase database;
    int [] blockIdsArray,criterionArray;
    Typeface typeface;
    SessionManager manager;
    int auditId,subAuditId;
    TextView textView1,textView2,textView3;
    CompliancePercentageAdapter adapter;
    public ComplianceFragment() {
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
        View view = inflater.inflate(R.layout.compliane_fragment_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        textView1 = (TextView) view.findViewById(R.id.textView33);
        textView2 = (TextView) view.findViewById(R.id.textView34);
        textView3 = (TextView) view.findViewById(R.id.textView37);
        typeface = Typeface.createFromAsset(getActivity().getAssets(),
                "font/PANTON-BOLD.OTF");
        textView1.setTypeface(typeface);
        textView2.setTypeface(typeface);
        textView3.setTypeface(typeface);
        LinearLayoutManager layoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagaer);

        database = new UtzDatabase(getActivity());
        manager = new SessionManager(getActivity());

        final HashMap<String,String> subAuditid = manager.getSubAuditId();
        subAuditId = Integer.parseInt(subAuditid.get(SessionManager.KEY_SUBAUDITID));
        final HashMap<String,String> userId = manager.getAuditId();
        auditId = Integer.parseInt(userId.get(SessionManager.KEY_AUDITID));

        commonAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails(auditId);  // get commonAudit Details from commonAuditDetailsTable
        versionName = commonAuditDetails.get(0).getVersionName();
        inspectionYear = commonAuditDetails.get(0).getInspectionYear();
        totalCriterionList = (ArrayList<Items>) database.getCriterionTableDetails(versionName,inspectionYear);
        blockIdsArray = new int[totalCriterionList.size()];
        for (int i =0; i<totalCriterionList.size() ; i++)
        {
            blockIdsArray[i] = totalCriterionList.get(i).getBlockId();
        }

        blockDetails = (ArrayList<Items>) database.getBlockDetails(blockIdsArray);
        adapter = new CompliancePercentageAdapter(getActivity(), blockDetails);
        adapter.setSubAuditId(subAuditId);
        adapter.setVersionName(versionName);
        adapter.setInspectionYear(inspectionYear);
        recyclerView.setAdapter(adapter);
        return view;

    }
}
