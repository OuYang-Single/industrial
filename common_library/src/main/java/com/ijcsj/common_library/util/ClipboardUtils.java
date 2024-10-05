package com.ijcsj.common_library.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
 
public class ClipboardUtils {
 
    public static void copyTextToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("label", text));
    }
}