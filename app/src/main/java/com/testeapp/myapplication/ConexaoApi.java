package com.testeapp.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;

/**
 * Created by leandro on 02/11/15.
 */
public class ConexaoApi extends AsyncTask<Object, String, String> {


    public ConexaoApi() {
        super();
    }


    protected String doInBackground(Object... params) throws RuntimeException {
        StringBuffer chaine = new StringBuffer("");
        String urlString = (String)params[0];
        JSONObject json = (JSONObject)params[1];
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.connect();

            BufferedWriter out =
                    new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            out.write(json.toString());
            out.close();

            //InputStream inputStream = connection.getInputStream();


            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while ((line = rd.readLine()) != null) {
               // System.out.println(line);
                chaine.append(line);
            }

        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }


        return chaine.toString();
    }

}
