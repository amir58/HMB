package com.gp.hmb;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.gp.hmb.model.Child;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static android.content.Context.MODE_PRIVATE;

public class MyNotificationWorker extends Worker {
    private static final String TAG = "MyNotificationWorker";

    private int days;

    public MyNotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i(TAG, "doWork: " + days);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("users").document(getUserId()).collection("children")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Child child = snapshot.toObject(Child.class);

                            String childId = snapshot.getId();

                            calculateDate(child.getDateOfBirth());

                            int months = days / 30;

                            if (child.getMonths() < months) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("months", months);

                                firestore.collection("users").document(getUserId())
                                        .collection("children").document(childId).update(map);

                                showNotification(months);
                            }
                        }

                        Log.i(TAG, "onEvent: " + days);
                    }
                });

        return Result.success();
    }

    private void showNotification(int months) {
        String text =" لقد اصبح عمر طفلك  "  + months + " شهور لا تنسي التطعميات";
//        String text = "لقد ازداد عمر طفلك شهراً لا تنسي التطعيمات";

        // Notification Manager, Builder, Channel
        NotificationManager notificationManager = (NotificationManager)
                getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "notificationChannelId";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel
                    (NOTIFICATION_CHANNEL_ID, "notificationChannelName",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 500, 0, 500});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID);

        notificationBuilder
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Alert")
                .setContentText(text);

        notificationManager.notify(1, notificationBuilder.build());

    }

    private String getUserId() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        return preferences.getString("phone", "");
    }

    private void calculateDate(String childDate) {
        childDate = childDate.replaceAll("/", " ");

        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        Date date = new Date();
        String currentDate = formatter.format(date);

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = "1 01 2021";
        String inputString2 = "1 04 2021";
        try {
            Date date1 = myFormat.parse(childDate);
            Date date2 = myFormat.parse(currentDate);
            long millis = date2.getTime() - date1.getTime();
            days = (int) TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
            System.out.println("Days: " + days);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


}
