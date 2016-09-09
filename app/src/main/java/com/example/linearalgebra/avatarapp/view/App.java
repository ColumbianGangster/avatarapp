package com.example.linearalgebra.avatarapp.view;

import android.app.Application;
import android.content.Context;

import com.example.linearalgebra.avatarapp.api.Network;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class App extends Application {
    private Network network;

    @Override
    public void onCreate(){
        super.onCreate();
        // Best practice: https://realm.io/docs/java/latest/#best-practices
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public static App getInstance(Context context) {
        return (App) context.getApplicationContext();
    }

    public Network getClient() {
        if(network == null) {
            network = new Network();
        }
        return network;
    }
}
