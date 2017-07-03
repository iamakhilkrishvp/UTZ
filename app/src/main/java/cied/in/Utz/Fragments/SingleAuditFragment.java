package cied.in.Utz.Fragments;

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

import cied.in.Utz.Adapter.SingleAuditAdapter;
import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.Model.URL;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;

/**
 * Created by cied on 31/1/17.
 */
public class SingleAuditFragment extends Fragment {
    ArrayList<Items> singleAuditDetails = new ArrayList<Items>();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    ArrayList<Items> chapterDetails = new ArrayList<Items>();
    ArrayList<Items> criterionDetails = new ArrayList<Items>();
    ArrayList<Items> versionDetails = new ArrayList<Items>();
    String[] versionNamesArray;
    RecyclerView recyclerView;
    int id = 201,iteration;
    String auditId,getAuditDetailsUrl,accessToken,auditorId,userType;
    URL url;
    SessionManager sessionManager;
    TextView textView;
    UtzDatabase database;

    public SingleAuditFragment() {
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
        View view = inflater.inflate(R.layout.all_audit_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.textView9);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagaer);
        sessionManager = new SessionManager(getActivity());
        database = new UtzDatabase(getActivity());
        final HashMap<String,String> userDetails = sessionManager.getUserDetails();
        accessToken = userDetails.get(SessionManager.KEY_ACCESS_TOKEN);
        auditorId  = userDetails.get(SessionManager.KEY_USERID);

        singleAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails("singleaudit");
        if(singleAuditDetails.size() ==0 )
        {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("No Single Audits Obtained..");
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        Log.e("singleAuditDetails size",""+singleAuditDetails.size());
        SingleAuditAdapter singleAuditAdapter = new SingleAuditAdapter(getActivity(), singleAuditDetails);
        recyclerView.setAdapter(singleAuditAdapter);

        return view;

    }
}
