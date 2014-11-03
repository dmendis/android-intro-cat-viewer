package com.m2d2apps.catviewer;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.m2d2apps.catviewer.adapters.InstagramPhotosAdapter;
import com.m2d2apps.catviewer.api.CatServices;
import com.m2d2apps.catviewer.models.Photo;
import com.m2d2apps.catviewer.models.PhotosList;

import java.util.ArrayList;


public class CatsActivity extends Activity {

    PhotosList photosList;
    InstagramPhotosAdapter adapter;

    ListView photosListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cats);

        adapter = new InstagramPhotosAdapter(this);
        photosListView = (ListView)findViewById(R.id.photosListView);
        photosListView.setAdapter(adapter);

        retrievePhotos();
    }

    private void retrievePhotos() {
        AsyncTask<Void, Void, PhotosList> task = new AsyncTask<Void, Void, PhotosList>() {
            @Override
            protected PhotosList doInBackground(Void... voids) {
                return CatServices.getInstagramService().getPhotosWithHashTag("cat");
            }

            @Override
            protected void onPostExecute(PhotosList photos) {
                super.onPostExecute(photos);
                Log.d("CatApp", photos.toString());
                photosList = photos;
                adapter.setPhotos(photosList.getPhotos());
            }
        };
        task.execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
