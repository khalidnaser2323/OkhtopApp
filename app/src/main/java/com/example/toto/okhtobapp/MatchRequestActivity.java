package com.example.toto.okhtobapp;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class MatchRequestActivity extends AppCompatActivity {
    ArrayList<RowItems> sent = new ArrayList<>(), received = new ArrayList<>();

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_match_request);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //change activity lablel
        Window w = getWindow();
        setTitle("Match requests");
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        getSupportActionBar().setElevation(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.match_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filter) {
            Toast.makeText(MatchRequestActivity.this, "Filter", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);

    }

    public void getPosters() {
        received.clear();
        sent.clear();

        String url = "https://";

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Response: ", response.toString());
                        if (response.length() == 0) {
                        }
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonResponse = response.getJSONObject(i);
                                String posterId, name, city, useerPic;

                                posterId = jsonResponse.getString("posterId");
                                city = jsonResponse.getString("city");
                                name = jsonResponse.getString("name");

                                if (jsonResponse.get("type").toString().equals("offer")) {
                                    RowItems receives = new RowItems(posterId, city, name);
                                    received.add(receives);
                                } else if (jsonResponse.get("type").toString().equals("request")) {
                                    RowItems send = new RowItems(posterId, city, name);
                                    sent.add(send);
                                }
                                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {

                                    }

                                    @Override
                                    public void onTabUnselected(TabLayout.Tab tab) {

                                    }

                                    @Override
                                    public void onTabReselected(TabLayout.Tab tab) {

                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("get posters error", error.toString());
                Toast.makeText(MatchRequestActivity.this, "You cannot connect to the internet", Toast.LENGTH_LONG).show();
            }
        }) {

            public String getBodyContentType() {
                return "application/json";
            }

        };

        AppController.getInstance().addToRequestQueue(req);

    }

}