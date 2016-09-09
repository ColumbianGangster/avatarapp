package com.example.linearalgebra.avatarapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.example.linearalgebra.avatarapp.model.Person;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class RealmUtils {
    @NonNull
    public static ArrayList<Person> map(RealmResults<Person> people, ArrayList<Person> arr) {
        for(Person p : people) {
            arr.add(p);
        }
        return arr;
    }

    public static void setPrimaryKeys(ArrayList<Person> people) {
        for(int i = 0; i < people.size(); i++) {
            people.get(i).setId(i);
        }
    }

    public static void populate(Person p) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(p);
        realm.commitTransaction();
    }

    public static void convertImg(Context context, String url, Person person){
        Picasso.with(context)
                .load(url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
                            /* Save the bitmap or do something with it here */

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        person.setImageCache(byteArray);
                        RealmUtils.populate(person);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        // do nothing
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        // do nothing
                    }
                });
    }

    public static void convertImages(ArrayList<Person> people, Context context) {
        for(Person p : people) {
            convertImg(context, p.getAvatarImage(), p);
        }
    }
}
