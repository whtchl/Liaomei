package com.example.tchl.liaomei.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.tchl.liaomei.R;

/**
 * Created by tchl on 2016-06-23.
 */
public class Shares {

    public static void shareImage(Context context, Uri uri, String title) {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        //shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, title));
    }
    public  static void share(Context context,String extraText){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,context.getString(R.string.action_share));
        intent.putExtra(Intent.EXTRA_TEXT,extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent,context.getString(R.string.action_share)));
    }
}
