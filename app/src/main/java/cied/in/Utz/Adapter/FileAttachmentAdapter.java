package cied.in.Utz.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cied.in.Utz.Database.UtzDatabase;
import cied.in.Utz.Model.Items;
import cied.in.Utz.R;
import cied.in.Utz.SharedPreferance.SessionManager;

/**
 * Created by cied on 2/2/17.
 */
public class FileAttachmentAdapter extends RecyclerView.Adapter<FileAttachmentAdapter.MyViewHolder> {

    private List<Items> attachmentFileDetailList;
    Context mcontext;
    SessionManager manager;
    UtzDatabase database;
    private Activity activity;
    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView fileNameText,closeButton;
        RelativeLayout relativeLayout;



        public MyViewHolder(View view) {
            super(view);
            fileNameText = (TextView) view.findViewById(R.id.textView24);
            closeButton = (TextView) view.findViewById(R.id.textView25);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);

            Typeface typeface =  Typeface.createFromAsset((activity).getAssets(),
                    "font/PANTON-BOLD.OTF");
            fileNameText.setTypeface(typeface);
            database = new UtzDatabase(activity);
        }
    }

    public FileAttachmentAdapter(Activity context,List<Items> horizontalList) {
        this.attachmentFileDetailList = horizontalList;
        this.activity = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_fileattachment_layout, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.fileNameText.setText(attachmentFileDetailList.get(position).getFileName());

        holder.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.updateFilesAttachmentTable(new Items("true"),""+attachmentFileDetailList.get(position).getFileId());
                attachmentFileDetailList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, attachmentFileDetailList.size());

            }
        });
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("path ",attachmentFileDetailList.get(position).getFilePath());
                Log.e("Status ",attachmentFileDetailList.get(position).getStatus());
                // viewImage(attachmentFileDetailList.get(position).getFilePath(),attachmentFileDetailList.get(position).getStatus());
            }
        });

    }

    @Override
    public int getItemCount() {
        return attachmentFileDetailList.size();
    }

    public void viewImage(final String filePath,String status) {
        final Dialog dialogbox = new Dialog(activity);
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        dialogbox.setContentView(R.layout.image_popup);
        dialogbox.setCancelable(false);
        final ImageView imageView = (ImageView) dialogbox.findViewById(R.id.imageView16);
        TextView  textView = (TextView) dialogbox.findViewById(R.id.textView29);
        WebView webView = (WebView) dialogbox.findViewById(R.id.webView);
        if(status.equals("true"))
        {
            webView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Bitmap myBitmap = BitmapFactory.decodeFile(filePath);
            imageView.setImageBitmap(myBitmap);
        }
        else
        {
            String url = "http://"+filePath.replaceAll("\\\\", "");
            webView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);

            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.loadUrl(url);
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogbox.dismiss();
            }
        });


        dialogbox.show();

    }
}