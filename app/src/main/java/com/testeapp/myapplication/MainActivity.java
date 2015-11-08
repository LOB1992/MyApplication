package com.testeapp.myapplication;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private TextView tvResposta;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResposta = (TextView) findViewById(R.id.textView);

        callConnection();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    private synchronized void callConnection(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApiIfAvailable(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    //Listener
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", "onConnected(" + bundle + ")");
        String RespostaServ;
        GerarJSON Arquivo = new GerarJSON();
        ConexaoApi Conn = new ConexaoApi();
        String url = "http://humanitary.cloudapp.net/humanitary_api/near_groups";
        DecodeJSON decJSON = new DecodeJSON();
        JSONObject json = null;
        String finaljson = null;
        String[] Resposta = null;

        Location Localizacao = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        try {
            json = (JSONObject) Arquivo.GeraArquivo(0,0);
            finaljson = Conn.execute(url, json).toString();
            Resposta = decJSON.DecodeJSON(finaljson);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("Log","onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("Log","onConnectionFailed(" + connectionResult + ")");
    }
}
