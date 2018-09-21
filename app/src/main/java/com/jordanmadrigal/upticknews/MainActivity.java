package com.jordanmadrigal.upticknews;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";
    private static final String apiKey = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAppBarTranslucent(true);

        //connecting recyclerView to layout
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        //connecting recyclerView to layout manager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //adapter is located within the extract data method used with the dataRetrieval object
        JSONUtils dataRetrieval = new JSONUtils();
        dataRetrieval.extractData(url+apiKey, recyclerView, this);

    }

    //Method to get the status bar translucent
    protected void setAppBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}
