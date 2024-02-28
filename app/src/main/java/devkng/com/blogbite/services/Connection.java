package devkng.com.blogbite.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes2.dex */
public class Connection {
    public static boolean isConnectedToInternet(Context context) {
        NetworkInfo[] info;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null && (info = connectivityManager.getAllNetworkInfo()) != null) {
            for (NetworkInfo networkInfo : info) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}