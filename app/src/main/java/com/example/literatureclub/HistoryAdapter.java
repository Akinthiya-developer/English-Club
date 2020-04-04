package com.example.literatureclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<String> eventlist;
    Context context;

    public HistoryAdapter(List<String> eventlist, Context context) {
        this.eventlist = eventlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eheader.setText(eventlist.get(position));
    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView eheader;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eheader=itemView.findViewById(R.id.eheader);
        }
    }
}
