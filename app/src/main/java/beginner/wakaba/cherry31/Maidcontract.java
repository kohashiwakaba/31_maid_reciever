package beginner.wakaba.cherry31;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class Maidcontract extends BroadcastReceiver {

    /*

    계약 알림은 헤드업이 나타나지 않음

     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        String m,n,q;
        String nc = "MAID_CONT";

        m = intent.getStringExtra("maid_cont");
        NotificationCompat.Builder noti =
                new NotificationCompat.Builder(context,"maid_cont")
                        .setSmallIcon(R.drawable.ic_favorite_border_black_24dp)
                        .setContentTitle("메이드 "+m+"와(과)의 계약을 신청했습니다.")
                        .setContentText("메이드 신청 안내 다이얼로 이동합니다")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setChannelId(nc)
                ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "메이드 계약";
            String description = "메이드 계약 알림";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
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
        nm2.notify(01,noti.build());

        Intent s = new Intent();
        s.setAction(Intent.ACTION_VIEW);
        s.setData(Uri.parse("tel:0212344321"));
        context.startActivity(s);
    }
}
