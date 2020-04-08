package com.example.literatureclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class eventieAdapter extends RecyclerView.Adapter<eventieAdapter.ViewHolder> {

    Context context;
    List<String> regis,events;

    public eventieAdapter(Context context, List<String> regis, List<String> events) {
        this.context = context;
        this.regis = regis;
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.eventiecard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name=events.get(position);
        holder.evento.setText(name.substring(8,name.length()));
//        holder.regiso.setText(regis.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView evento,regiso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            evento=itemView.findViewById(R.id.evento);
            regiso=itemView.findViewById(R.id.regiso);
        }
    }
}
