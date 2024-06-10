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

public class viewproposals extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    SharedPreferences sh;
    String[] amount,date,statu,name,fname,value,solution_id,investor_id;
    public static String sid,iid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproposals);
        l1=(ListView) findViewById(R.id.list);
        l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) viewproposals.this;
        String q = "/viewproposals?log_id=" +sh.getString("log_id", "");
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
                amount = new String[ja1.length()];
                date = new String[ja1.length()];
                statu = new String[ja1.length()];
                name = new String[ja1.length()];
                fname = new String[ja1.length()];
                solution_id = new String[ja1.length()];
                investor_id= new String[ja1.length()];

                value = new String[ja1.length()];



                String[] value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    amount[i] = ja1.getJSONObject(i).getString("amount");

                    date[i] = ja1.getJSONObject(i).getString("date");
                    statu[i] = ja1.getJSONObject(i).getString("status");
                    name[i] = ja1.getJSONObject(i).getString("name");
                    fname[i] = ja1.getJSONObject(i).getString("fname");
                    solution_id[i] = ja1.getJSONObject(i).getString("proposal_id");

                    investor_id[i] = ja1.getJSONObject(i).getString("investor_id");

                    value[i] = "fname:" + fname[i] + "\nname: " + name[i]  + "\namount: " + amount[i]  + "\ndate: " + date[i]  + "\nstatu: " + statu[i]   ;

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        sid=solution_id[i];
        iid=investor_id[i];
        final CharSequence[] items = {"Accept","reject","Message","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(viewproposals.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Accept")) {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) viewproposals.this;
                    String q = "/accept?sid="+sid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }
                else if (items[item].equals("Reject")) {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) viewproposals.this;
                    String q = "/reject?sid="+sid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }


                else if (items[item].equals("Message")) {
                    startActivity(new Intent(getApplicationContext(),Messages.class));
                }


                else if (items[item].equals("Cancel")) {


                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
}