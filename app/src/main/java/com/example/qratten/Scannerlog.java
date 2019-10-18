package com.example.qratten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Scannerlog extends AppCompatActivity {
    TextView txt1;
    IntentIntegrator qrscan;
    SharedPreferences sharedPreference;
    String Qrid="inet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        sharedPreference = getSharedPreferences("asd", MODE_PRIVATE);
        txt1 = findViewById(R.id.textView);
        qrscan = new IntentIntegrator(this);
        qrscan.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "No matching QR CODE", Toast.LENGTH_LONG).show();
            } else {
                 if(result.getContents().equals(Qrid))
                 {
                     StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://intown-film.000webhostapp.com/attenreg.php",
                             new Response.Listener<String>() {
                                 @Override
                                 public void onResponse(String response) {
//If we are getting success from server

                                     Toast.makeText(Scannerlog.this, response, Toast.LENGTH_LONG).show();
                                     try {
                                         JSONArray jsonArray = new JSONArray(response);
                                         for (int i = 0; i < jsonArray.length(); i++) {
                                             JSONObject json_obj = jsonArray.getJSONObject(i);

                                         }
                                     } catch (JSONException e) {
                                         e.printStackTrace();
                                     }

                                 }
                             },
                             new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                 }

                             }) {
                         @Override
                         protected Map<String, String> getParams() throws AuthFailureError {
                             Map<String, String> params = new HashMap<>();
//Adding parameters to request

                             params.put("name",sharedPreference.getString("name","*****"));
                             params.put("s_id",sharedPreference.getString("S_id","*****"));;
//returning parameter
                             return params;
                         }
                     };

//Adding the string request to the queue
                     RequestQueue requestQueue = Volley.newRequestQueue(Scannerlog.this);
                     requestQueue.add(stringRequest);

                 }


            }
        }
     else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}




