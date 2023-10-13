package com.example.a40;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class UbikeActivity extends AppCompatActivity {

   /* Button back2, ubikesearch;
    TextView exit1, exit4, eyuan;
    String TAG = MainActivity.class.getSimpleName()+"My";
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubike);
        ubikesearch = findViewById(R.id.ubikesearch);
        exit1 = findViewById(R.id.exit1);
        exit4 = findViewById(R.id.exit4);
        eyuan = findViewById(R.id.eyuan);
        back2 = findViewById(R.id.back2btn);
        catchData();


        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openButtonActivity();
            }
        });
    }
    public void openButtonActivity(){
        Intent intent = new Intent(this, ButtonActivity.class);
        startActivity(intent);
    }

    private void catchData(){
        String catchData = "https://data.ntpc.gov.tw/api/datasets/71cd1490-a2df-4198-bef1-318479775e8a/json?size=2000";
        try {
            URL url = new URL(catchData);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String line = in.readLine();
            StringBuffer json = new StringBuffer();
            while (line != null) {
                json.append(line);
                line = in.readLine();
            }
            JSONArray jsonArray= new JSONArray(String.valueOf(json));
            for (int i =0;i<10;i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String sna = jsonObject.getString("sna");
                if(!sna.equals("輔大捷運站(4號出口)")){
                    String sno = jsonObject.getString("sno");
                    String tot = jsonObject.getString("tot");
                    String sbi = jsonObject.getString("sbi");
                    String sarea = jsonObject.getString("sarea");
                    String mday = jsonObject.getString("mday");
                    String lat = jsonObject.getString("lat");
                    String lng = jsonObject.getString("lng");
                    String ar = jsonObject.getString("ar");
                    String sareaen = jsonObject.getString("sareaen");
                    String snaen = jsonObject.getString("snaen");
                    String aren = jsonObject.getString("aren");
                    String bemp = jsonObject.getString("bemp");
                    String act = jsonObject.getString("act");

                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("sbi",sbi);
                    hashMap.put("bemp",bemp);
                    hashMap.put("mday",mday);
                    arrayList.add(hashMap);
                }
            }
            Log.d(TAG,""+arrayList);

            runOnUiThread(()->{

            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }).start();*/
}