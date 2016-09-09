package com.example.linearalgebra.avatarapp.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by LinearAlgebra on 09/09/2016.
 */
public class BitmapUtils {
    public static Bitmap convert(byte[] img){
        return BitmapFactory.decodeByteArray(img, 0, img.length);
    }
}
