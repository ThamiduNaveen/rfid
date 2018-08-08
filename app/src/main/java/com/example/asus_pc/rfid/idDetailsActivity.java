package com.example.asus_pc.rfid;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class idDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_details);
        TextView idTW = (TextView) findViewById(R.id.textView_id);
        String idStr = getIntent().getExtras().get("id_detect").toString();
        idTW.setText(idStr);
        Button changeNameBT = (Button) findViewById(R.id.button_idDetail_changeName);
        changeNameBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialogChangeName = new Dialog(view.getContext());
                dialogChangeName.setContentView(R.layout.dialog_change_name);
                dialogChangeName.setTitle("Change the Name");

                Button dialogButton = (Button) dialogChangeName.findViewById(R.id.dialogButtonOK);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogChangeName.dismiss();
                    }
                });

                dialogChangeName.show();
            }
        });

    }
}
