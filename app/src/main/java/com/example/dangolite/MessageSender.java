package com.example.dangolite;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet6Address;
import java.net.Socket;



public class MessageSender extends AsyncTask <Void, Void ,Void> {
    Socket s;
    DataOutputStream dos;
    PrintWriter pw;


    @Override
    protected Void doInBackground(Void... voids) {
        try{
            String message = "Success FingerPrint";
            Log.v("tag","do in background");



            s = new Socket("192.168.100.29",8000);


            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            s.close();
        }catch (IOException e){
            Log.e("my app", e.toString());
        }



        return null;
    }
}

