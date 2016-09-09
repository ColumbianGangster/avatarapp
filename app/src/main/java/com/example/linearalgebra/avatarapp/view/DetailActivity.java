package com.example.linearalgebra.avatarapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linearalgebra.avatarapp.R;
import com.example.linearalgebra.avatarapp.api.Network;
import com.example.linearalgebra.avatarapp.model.Person;
import com.example.linearalgebra.avatarapp.utils.BitmapUtils;
import com.example.linearalgebra.avatarapp.utils.DateUtils;
import com.squareup.picasso.Picasso;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_firstName)
    TextView detail_firstName;
    @BindView(R.id.detail_LastName)
    TextView detail_LastName;
    @BindView(R.id.detail_DateOfBirth)
    TextView detail_DateOfBirth;
    @BindView(R.id.detail_Role)
    TextView detail_Role;

    @BindView(R.id.detail_image)
    ImageView imageView;

    private static final String EXTRA_FIRST_NAME = "extra.firstname";
    private static final String EXTRA_LAST_NAME = "extra.lastname";
    private static final String EXTRA_DOB = "extra.dob";
    private static final String EXTRA_AVATAR_IMAGE = "extra.avatar_img";
    private static final String EXTRA_ROLE = "extra.role";
    private static final String EXTRA_AVATAR_IMAGE_CACHE = "extra.avatar_img_cache";

    public static Intent getIntent(Context context, Person person) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_FIRST_NAME, person.getFirstName());
        intent.putExtra(EXTRA_LAST_NAME, person.getLastName());
        intent.putExtra(EXTRA_DOB, person.getDateOfBirth());
        intent.putExtra(EXTRA_AVATAR_IMAGE, person.getAvatarImage());
        intent.putExtra(EXTRA_ROLE, person.getRole());
        intent.putExtra(EXTRA_AVATAR_IMAGE_CACHE, person.getImageCache());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview_item);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ButterKnife.bind(this);
        setFirstName(getString(EXTRA_FIRST_NAME));
        setLastName(getString(EXTRA_LAST_NAME));
        setDateOfBirth(formatDob(getString(EXTRA_DOB)));
        setImage(getString(EXTRA_AVATAR_IMAGE), getIntent().getByteArrayExtra(EXTRA_AVATAR_IMAGE_CACHE));
        setRole(getString(EXTRA_ROLE));
    }

    private String formatDob(String unformattedDob) {
        Date d = DateUtils.convertToDate(unformattedDob);
        return DateUtils.ordinalDay(d) + " " + DateUtils.convertToString(d);
    }

    private String getString(String extra) {
        return getIntent().getStringExtra(extra);
    }

    public void setImage(String url, byte[] cache) {
        if (Network.isAvailable(this)) {
            Picasso.with(this).load(url).into(imageView);
        } else {
            imageView.setImageBitmap(BitmapUtils.convert(cache));
        }
    }

    public void setFirstName(String firstName) {
        detail_firstName.setText(firstName);
    }

    public void setLastName(String lastName) {
        detail_LastName.setText(lastName);
    }

    public void setDateOfBirth(String dateOfBirth) {
        detail_DateOfBirth.setText(dateOfBirth);
    }

    public void setRole(String role) {
        detail_Role.setText(role);
    }
}
