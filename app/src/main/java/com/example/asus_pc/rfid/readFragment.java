package com.example.asus_pc.rfid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import uhf.api.CommandType;
import uhf.api.Query_epc;
import uhf.api.ShareData;

public class readFragment extends Fragment {
    private ArrayList<String> countAr = new ArrayList<>();
    private ArrayList<String> epcIDAr = new ArrayList<>();

    private Button readBT;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_read,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readBT = (Button) getActivity().findViewById(R.id.button_read);
        readBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = OutOneData();
                if(data!=null) {
                    //Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                    countAr.add("1");
                    epcIDAr.add(data);
                    initRecyclerView();
                }


            }
        });
    }

    private String OutOneData() {
        Query_epc mQuery_epc=new Query_epc();
        UHFClient info=UHFClient.getInstance();
        if(info!=null) {

            Boolean ret=UHFClient.mUHF.command(CommandType.SINGLE_QUERY_TAGS_EPC, mQuery_epc);
            if(ret) {

                String str_tmp= ShareData.CharToString(mQuery_epc.epc.epc, mQuery_epc.epc.epc.length);
                str_tmp=str_tmp.replace(" ", "");
                return str_tmp;

            }
        }
        return null;
    }
    private void initRecyclerView() {
        RecyclerView rw = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(),countAr,epcIDAr);
        rw.setAdapter(adapter);
        rw.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
