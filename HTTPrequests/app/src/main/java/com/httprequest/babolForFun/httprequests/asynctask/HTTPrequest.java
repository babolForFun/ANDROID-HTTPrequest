package com.httprequest.babolForFun.httprequests.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;


/**
 * Execute HTTP request as asynctask and log the response.
 */
public class HTTPrequest extends AsyncTask<String, String, String> {

    private String TAG = "Servar";
    private Context ctx;
    private ProgressDialog pdia;
    private String text;

    /**
     * Constructor
     * @param context   to create process dialog
     */
    public HTTPrequest(Context context) {
        this.ctx = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        // Create process dialog
        pdia = new ProgressDialog(ctx);
        pdia.setMessage("Loading...");
        pdia.show();
    }

    @Override
    protected String doInBackground(String... uri) {

        // Http variables
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = null;
        HttpPost httpPost     = new HttpPost(uri[0]);

        // Add data in Post
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("Username", uri[1]));      // Look at the name of post data!
        nameValuePair.add(new BasicNameValuePair("Password", uri[2]));      // Look at the name of post data!

        // Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Execute POST request.
        try {
            response = httpClient.execute(httpPost);

            // parse response as a String
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder builder = new StringBuilder();
            String str = "";
            while ((str = rd.readLine()) != null) {
                builder.append(str);
            }

            // RESPONSE!
            text = builder.toString();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG,result);              // Log response
        pdia.dismiss();                 // Dismiss progress Dialog
    }

}



