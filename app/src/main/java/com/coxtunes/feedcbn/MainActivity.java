package com.coxtunes.feedcbn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String url = "http://coxsbazarnews.com/feed/json";
    private String myblogurl = "http://thisisabir.com/feed/json";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textview);
        //loadcbnheadline();
        loadtechblogheadline();
    }

    private void loadcbnheadline() {

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String title = object.getString("title");

                        JSONObject authorobject = object.getJSONObject("author");
                        String authorname = authorobject.getString("name");
                        textView.append(title+"\n"+authorname+"\n\n");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(objectRequest);

    }

    private void loadtechblogheadline() {

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, myblogurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("items");
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String title = object.getString("title");
                        String datetime = object.getString("date_published");
                        String modify = datetime.replace("T"," সময়ঃ ");
                        JSONObject authorobject = object.getJSONObject("author");
                        String authorname = authorobject.getString("name");
                        textView.append(title+"\n"+authorname+"\n"+"তারিখঃ "+modify+"\n\n");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(objectRequest);

    }



}
