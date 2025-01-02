package tlu.cse.android.ht63.quanlychitieu.Other;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.google.BTL_Quanlychitieu.BroadcastReciver.AlarmReceiver;
import com.google.BTL_Quanlychitieu.Entity.ChiDuKien;

import java.time.LocalDateTime;
import java.util.Calendar;

public class CustomPendingIntent {
    public static void CreatePendingintent(Context context, ChiDuKien chiDuKien, LocalDateTime localDateTime)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent it = new Intent(context, AlarmReceiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            calendar.set(Calendar.YEAR, localDateTime.getYear());
            calendar.set(Calendar.DAY_OF_MONTH, localDateTime.getDayOfMonth());
            calendar.set(Calendar.MONTH, localDateTime.getMonthValue()-1);
            calendar.set(Calendar.HOUR_OF_DAY, localDateTime.getHour());
            calendar.set(Calendar.MINUTE, localDateTime.getMinute());
        }
        it.putExtra("ContentText","Đến hạn khoản chi dự kiến ("+chiDuKien.ten+")");
        it.setAction("Myaction");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, chiDuKien.idpending,it,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Log.d("thongbao","Them thanh cong khoan du kien "+ chiDuKien.idpending);
    }

    public static void removePendingIntent(Context context, ChiDuKien chiDuKien)
    {
        Intent it = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, chiDuKien.idpending,it,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        //Log.d("thongbao","Huy thanh cong khoan du kien "+ chiDuKien.idpending);
        pendingIntent.cancel();
    }
}
