package com.m2d2apps.catviewer.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m2d2apps.catviewer.api.serializers.PhotosConverter;
import com.m2d2apps.catviewer.models.PhotosList;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;

public class CatServices {

    // https://api.instagram.com/v1/tags/cats/media/recent?access_token=86456.577b36b.acec7bbaf4ff4079b866910f48cd0784
    static final String token = "86456.577b36b.acec7bbaf4ff4079b866910f48cd0784";

    private static InstagramService instagramService = null;

    public static InstagramService getInstagramService() {
        if (instagramService == null) {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(PhotosList.class, new PhotosConverter())
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://api.instagram.com/v1")
                    .setConverter(new GsonConverter(gson))
                    .build();

            instagramService = restAdapter.create(InstagramService.class);
        }
        return instagramService;
    }

    public interface InstagramService {
        @GET("/tags/{tag}/media/recent?access_token=" + token)
        PhotosList getPhotosWithHashTag(@Path("tag") String tagName);
    }

}
