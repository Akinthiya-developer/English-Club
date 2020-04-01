package com.example.literatureclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class listadapter extends RecyclerView.Adapter<listadapter.ViewHolder> {

    Context context;
    List<UploadData> eventData;

    public listadapter(Context context, List<UploadData> eventData) {
        this.context = context;
        this.eventData = eventData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcycle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listadapter.ViewHolder holder, int position) {

        UploadData uploadData = eventData.get(position);

//        if(uploadData.getImgURL()==null)holder.emage.setVisibility(View.GONE);
////        if(uploadData.getInfo().toString()==null)holder.einfo.setVisibility(View.GONE);
//        if(uploadData.getName().equals("downdemo"))
//            Toast.makeText(context, uploadData.getInfo(), Toast.LENGTH_SHORT).show();

        holder.eheader.setText(uploadData.getName());
        Glide.with(context).load(uploadData.getImgURL()).into(holder.emage);
        holder.einfo.setText(uploadData.getInfo());
        if(uploadData.getDocURL()==null)holder.docie.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return eventData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eheader,einfo;
        ImageView emage;
        Button docopner;
        RelativeLayout docie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            docie=itemView.findViewById(R.id.docie);
            eheader=itemView.findViewById(R.id.eheader);
            einfo=itemView.findViewById(R.id.einfo);
            emage=itemView.findViewById(R.id.emage);
            docopner =itemView.findViewById(R.id.docopner);

        }
    }
}
