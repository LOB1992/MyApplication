package com.testeapp.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leandro on 02/11/15.
 */
public class GerarJSON {

    public Object GeraArquivo(double longitude, double latitude){
        JSONObject near_groups= new JSONObject();
        JSONObject parent = new JSONObject();

        try {
            near_groups.put("fb_user_id", "1234");
            near_groups.put("longitude", longitude);
            near_groups.put("latitude", latitude);
            near_groups.put("fb_access_token", "1234556");

            parent.put("near_groups", near_groups);
        }catch(JSONException e){ e.printStackTrace(); }


        return parent;
    }
}
