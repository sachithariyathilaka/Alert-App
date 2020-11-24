package com.zacseed.alertapp.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zacseed.alertapp.LogModel;
import com.zacseed.alertapp.R;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {
    ArrayList<LogModel> arrayList = new ArrayList<>();
    public RecycleAdapter(ArrayList<LogModel> arrayList){
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_log,parent,false);
        RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
        return recycleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        LogModel logModel = arrayList.get(position);
        holder.messageLog.setText("Log type: "+logModel.getType()+", Phone number: "+logModel.getPhoneNumber()+", Location Id : "+logModel.getLocationId() + ", Alarm Id: "+logModel.getAlarmId()+", Timestamp: "+logModel.getTimestamp()+", Received Time: "+logModel.getReceived_time());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{

        TextView messageLog;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            messageLog = itemView.findViewById(R.id.locationId);
        }
    }

}
