package com.example.urlscrap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private ArrayList<UrlName> urllist;
    public RecycleAdapter(ArrayList<UrlName> urllist){
        this.urllist=urllist;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView urlText;

        public MyViewHolder(final View view){
            super(view);
            urlText=view.findViewById(R.id.txtItem);
        }
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {
        String urls = urllist.get(position).getUrlName();
        holder.urlText.setText(urls);

    }

    @Override
    public int getItemCount() {
        return urllist.size();
    }
}
