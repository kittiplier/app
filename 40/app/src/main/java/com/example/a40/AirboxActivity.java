package com.example.a40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AirboxActivity extends AppCompatActivity {
    Button back, getdata;
    TextView textView, textView1, textView2;
    String result, result1, result2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airbox);
        back = findViewById(R.id.backbtn);
        textView = findViewById(R.id.temp);
        textView1 = findViewById(R.id.humi);
        textView2 = findViewById(R.id.co2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openButtonActivity();
            }
        });

        getdata = findViewById(R.id.getdata);
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Thread thread = new Thread(runnable);
                thread.start();

            }
        });
    }
    public void openButtonActivity(){
        Intent intent = new Intent(this, ButtonActivity.class);
        startActivity(intent);
    }
    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            try
            {
                URL url = new URL("http://10.0.2.2/data.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                // 設定連線方式為 POST
                connection.setDoOutput(true); // 允許輸出
                connection.setDoInput(true); // 允許讀入
                connection.setUseCaches(false); // 不使用快取
                connection.connect(); // 開始連線

                int responseCode =
                        connection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"),8);
                   //String box = "";
                    String line = bufferedReader.readLine();
                    /*while ((line = bufferedReader.readLine()) != null) {
                        //box += line + "\n";
                    }*/
                    JSONArray dataJson = new JSONArray(line);
                    int i = dataJson.length()-1;
                    JSONObject info = dataJson.getJSONObject(i);
                    String temp = info.getString("temp");
                    String humd = info.getString("humd");
                    String co2 = info.getString("co2");
                    result = temp.toString();
                    result1 = humd.toString();
                    result2 = co2.toString();
                    inputStream.close();
                    //result = box;
                }


            }catch (Exception e){
                result = e.toString();
                result1 = e.toString();
                result2 = e.toString();

            }
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    textView.setText(result);
                    textView1.setText(result1);
                    textView2.setText(result2);



                }
            });
        }
    };


}