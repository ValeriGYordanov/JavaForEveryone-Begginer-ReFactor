package com.example.valery.javaforeveryone_begginer.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.valery.javaforeveryone_begginer.R;
import com.example.valery.javaforeveryone_begginer.ui.MainActivity;


/**
 * Created by Valery on 3/16/2018.
 */
public class NotifierService extends Service{

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        final IntentFilter batteryChangeFilter = new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED
        );
        this.registerReceiver(new BatteryReceiver(), batteryChangeFilter);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class BatteryReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                fullBatteryAPI26(context);
            }else{
                fullBattery(context);
            }

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void fullBatteryAPI26(Context context){
            Bitmap bitmap = getBitmap(context, R.mipmap.ic_launcher);
            Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

            int currentBatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            Intent notifier = new Intent(context, MainActivity.class);
            Notification notification;
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, notifier, 0);

            if (currentBatteryLevel == 100){

                notification = new Notification.Builder(context, "512312")
                        .setTicker("BatteryTicker")
                        .setContentTitle("Hello There...")
                        .setContentText("Looks like your battery is full, Time to learn?")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(bitmap)
                        .setContentIntent(pIntent).build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                NotificationManager nManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel("512312", "JFE", nManager.IMPORTANCE_DEFAULT);
                nManager.createNotificationChannel(channel);


                nManager.notify(0, notification);
            }


        }
        public void fullBattery(Context context){
            Bitmap bitmap = getBitmap(context, R.mipmap.ic_launcher);
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            //BatteryLevel is always 0?!
            int currentBatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            NotificationCompat.Builder notificationBuilder = new android.support.v7.app.NotificationCompat.Builder(context)
                    .setContentTitle("Java For Everyone")
                    .setContentText("Looks like your battery is full, Time to learn?")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(bitmap)
                    .setAutoCancel(true)
                    .setContentIntent(pIntent);

            NotificationManager nManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

            nManager.notify(0, notificationBuilder.build());

        }

        private Bitmap getBitmap(VectorDrawable vectorDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
            return bitmap;
        }
        private Bitmap getBitmap(Context context, int drawableId) {
            Drawable drawable = ContextCompat.getDrawable(context, drawableId);
            if (drawable instanceof BitmapDrawable) {
                return BitmapFactory.decodeResource(context.getResources(), drawableId);
            } else if (drawable instanceof VectorDrawable) {
                return getBitmap((VectorDrawable) drawable);
            } else {
                throw new IllegalArgumentException("unsupported drawable type");
            }
        }
    }
}