package com.example.linearalgebra.avatarapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linearalgebra.avatarapp.R;
import com.example.linearalgebra.avatarapp.model.Person;

import java.util.ArrayList;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{
    private final LayoutInflater layoutInflater;
    private ArrayList<Person> people;
    private Context context;

    public MainAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        people = new ArrayList<>();
        this.context = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.bind(people.get(position), context);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
}
