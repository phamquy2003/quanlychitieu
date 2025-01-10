package tlu.cse.android.ht63.quanlychitieu.BroadcastReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.BTL_Quanlychitieu.Other.Notificationn;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == "Myaction")
        {
            //Log.d("thongbao","den gio roi");
            Notificationn.sendnotification(context, intent);
        }
    }
}
