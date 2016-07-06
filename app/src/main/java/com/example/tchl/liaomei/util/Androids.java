package com.example.tchl.liaomei.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by tchl on 2016-07-06.
 */
public class Androids {

    public static void copyToClipBoard(Context context, String text, String success) {
        ClipData clipData = ClipData.newPlainText("liaomei_copy", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(clipData);
        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
    }
}
