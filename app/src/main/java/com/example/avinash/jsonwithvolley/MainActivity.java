package com.example.avinash.jsonwithvolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    TextView textView;
    Button parse;

    final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        parse = (Button) findViewById(R.id.parse);

        requestQueue = Volley.newRequestQueue(this);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonArrayParse();
            }
        });

        //MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    private void jsonParse() {

        String url = "http://24crafts.tk:3000/directory/adcorp";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("adcorp");

                            for(int i = 0; i<jsonArray.length(); i++){
                                JSONObject adcorp = jsonArray.getJSONObject(i);

                                String address = adcorp.optString("ADDRESS"),
                                        name = adcorp.optString("NAME"),
                                        phone = adcorp.optString("PHONE"),
                                        email = adcorp.optString("EMAIL"),
                                        website = adcorp.optString("WEBSITE");

                                textView.append("Entry " + String.valueOf(i) + "\n");
                                if(!name.equals("")) textView.append("Name: " + name + "\n");
                                if(!address.equals("")) textView.append("Address: " + address + "\n");
                                if(!phone.equals("")) textView.append("Phone: " + phone + "\n");
                                if(!email.equals("")) textView.append("Email: " + email + "\n");
                                if(!website.equals("")) textView.append("Website: " + website + "\n");
                                textView.append("\n\n");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "There is an error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }


    private void jsonArrayParse() {

        String url = "http://24crafts.tk:3000/directory/adcorp";

        // prepare the Request
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int i = 0; i<response.length(); i++){
                            JSONObject adcorp = null;
                            try {
                                adcorp = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            String address = adcorp.optString("ADDRESS"),
                                    name = adcorp.optString("NAME"),
                                    phone = adcorp.optString("PHONE"),
                                    email = adcorp.optString("EMAIL"),
                                    website = adcorp.optString("WEBSITE");

                            textView.append("Entry " + String.valueOf(i+1) + "\n");
                            if(!name.equals("")) textView.append("Name: " + name + "\n");
                            if(!address.equals("")) textView.append("Address: " + address + "\n");
                            if(!phone.equals("")) textView.append("Phone: " + phone + "\n");
                            if(!email.equals("")) textView.append("Email: " + email + "\n");
                            if(!website.equals("")) textView.append("Website: " + website + "\n");
                            textView.append("\n\n");
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        // add it to the RequestQueue
        requestQueue.add(getRequest);

    }


}
