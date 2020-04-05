package com.example.literatureclub;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class ListieList extends RecyclerView.Adapter<ListieList.ViewHolder> {

    Context context;
    static List<String> namePair;
    static List<GetUser> dataPair;
    static int POS;

    public ListieList(Context context, List<String> namePair, List<GetUser> datapair) {
        this.context = context;
        this.namePair = namePair;
        this.dataPair=datapair;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pupcard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.namer.setText(namePair.get(position));
        holder.namer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POS=position;
                Intent intent=new Intent(context, PersonaActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return namePair.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button namer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namer=itemView.findViewById(R.id.namer);
        }
    }
}
