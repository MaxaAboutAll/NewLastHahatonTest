package com.hahatontest.scryptan.newlasthahatontest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;

public class MainActivity extends AppCompatActivity {
    ChirpConnect chirpConnect;
    ConnectEventListener connectEventListener;
    public final int RESULT_REQUEST_RECORD_AUDIO = 6001;
    private final String TAG = "ChirpHackatonTry";
    private String KEY = "D5144D4b91b17fBf0266Dbd9d";
    private String SECRET = "69Cc407c95ccdCb4Ae2cc50AAa226B5f6F510fDcc23bEfCe50";
    private String CONFIG = "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";
    private String accountText = "Ваш счёт: ", statusText = "Статус: ", deviceID;
    private String[] doYouProove;
    private String sendingString = "";
    private int myMoney = 1000000;
    private TextView acoountTV, statusTV, amountRESTV;
    private Button receiveBTN, prooveBTN, unprooveBTN;
    private EditText moneyET;
    private boolean isProove = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acoountTV = findViewById(R.id.countTV);
        receiveBTN = findViewById(R.id.receiveBTN);
        prooveBTN = findViewById(R.id.prooveBTN);
        unprooveBTN = findViewById(R.id.unprooveBTN);
        moneyET = findViewById(R.id.moneyET);
        statusTV = findViewById(R.id.statusTV);
        amountRESTV = findViewById(R.id.amountRES);
        chirpConnect = new ChirpConnect(this, KEY, SECRET);
        chirpConnect.setConfig(CONFIG, new ConnectSetConfigListener() {

            @Override
            public void onSuccess() {
                Log.i(TAG, "Config successfully set.");
            }

            @Override
            public void onError(ChirpError setConfigError) {
                Log.e(TAG, setConfigError.getMessage());
            }
        });
        chirpConnect.start();
        setupListener();
        prooveBTN.setVisibility(View.GONE);
        unprooveBTN.setVisibility(View.GONE);
        final String ANDROID_ID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        deviceID = ANDROID_ID;
        acoountTV.setText(accountText+myMoney+"");
        statusTV.setText(statusText+"Ожидание");
    }
    public void updateLastPayload(final String text, final TextView myTV){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myTV.setText(text);
            }
        });
    }

    public void onClick(View v) throws UnsupportedEncodingException {
        switch (v.getId()){
            case R.id.receiveBTN:
                sendingString = "receive "+ deviceID+" "+moneyET.getText().toString();
                chirpConnect.send(sendingString.getBytes("UTF-8"));
                break;
            case R.id.prooveBTN:
                if(!isProove) {
                    sendingString = "proove "+ doYouProove[1]+" "+doYouProove[2];
                    if(Integer.parseInt(doYouProove[2])<=myMoney){
                        acoountTV.setText(accountText+ (myMoney - Integer.parseInt(doYouProove[2])));
                        chirpConnect.send(sendingString.getBytes("UTF-8"));
                    }else{
                        Toast.makeText(getApplicationContext(),"Недостаточно средств",Toast.LENGTH_SHORT).show();
                        prooveBTN.setVisibility(View.GONE);
                        unprooveBTN.setVisibility(View.GONE);
                        receiveBTN.setVisibility(View.VISIBLE);
                        updateLastPayload("", amountRESTV);
                        break;
                    }
                    chirpConnect.send(sendingString.getBytes("UTF-8"));
                    isProove = true;
                    prooveBTN.setVisibility(View.GONE);
                    receiveBTN.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.unprooveBTN:
                prooveBTN.setVisibility(View.GONE);
                unprooveBTN.setVisibility(View.GONE);
                receiveBTN.setVisibility(View.VISIBLE);
                updateLastPayload("", amountRESTV);
                break;
        }
    }

    public void setupListener(){
        connectEventListener = new ConnectEventListener() {

            @Override
            public void onSending(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload is being sent " + payload.toString() + " on channel: " + channel);
                updateLastPayload(statusText+"Отправка", statusTV);
            }

            @Override
            public void onSent(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload has been sent " + payload.toString()  + " on channel: " + channel);
                updateLastPayload(statusText+"Отправлен", statusTV);
            }

            @Override
            public void onReceiving(byte channel) {
                Log.v(TAG, "This is called when the SDK is expecting a payload to be received on channel: " + channel);
                updateLastPayload(statusText+"Получение", statusTV);
            }

            @Override
            public void onReceived(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload has been received " + payload  + " on channel: " + channel);
                isProove = false;
                String received = "";
                try {
                    received = new String(payload, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),received,Toast.LENGTH_SHORT).show();
                doYouProove = received.split(" ");
                if(doYouProove[0].equals("proove")){
                    if(doYouProove[1].equals(deviceID))
                        updateLastPayload(accountText+(myMoney + Integer.parseInt(doYouProove[2])), acoountTV);
                }else {
                    updateLastPayload("У вас просят: "+doYouProove[2], amountRESTV);
                    updateButtons();
                }
                updateLastPayload(statusText+"Ожидание", statusTV);
            }

            @Override
            public void onStateChanged(byte oldState, byte newState) {
                Log.v(TAG, "This is called when the SDK state has changed " + oldState + " -> " + newState);
            }

            @Override
            public void onSystemVolumeChanged(int old, int current) {
                Log.d(TAG, "This is called when the Android system volume has changed " + old + " -> " + current);
            }

        };
        chirpConnect.setListener(connectEventListener);
    }

    public void updateButtons(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                prooveBTN.setVisibility(View.VISIBLE);
                unprooveBTN.setVisibility(View.VISIBLE);
                receiveBTN.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        }
        else {
            chirpConnect.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chirpConnect.start();
                }
                return;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chirpConnect.stop();
        try {
            chirpConnect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        chirpConnect.stop();
        try {
            chirpConnect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}