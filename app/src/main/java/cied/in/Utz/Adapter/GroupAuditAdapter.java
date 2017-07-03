package cied.in.Utz.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;
import cied.in.Utz.SingleAuditListActivity;

/**
 * Created by cied on 31/1/17.
 */
public class GroupAuditAdapter extends RecyclerView.Adapter<GroupAuditAdapter.MyViewHolder> {

    private List<Items> auditDetailsList;
    Context mcontext;
    SessionManager manager;
    UtzDatabase database;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView statusImage,categoryImageView;
        TextView text1,text2,text3,text4,text5 ,auditIdText,firmNameText,auditTypeText,auditStatusText,auditCategoryText;
        RelativeLayout relativeLayout;
        ImageView icon;

        public MyViewHolder(View view) {
            super(view);

            text1 = (TextView) view.findViewById(R.id.text1);
            text2 = (TextView) view.findViewById(R.id.text2);
            text3 = (TextView) view.findViewById(R.id.text3);
            text4 = (TextView) view.findViewById(R.id.text4);
            text5 = (TextView) view.findViewById(R.id.text5);
            icon = (ImageView) view.findViewById(R.id.imageView3);
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
            database = new UtzDatabase(mcontext);
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

    public GroupAuditAdapter(Context context,List<Items> horizontalList) {
        this.auditDetailsList = horizontalList;
        this.mcontext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_all_audit_list, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.icon.setImageResource(R.drawable.folder_icon);

        holder.auditIdText.setText(""+auditDetailsList.get(position).getAuditId());
        holder.firmNameText.setText(auditDetailsList.get(position).getAuditName());
        holder.auditTypeText.setText(auditDetailsList.get(position).getYear());
        holder.auditStatusText.setText(auditDetailsList.get(position).getStatus());
        holder.auditCategoryText.setText(auditDetailsList.get(position).getCategory());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager.setAuditId(""+auditDetailsList.get(position).getAuditId());
                Intent intent = new Intent(mcontext, SingleAuditListActivity.class);
                intent.putExtra("AUDIT_ID",auditDetailsList.get(position).getAuditId());
                mcontext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return auditDetailsList.size();
    }
}