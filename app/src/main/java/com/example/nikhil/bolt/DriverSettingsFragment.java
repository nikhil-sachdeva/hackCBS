package com.example.nikhil.bolt;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Objects;

public class DriverSettingsFragment extends Fragment{

    ToggleButton isDndOn;
    TextView label;
    FloatingActionButton fab;
    BroadcastReceiver incomingCallReceiver;
    public static ArrayList<String> incomingCallList;

    private static final int PERMISSION_REQUEST_READ_PHONE_STATE = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.driver_fragment,container,false);
        getRequiredPermissions();
        fab = view.findViewById(R.id.floatingActionButton);
        incomingCallReceiver = new IncomingCallReceiver();
        isDndOn = view.findViewById(R.id.toggle);
        label = view.findViewById(R.id.label);
        incomingCallList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddContacts();
            }
        });

        isDndOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(200);
            }
        });
        isDndOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    getRequiredPermissions();
                    IntentFilter filter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
                    if(getActivity()!=null) {
                        getActivity().registerReceiver(incomingCallReceiver, filter);
                        Log.i("Messages", "getActivity() is not null");
                    } else {
                        Log.i("Messages", "getActivity() is null");
                    }
                    label.setText("Driving mode enabled.");
                } else if (!isChecked) {
                    Objects.requireNonNull(getActivity()).unregisterReceiver(incomingCallReceiver);
                    sendAvailableSMS();
                    label.setText("Driving mode disabled.");
                }
            }
        });

        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_READ_PHONE_STATE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    isDndOn.setChecked(false);
                    Toast.makeText(getContext(), "DND services cannot function without the required permissions.", Toast.LENGTH_SHORT).show();
                }

                return;
            }
        }
    }


    public void onClickAddContacts() {
        Intent intent = new Intent(getContext(), ContactsActivity.class);
        startActivity(intent);

    }

    private void getRequiredPermissions() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED ||
                    getContext().checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED ||
                    getContext().checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_READ_PHONE_STATE);
            }
        }
    }

    private void sendAvailableSMS() {
        if(incomingCallList.size()>0) {
            for(String number: incomingCallList) {
                sendReplySMS(number, getContext());
            }
        }
        incomingCallList.clear();
    }

    private void sendReplySMS(String number, Context context){
        DriverSettingsFragment.incomingCallList.add(number);
        String message = "Hi, I'm available to take calls now.";
        try {
            SmsManager.getDefault().sendTextMessage(number, null,
                    message, null, null);
        } catch (Exception e) {
            AlertDialog.Builder alertDialogBuilder = new
                    AlertDialog.Builder(context);
            AlertDialog dialog = alertDialogBuilder.create();
            dialog.setMessage(e.getMessage());
            dialog.show();
        }
    }
}
