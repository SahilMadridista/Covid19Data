package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import Recycler.DetailsRecyclerViewAdapter;
import listClass.ListItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private Button refresh;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refresh = (Button)findViewById(R.id.refresh_button);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadRecyclerViewData();
                Toast.makeText(MainActivity.this,"Data Loaded",Toast.LENGTH_SHORT).show();
            }
        });

        listItems = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.details_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DetailsRecyclerViewAdapter(listItems,getApplicationContext());
        recyclerView.setAdapter(adapter);

        listItems = new ArrayList<>();

        loadRecyclerViewData();


    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        String URL_DATA = "https://api.covid19api.com/summary";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Countries");
                    for(int i = 0;i<array.length();i++) {
                        JSONObject object = array.getJSONObject(i);
                        ListItem item = new ListItem(
                                object.getString("Country"),
                                object.getString("TotalConfirmed"),
                                object.getString("TotalRecovered"),
                                object.getString("TotalDeaths")
                        );
                        listItems.add(item);
                        adapter = new DetailsRecyclerViewAdapter(listItems, getApplicationContext());
                        recyclerView.setAdapter(adapter);
                        progressDialog.dismiss();
                    }

                } catch (JSONException e) {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Volley",error.toString());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();


            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

            }

}

