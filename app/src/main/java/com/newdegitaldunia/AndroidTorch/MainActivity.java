package com.newdegitaldunia.AndroidTorch;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    Global global;
    ToggleButton tb;
    boolean isTorchOn = false;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Adds_file.showInterStitialAd(MainActivity.this);


        mAdView = new AdView(MainActivity.this);
        mAdView.setAdUnitId("MY_AD_UNIT_ID");
        mAdView.setAdSize(AdSize.BANNER);



        mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);


        tb=(ToggleButton) findViewById(R.id.main_toggle_btn);


        global = new Global();

        startService(new Intent(this, ShakeDetectService.class));

        //from here toggle button working is start

        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tb.isChecked()){
                    torchToggle("on");
                }
                else {
                    torchToggle("off");
                }

            }
        });
    }

    //end of toggle button



    //starting on normal button for on/off

    //
//    public void toggle(View view) {
//        Button button = (Button) view;
//        if (button.getText().equals("Switch On")) {
//
//            button.setText(R.string.switch_off_text);
//            torchToggle("on");
//        } else {
//            button.setText(R.string.switch_on_text);
//            torchToggle("off");
//        }
//    }
// end of normal button


    private void torchToggle(String command) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null; // Usually back camera is at 0 position.
            try {
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0];
                }
                if (camManager != null) {
                    if (command.equals("on")) {
                        camManager.setTorchMode(cameraId, true);   // Turn ON
                        isTorchOn = true;
                    } else {
                        camManager.setTorchMode(cameraId, false);  // Turn OFF
                        isTorchOn = false;
                    }
                }
            } catch (CameraAccessException e) {
                e.getMessage();
            }
        }
    }

    public void goToSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
