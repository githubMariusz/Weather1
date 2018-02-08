package com.example.q.myweather;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherWindowActivity extends AppCompatActivity {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?id=";
    private static String BASE_URL_1 = "&APPID=252f36f2f8d4faffc7ed6d9c6cfeb98d&units=metric";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatger_window);
        findViewById(R.id.start_button)
                .setOnClickListener(new View.OnClickListener() {
                    Intent intent = getIntent();
                        String name1 = intent.getStringExtra("name");
                    @Override
public void onClick(View v) {
        new WebServiceHandler()
        .execute(BASE_URL + name1 + BASE_URL_1);}
        });
    }
private class WebServiceHandler extends AsyncTask<String, Void, String> {
    private ProgressDialog dialog = new ProgressDialog(WeatherWindowActivity.this);
    @Override
    protected void onPreExecute() {
        dialog.setMessage (getString(R.string.please_wait));
        dialog.show();
    }
    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            InputStream in = new BufferedInputStream(
                    connection.getInputStream());
            return streamToString(in);
        } catch (Exception e) {
            Log.d(WeatherWindowActivity.class.getSimpleName(), e.toString());
            return null;
        }
    }
    @Override
    protected void onPostExecute(String  result) {
        dialog.dismiss();
        try {
            JSONObject json = new JSONObject(result);
            ((TextView) findViewById(R.id.response_id)).setText(String.format((getString(R.string.json_id))+" %s", json.optString("id")));
            ((TextView) findViewById(R.id.response_name)).setText(String.format((getString(R.string.json_name))+" %s", json.optString("name")));

            JSONObject reader = new JSONObject(result);
            JSONObject main  = reader.getJSONObject(getString(R.string.json_main));
            ((TextView) findViewById(R.id.response_temp)).setText(String.format((getString(R.string.json_temperature))+" %s", main.optString("temp")+(getString(R.string.json_C))));
            ((TextView) findViewById(R.id.response_press)).setText(String.format((getString(R.string.json_pressure))+" %s", main.optString("pressure")+(getString(R.string.json_hpa))));
            ((TextView) findViewById(R.id.response_hum)).setText(String.format((getString(R.string.json_humidity))+" %s", main.optString("humidity")+(getString(R.string.json_proc))));

            JSONObject readerWind =new JSONObject(result);
            JSONObject wind =readerWind.getJSONObject(getString(R.string.json_wind));
            ((TextView) findViewById(R.id.respone_speed)).setText(String.format((getString(R.string.json_speer_wind))+" %s",wind.optString("speed")+(getString(R.string.json_km_h))));
        } catch (Exception e) {
            Log.d(WeatherWindowActivity.class.getSimpleName(), e.toString());
        }
    }
}
    @NonNull
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {  Log.d(WeatherWindowActivity.class.getSimpleName(), e.toString()); }
        return stringBuilder.toString();
    }
    public void closeActivity(View view) {
        finish();}
}