package com.example.bridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Postmyideas extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String name,place,phone,email;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postmyideas);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText) findViewById(R.id.name);
        e2=(EditText) findViewById(R.id.cateory);
        e3=(EditText) findViewById(R.id.place);
        e4=(EditText) findViewById(R.id.email);

        b1=(Button) findViewById(R.id.update);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=e1.getText().toString();
                place=e2.getText().toString();
                phone=e3.getText().toString();
                email=e4.getText().toString();



                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) Postmyideas.this;
                String q = "/Postmyideas?login_id="+sh.getString("log_id", "")+"&name="+name+"&place="+place+"&Phone="+phone+ "&email="+email;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), " SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userhome.class));

            }

            else if (status.equalsIgnoreCase("already")) {
                Toast.makeText(getApplicationContext(), "NO AMOUNT IN WALLET", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Userhome.class));

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}