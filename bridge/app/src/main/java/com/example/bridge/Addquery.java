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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Addquery extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    EditText e1,e2;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    String query;
    String[] querys,query_id,date,value;
    public static String qid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquery);
        e1=(EditText)findViewById(R.id.query);
        l1=(ListView)findViewById(R.id.list);

                l1.setOnItemClickListener(this);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        b1=(Button) findViewById(R.id.actbutton);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Addquery.this;
        String q = "/Viewquery?login_id="+ sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query=e1.getText().toString();


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) Addquery.this;
                String q = "/Addquery?login_id="+ sh.getString("log_id", "") +"&query=" +query ;
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Addquery")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Addquery.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("Viewquery"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");

                    querys=new String[ja1.length()];
                    date=new String[ja1.length()];
                    query_id=new String[ja1.length()];
                    value=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {
                        querys[i]=ja1.getJSONObject(i).getString("query");

                        date[i]=ja1.getJSONObject(i).getString("date");
                        query_id[i]=ja1.getJSONObject(i).getString("query_id");



                        value[i]="querys: "+querys[i]+"\ndate: "+date[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),R.layout.custtext,value);
                    l1.setAdapter(ar);
                }
            }

        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        qid=query_id[i];

        final CharSequence[] items = {"View Solution","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Addquery.this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("View Solution")) {

                    startActivity(new Intent(getApplicationContext(),Viewsolutions.class));



                }





                else if (items[item].equals("Cancel")) {


                    dialog.dismiss();
                }
            }

        });
        builder.show();
    }
}