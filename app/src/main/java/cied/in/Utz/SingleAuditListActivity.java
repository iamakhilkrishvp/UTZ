package cied.in.Utz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cied.in.Utz.Adapter.SingleAuditAdapter;
import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.Model.URL;
import cied.in.Utz.SharedPreferance.SessionManager;

public class SingleAuditListActivity extends AppCompatActivity {

    ArrayList<Items> singleAuditDetails = new ArrayList<Items>();
    ArrayList<Items> commonAuditDetails = new ArrayList<Items>();
    String[] versionNamesArray;
    RecyclerView recyclerView;
    int id = 201,iteration;
    String auditId,year,status,category,userType;
    URL url;
    SessionManager sessionManager;
    TextView headerText;
    UtzDatabase database;
    ImageView backArrow;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_audit_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headerText = (TextView) toolbar.findViewById(R.id.textView14);
        backArrow = (ImageView) toolbar.findViewById(R.id.imageView7);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "font/PANTON-BOLD.OTF");
        LinearLayoutManager layoutManagaer
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManagaer);
        database = new UtzDatabase(this);
        headerText.setTypeface(typeface);
        Bundle extras = getIntent().getExtras();
        auditId = extras.getString("AUDIT_ID");
        commonAuditDetails = (ArrayList<Items>) database.getCommonAuditTableDetails(Integer.parseInt(auditId));
        year = commonAuditDetails.get(0).getYear();
        status = commonAuditDetails.get(0).getStatus();
        category = commonAuditDetails.get(0).getCategory();
        singleAuditDetails = (ArrayList<Items>) database.getSubAuditDetailsTable(Integer.parseInt(auditId),"");
        SingleAuditAdapter singleAuditAdapter = new SingleAuditAdapter(getApplicationContext(),singleAuditDetails);
        recyclerView.setAdapter(singleAuditAdapter);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    public class SingleAuditAdapter extends RecyclerView.Adapter<SingleAuditAdapter.MyViewHolder> {

        private List<Items> auditDetailsList;
        Context mcontext;
        SessionManager manager;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView statusImage,categoryImageView;
            TextView text1,text2,text3,text4,text5 ,auditIdText,firmNameText,auditTypeText,auditStatusText,auditCategoryText;
            RelativeLayout relativeLayout;
            ImageView icon;
            public MyViewHolder(View view) {
                super(view);

                icon = (ImageView) view.findViewById(R.id.imageView3);
                text1 = (TextView) view.findViewById(R.id.text1);
                text2 = (TextView) view.findViewById(R.id.text2);
                text3 = (TextView) view.findViewById(R.id.text3);
                text4 = (TextView) view.findViewById(R.id.text4);
                text5 = (TextView) view.findViewById(R.id.text5);
                auditIdText = (TextView) view.findViewById(R.id.auditIdText);
                firmNameText = (TextView) view.findViewById(R.id.firmText);
                auditTypeText = (TextView) view.findViewById(R.id.auditTypeText);
                auditStatusText =(TextView) view.findViewById(R.id.statusText);
                auditCategoryText = (TextView) view.findViewById(R.id.auditCategoryText);
                relativeLayout = (RelativeLayout) view.findViewById(R.id.relativelayout);
                Typeface typeface =  Typeface.createFromAsset((mcontext).getAssets(),
                        "font/PANTON-BOLD.OTF");
                Typeface typeface1 =  Typeface.createFromAsset((mcontext).getAssets(),
                        "font/ROBOTO-MEDIUM_0.TTF");
                manager = new SessionManager(mcontext);
                text1.setTypeface(typeface1);
                text2.setTypeface(typeface1);
                text3.setTypeface(typeface1);
                text4.setTypeface(typeface1);
                text5.setTypeface(typeface1);
                auditIdText.setTypeface(typeface);
                firmNameText.setTypeface(typeface);
                auditTypeText.setTypeface(typeface);
                auditStatusText.setTypeface(typeface);
                auditCategoryText.setTypeface(typeface);

            }
        }

        public SingleAuditAdapter(Context context,List<Items> horizontalList) {
            this.auditDetailsList = horizontalList;
            this.mcontext = context;
        }

        @Override
        public SingleAuditAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_all_audit_list, parent, false);



            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.icon.setImageResource(R.drawable.file_icon);
            holder.auditIdText.setText(""+auditDetailsList.get(position).getSubAuditId());
            holder.firmNameText.setText(auditDetailsList.get(position).getFarmName());
            holder.auditTypeText.setText(year);
            holder.auditStatusText.setText(status);
            holder.auditCategoryText.setText(category);
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    manager.setAuditId(""+auditId);
                    Intent intent = new Intent(mcontext, BasicAuditDetailsActivity.class);
                    intent.putExtra("SUB_AUDIT_ID",""+auditDetailsList.get(position).getSubAuditId());

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mcontext.startActivity(intent);

                }
            });



        }

        @Override
        public int getItemCount() {
            return auditDetailsList.size();
        }
    }

}
