package com.example.bridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class viewloandetails extends AppCompatActivity implements JsonResponse {
    ListView l1;
    SharedPreferences sh;
    String[] fname,loandetails,noofdays,amount,monthlyamount,papers,value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewloandetails);
        l1=(ListView) findViewById(R.id.list);
//        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) viewloandetails.this;
        String q = "/viewloandetails?log_id=" +sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                fname = new String[ja1.length()];
                loandetails = new String[ja1.length()];

                noofdays = new String[ja1.length()];
                amount = new String[ja1.length()];
                monthlyamount = new String[ja1.length()];

                papers = new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    fname[i] = ja1.getJSONObject(i).getString("fname");

                    loandetails[i] = ja1.getJSONObject(i).getString("loandetails");
                    noofdays[i] = ja1.getJSONObject(i).getString("noofdays");

                    amount[i] = ja1.getJSONObject(i).getString("amount");
                    monthlyamount[i] = ja1.getJSONObject(i).getString("monthlyamount");

                    papers[i] = ja1.getJSONObject(i).getString("papers");





                    value[i] = "name:" + fname[i] + "\nloandetails: " + loandetails[i]+"\nnoofdays:" + noofdays[i] + "\namount: " + amount[i]+"\nmonthlyamount:" + monthlyamount[i]   ;

                }
                Custimage a = new Custimage(this, fname, loandetails, noofdays,amount, monthlyamount,papers);
                l1.setAdapter(a);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }
}