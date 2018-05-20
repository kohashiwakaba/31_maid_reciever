package beginner.wakaba.cherry31;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class MaidReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        //PendingIntent pi = PendingIntent.getBroadcast(context,00,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        String m,n,q;
        String nc = "MAID";

        m = intent.getStringExtra("maid");
        n = intent.getStringExtra("menu");
        q = intent.getStringExtra("quantity");

        NotificationCompat.Builder noti =
                new NotificationCompat.Builder(context,"maid")
                    .setSmallIcon(R.drawable.ic_favorite_border_black_24dp)
                    .setContentTitle("메이드 "+m+"을(를) 호출했습니다.")
                    .setContentText(m+"에게 "+n+" "+q+"개를 주문하셨습니다.")
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setChannelId(nc)
                ;
        /*
        안드로이드 8.0부터는 알림 그룹이 필수적으로 요구됨.
        채널을 설정하지 않으면 알림 자체가 나타나지 않음.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "메이드";
            String description = "메이드 주문 알림";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(nc, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationManager nm;
        nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManagerCompat nm2 = NotificationManagerCompat.from(context);
        nm2.notify(00,noti.build());



        Toast.makeText(context, "메이드 "+m+"에게 "+n+" "+q+"개를 주문하셨습니다.",Toast.LENGTH_LONG).show();
        /*
        Intent s = new Intent();
        s.setAction(Intent.ACTION_VIEW);
        s.setData(Uri.parse("tel:123456"));
        context.startActivity(s);
        */
    }

}
