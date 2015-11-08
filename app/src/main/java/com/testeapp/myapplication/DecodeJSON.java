package com.testeapp.myapplication;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by leandro on 06/11/15.
 */
public class DecodeJSON {

    public String[] DecodeJSON(String json) throws JSONException {

        String[] endPoints = null;

        try {
            JSONObject jsonAwnser = new JSONObject(json);

            JSONArray grupos = jsonAwnser.getJSONArray("nearby");
            endPoints = new String[grupos.length()];
            for (int i = 0; i < grupos.length();i++){
                JSONObject j = grupos.getJSONObject(i);

                endPoints[i] = j.getString("group_name");
                endPoints[i] += ";"+j.getString("group_description");
                endPoints[i] += ";"+j.getString("responsable_name");
                endPoints[i] += ";"+j.getString("group_phone");
                endPoints[i] += ";"+j.getString("longitude");
                endPoints[i] += ";"+j.getString("latitude");
                endPoints[i] += ";"+j.getString("address");
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return endPoints;
    }
}
