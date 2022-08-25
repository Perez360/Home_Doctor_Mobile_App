package com.example.home_doctor.Localhost_Connection;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class ConnectionClass extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog.Builder alertDialog;
    ProgressDialog progressDialog;
    ConnectionClass(Context getContext){
        this.context=getContext;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_Url="http://192.168.56.1/Home_Doctor/login.php";
        if(type.equals("login")) {
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_Url);
                HttpURLConnection httpurlConnection = (HttpURLConnection) url.openConnection();
                httpurlConnection.setRequestMethod("POST");
                httpurlConnection.setDoInput(true);
                httpurlConnection.setDoOutput(true);
                OutputStream outputStream=httpurlConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                System.out.println(post_data);
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream=httpurlConnection.getInputStream();
                BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpurlConnection.disconnect();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },5000);
                return result;
            }catch(MalformedURLException u){
                u.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Connecting to Server");
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },5000);
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.cancel();
        alertDialog.setMessage(result);
        alertDialog.show();



    }
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);

    }
}
