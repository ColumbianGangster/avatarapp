package com.example.linearalgebra.avatarapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linearalgebra.avatarapp.R;
import com.example.linearalgebra.avatarapp.api.Network;
import com.example.linearalgebra.avatarapp.model.Person;
import com.example.linearalgebra.avatarapp.utils.BitmapUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class MainViewHolder  extends RecyclerView.ViewHolder{
    @BindView(R.id.main_image)
    ImageView imageView;
    @BindView(R.id.main_firstName)
    TextView firstName;
    @BindView(R.id.main_lastName)
    TextView lastName;

    private Person person;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(v -> {
            Intent intent = DetailActivity.getIntent(v.getContext(), person);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(intent);
        });
    }

    public void bind(Person person, Context context) {
        this.person = person;
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        loadImage(context, person.getAvatarImage(), person.getImageCache());
    }

    private void loadImage(Context context, String url, byte[] cache) {
        if(Network.isAvailable(context)){
            Picasso.with(context).load(url).into(imageView);
        } else {
            imageView.setImageBitmap(BitmapUtils.convert(cache));
        }
    }
}
