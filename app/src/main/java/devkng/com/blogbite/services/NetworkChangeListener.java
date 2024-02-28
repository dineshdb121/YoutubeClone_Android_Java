package devkng.com.blogbite.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import devkng.com.blogbite.others.Uitlity;

/* loaded from: classes2.dex */
public class NetworkChangeListener extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!Connection.isConnectedToInternet(context)) {
            Uitlity.showToast(context, "connected");
        } else {
            Uitlity.showToast(context, "Not connected");
        }
    }
}