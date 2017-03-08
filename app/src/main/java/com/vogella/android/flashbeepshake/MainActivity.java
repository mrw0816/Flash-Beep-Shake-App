package com.vogella.android.flashbeepshake;

import android.content.Context;
import android.content.pm.PackageManager;


import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean camAvailable;
    boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camAvailable = getApplication().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


    public void onClickFlash(View view){

        if(camAvailable){

            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            try {
                String camId = cameraManager.getCameraIdList()[0];
                if(!isOn){
                    cameraManager.setTorchMode(camId, true);
                    isOn = true;
                } else{
                    cameraManager.setTorchMode(camId, false);
                    isOn = false;
                }
            }catch(CameraAccessException e){
                e.printStackTrace();
            }

        }

    }

    public void onClickBeep(View view){
        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
        tg.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

    }

    public void onClickShake(View view){
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}
