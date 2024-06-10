package com.example.bridge;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Viewsolutions extends AppCompatActivity implements JsonResponse {
    ListView l1;
    SharedPreferences sh;
    String[] solution,name,query,date,value;
    public static String sid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsolutions);
        l1=(ListView) findViewById(R.id.list);
//        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewsolutions.this;
        String q = "/Viewsolutions?log_id=" +sh.getString("log_id", "")+"&qid="+Addquery.qid;
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
                solution = new String[ja1.length()];
                name = new String[ja1.length()];
                query = new String[ja1.length()];
                date = new String[ja1.length()];


                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    solution[i] = ja1.getJSONObject(i).getString("solution");

                    name[i] = ja1.getJSONObject(i).getString("name");
                    query[i] = ja1.getJSONObject(i).getString("query");

                    date[i] = ja1.getJSONObject(i).getString("date");


                    value[i] = "solution:" + solution[i] + "\nname: " + name[i] + "\nquery: " + query[i] + "\ndate: " + date[i]  ;

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();

        }
    }


}