package com.httprequest.babolForFun.httprequests;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.httprequest.babolForFun.httprequests.asynctask.HTTPrequest;


/**
 *  Created by BabolForFun
 *
 *
 *
 * In order to deploy correctly this project you have to edit this file as follow
 *
 * AndroidManifest.xml
 *      Insert user permission
 *          <uses-permission android:name="android.permission.INTERNET" />
 *          <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 *
 * build.gradle
 *      Insert into android{} after 'buildToolsVersion'
 *          useLibrary 'org.apache.http.legacy'
 *
 *
 *
 * You must have a valid url like http://www.example.com/login. Pay attention to use the
 * correct url form like 'http://www.yoursite.com/yourscript"
 *
 */
public class Home extends AppCompatActivity {

    private Context ctx;
    private String url,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ctx      = getApplicationContext();
        url      = "http://www.yoursite.com/yourscript";
        username = "myUsr";
        password = "myPsw";

        // If I need a return param, you can use async().execute().get
        new HTTPrequest(this).execute(url,username,password);

    }
}




