package com.example.q.myweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityBasic extends AppCompatActivity  implements AdapterView.OnItemClickListener{
    ListView List;
    String[] city1 = new String[]{
            ("7533560"),("7532249"),("7532536"),("7531136"),("753268"),("7531679"),("7533109"),("7531410"),("7530983"),("7533524"),("7531926"),("7533434"),("7532559"),("7533365"),("7530730"),("7531689"),("7532895"),("2643743"),("524901"),("2172797"),
    };
    String[] city = new String[]

    {
        ("Aleksandrów Lódzki"),
                ("Brzeg"),
                ("Bytom"),
                ("Bukowina Tatrzańska"),
                ("Chełmno"),
                ("Kalisz"),
                ("Kąty Wrocławskie"),
                ("Koło"),
                ("Koszalin"),
                ("Lask"),
                ("Warszawa"),
                ("Wieluń"),
                ("Wisła"),
                ("Wołomin"),
                ("Zamość"),
                ("Piątnica"),
                ("Żywiec"),
                ("LONDYN"),
                ("Moskwa"),
                ("Caris")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        List=(ListView) findViewById(R.id.List);
        ArrayAdapter<String> arrayadapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,city);
        List.setAdapter(arrayadapter);
        List.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), WeatherWindowActivity.class);
        intent.putExtra("name", city1[position] );
        startActivity(intent);
    }
}