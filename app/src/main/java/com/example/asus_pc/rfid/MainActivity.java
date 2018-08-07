package com.example.asus_pc.rfid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import uhf.api.CommandType;
import uhf.api.Ware;

public class MainActivity extends AppCompatActivity {

    private String cmd_open = "echo 1 > /sys/devices/soc.0/xt_dev.68/xt_gpio_112";
    private String cmd_close = "echo 0 > /sys/devices/soc.0/xt_dev.68/xt_gpio_112";

    private String cmd_open1 = "echo on > /proc/uart3_3v3_en";
    private String cmd_close1 = "echo off > /proc/uart3_3v3_en";

    private TextView minfos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        minfos = (TextView) findViewById(R.id.infos);


        Ware mWare = new Ware(CommandType.GET_FIRMWARE_VERSION, 0, 0, 0);
        int count = 0;
        while (true) {
            UHFClient info = UHFClient.getInstance();
            if (info != null) {
                Boolean rett = UHFClient.mUHF.command(CommandType.GET_FIRMWARE_VERSION, mWare);
                if (rett) {
                    Log.e("TAG", "Ver." + mWare.major_version + "." + mWare.minor_version + "." + mWare.revision_version);
                    if (mWare.major_version == 1
                            && (mWare.minor_version == 0 || mWare.minor_version == 1
                            || mWare.minor_version == 2 || mWare.minor_version == 3)
                            && (mWare.revision_version == 0 || mWare.revision_version == 1
                            || mWare.revision_version == 2 || mWare.revision_version == 3 ||
                            mWare.revision_version == 4 || mWare.revision_version == 5 ||
                            mWare.revision_version == 6 || mWare.revision_version == 7 ||
                            mWare.revision_version == 8 || mWare.revision_version == 9)
                            ) {
                        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
                        ;
                        minfos.setText("Ver." + mWare.major_version + "." + mWare.minor_version + "." + mWare.revision_version);
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    sleep(3000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    Intent welcome_main = new Intent(MainActivity.this, mainMenu.class);
                                    welcome_main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(welcome_main);
                                }
                            }
                        };
                        thread.start();
                        break;
                    }
                }
            }

            count++;
            if (count > 5) {
                Toast.makeText(this, "unsuccessful", Toast.LENGTH_SHORT).show();
                finish();
                break;
            }
        }
    }
}
