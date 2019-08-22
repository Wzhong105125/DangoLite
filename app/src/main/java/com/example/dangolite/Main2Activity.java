package com.example.dangolite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!= null)
        {
            if (result.getContents()==null)
            {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();
                String ScanAnswer = result.getContents().toString();
                int ipstart = ScanAnswer.indexOf(":")+1 ;
                int ipend = ScanAnswer.indexOf("o")-2;
                int portstart = ScanAnswer.indexOf("t")+2;
                int portend = ScanAnswer.length()-1;
                String ip = ScanAnswer.substring(ipstart,ipend);
                String port = ScanAnswer.substring(portstart,portend);

                Intent returnvalue  = getIntent();
                returnvalue.putExtra("ip",ip);
                returnvalue.putExtra("port",port);
                setResult(Activity.RESULT_OK,returnvalue);
                finish();
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        IntentIntegrator integrator = new IntentIntegrator(Main2Activity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("掃描QRcode");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();

    }
}
