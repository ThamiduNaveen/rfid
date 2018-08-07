package com.example.asus_pc.rfid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {
    private static final String TAG = "RecylerAdapter";
    private ArrayList<String> countAr = new ArrayList<>();
    private ArrayList<String> epcIDAr = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter( Context mContext,ArrayList<String> countAr, ArrayList<String> epcIDAr) {
        this.countAr = countAr;
        this.epcIDAr = epcIDAr;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitems,parent,false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder : called.");
        holder.countTW.setText(countAr.get(position));
        holder.epcIDTW.setText(epcIDAr.get(position));
        holder.listlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"on Clicke clickedOn :" +epcIDAr.get(position));
                Toast.makeText(mContext, epcIDAr.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return epcIDAr.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView countTW,epcIDTW;
        RelativeLayout listlayout;

        public viewHolder(View itemView) {
            super(itemView);
            countTW =(TextView) itemView.findViewById(R.id.textView_Count);
            epcIDTW =(TextView) itemView.findViewById(R.id.textView_epc);
            listlayout =(RelativeLayout) itemView.findViewById(R.id.parent_layout);

        }

    }
}
