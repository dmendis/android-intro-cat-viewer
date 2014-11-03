package com.m2d2apps.catviewer.api.serializers;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.m2d2apps.catviewer.models.Photo;
import com.m2d2apps.catviewer.models.PhotosList;

import org.json.JSONObject;

import java.lang.reflect.Type;

public class PhotosConverter implements JsonDeserializer<PhotosList>, JsonSerializer<PhotosList> {

    @Override
    public PhotosList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("CatApp", ">>>>>>>> " + json.toString());
        PhotosList list = new PhotosList();

        if (json.isJsonObject()) {
            JsonObject instagramData = json.getAsJsonObject();
            if (instagramData.get("data").isJsonArray()) {
                JsonArray data = instagramData.getAsJsonArray("data");
                for (int i = 0; i < data.size(); i++) {
                    JsonObject dataItem = data.get(i).getAsJsonObject();
                    Photo photo = new Photo();
                    photo.photoUrl = dataItem.getAsJsonObject("images").getAsJsonObject("standard_resolution").get("url").getAsString();
                    photo.postUrl = dataItem.get("link").getAsString();
                    photo.photoId = dataItem.get("id").getAsString();
                    if (!dataItem.get("caption").isJsonNull()) {
                        photo.caption = dataItem.getAsJsonObject("caption").get("text").getAsString();
                    } else {
                        photo.caption = "No caption.";
                    }
                    list.addPhoto(photo);
                }
            }
        }


        return list;
    }

    @Override
    public JsonElement serialize(PhotosList list, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray photosArray = new JsonArray();
        for (Photo photo : list.getPhotos()) {
            JsonObject element = new JsonObject();
            element.addProperty("id", photo.photoId);
            element.addProperty("photoUrl", photo.photoUrl);
            element.addProperty("postUrl", photo.postUrl);
            element.addProperty("caption", photo.caption);
            photosArray.add(element);
        }
        return photosArray;
    }
}
