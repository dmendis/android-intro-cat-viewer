package com.m2d2apps.catviewer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.m2d2apps.catviewer.R;
import com.m2d2apps.catviewer.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InstagramPhotosAdapter extends BaseAdapter {

    Context context;
    private ArrayList<Photo> photos;

    public InstagramPhotosAdapter(Context context) {
        this.context = context;
        photos = new ArrayList<Photo>();
    }

    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int pos) {
        return photos.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return photos.get(pos).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_photo, container, false);
        }

        Photo photo = (Photo)getItem(position);

        ImageView imageView = (ImageView)view.findViewById(R.id.photo);
        Picasso.with(context).load(photo.photoUrl).resize(640, 640).centerCrop().into(imageView);

        TextView caption = (TextView)view.findViewById(R.id.caption);
        caption.setText(photo.caption);

        return view;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }
}
