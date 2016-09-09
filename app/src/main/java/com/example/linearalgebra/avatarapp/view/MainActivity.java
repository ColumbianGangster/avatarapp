package com.example.linearalgebra.avatarapp.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.linearalgebra.avatarapp.BaseActivity;
import com.example.linearalgebra.avatarapp.R;
import com.example.linearalgebra.avatarapp.api.Network;
import com.example.linearalgebra.avatarapp.model.Person;
import com.example.linearalgebra.avatarapp.utils.RealmUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    Network network;
    private Realm realm;

    @Override
    protected void onCreateView() {
        network = App.getInstance(getApplicationContext()).getClient();
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        MainAdapter adapter = new MainAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);
        fetchPeople(adapter);
    }

    private void fetchPeople(final MainAdapter adapter) {
        // further research: Realm has first-class support for RxJava. Really cool. https://realm.io/docs/java/latest/#rxjava
        if(Network.isAvailable(this)){
            network.fetchPeople()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(people -> {
                        RealmUtils.setPrimaryKeys(people);
                        adapter.setPeople(people);
                        adapter.notifyDataSetChanged();
                        RealmUtils.convertImages(people, this);
                    }, Throwable::printStackTrace);
        } else {
            attemptGetFromDatabase(adapter);
        }
    }

    private void attemptGetFromDatabase(final MainAdapter adapter) {
        final RealmResults<Person> people = realm.where(Person.class).findAll();
        if(people.isEmpty()){
            Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_LONG).show();
        } else {
            adapter.setPeople(RealmUtils.map(people, new ArrayList<>(people.size())));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
