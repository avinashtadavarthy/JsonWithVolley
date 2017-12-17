package com.example.avinash.jsonwithvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends AppCompatActivity {

    EditText id;
    EditText pwd;
    Button parse;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        id = (EditText) findViewById(R.id.id);
        pwd = (EditText) findViewById(R.id.pwd);
        parse = (Button) findViewById(R.id.parse);
        display = (TextView) findViewById(R.id.display);

        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postJsonRequest();
                //getJsonRequest();
            }
        });

    }

    private void postJsonRequest() {
        String url = "http://24crafts.tk:3000/login";

        final String userid = id.getText().toString();
        final String password = pwd.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                display.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("username", userid);
                params.put("password", password);

                return params;
            }

            /*

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                params.put("authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "<username>", "<password>").getBytes(), Base64.DEFAULT)));

                return params;
            }

            */
        };

        requestQueue.add(stringRequest);
    }

}
