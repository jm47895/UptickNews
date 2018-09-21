package com.jordanmadrigal.upticknews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for handling JSON requests
 * Created by Jordan Madrigal on 1/31/18.
 */

public class JSONUtils {

    private static final String LOG_TAG = JSONUtils.class.getName();
    private RecyclerView.Adapter adapter;
    private String url;
    private String headline;
    private String sourceName;
    private String date;
    private String picSource;
    private List<News> news;

    public List<News> extractData (String stringUrl, final RecyclerView recyclerView, final Context activity){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.d(LOG_TAG, response.toString());

                try{
                    JSONArray newsArticles = response.getJSONArray("articles");
                    news = new ArrayList<>();

                    for (int i = 0; i < newsArticles.length(); i++){

                        JSONObject article = newsArticles.getJSONObject(i);

                        JSONObject source = article.getJSONObject("source");

                        headline = article.getString("title");

                        sourceName = source.getString("name");

                        date = article.getString("publishedAt");
                        date = date.substring(0,date.indexOf("T"));

                        picSource = article.getString("urlToImage");

                        url = article.getString("url");

                        news.add(new News(picSource, headline, sourceName, date, "Learn More", url));


                    }

                    adapter = new NewsAdapter(news, activity);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(LOG_TAG, "something went wrong parsing JSON");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, error.toString());
                Toast.makeText(activity, "There was a problem when trying to retrieve data", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(request);
        return news;
    }


}
