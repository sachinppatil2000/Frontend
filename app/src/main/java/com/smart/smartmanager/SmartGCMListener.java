package com.smart.smartmanager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
//import

import com.google.android.gms.gcm.GcmListenerService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.smart.smartmanager.R;


/**
 * Created by sachin_patil06 on 1/11/2016.
 */
public class SmartGCMListener extends GcmListenerService {

        private static final String TAG = "GcmListenerService";

        /**
         * Called when message is received.
         *
         * @param from SenderID of the sender.
         * @param data Data bundle containing message data as key/value pairs.
         *             For Set of keys use data.keySet().
         */
// [START receive_message]
        @Override
        public void onMessageReceived(String from, Bundle data) {
            String message = data.getString("key1");
            Log.d(TAG, "From: " + from);
            Log.d(TAG, "Message: " + message);
            sendNotification(message);
            updateSharedPreferences(message);
            updateMyActivity(this,message);
        }
// [END receive_message]

        private void sendNotification(String message) {
            Intent intent = new Intent(this,usermachine.class);
            intent.putExtra("gcmMessage",message);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            android.support.v4.app.NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_setting_dark)
                    .setContentTitle("GCM factory Message")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }

    static void updateMyActivity(Context context, String message) {

        Intent intent = new Intent("gcmMessageIntent");

        //put whatever data you want to send, if any
        intent.putExtra("msgRecieved", message);

        //send broadcast
        context.sendBroadcast(intent);
    }
   private void updateSharedPreferences(String message)
   {
       SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("gcmdata", MODE_PRIVATE);
       SharedPreferences.Editor prefsEditor = mPrefs.edit();
       Gson gson = new Gson();
       JsonElement element = gson.fromJson(message, JsonElement.class);
       JsonObject jsonObj = element.getAsJsonObject();
       String Area = jsonObj.get("Location").getAsString();
      // gson.
       //String json = gson.toJson(myObject); // myObject - instance of MyObject
       prefsEditor.putString(Area,message);
       prefsEditor.commit();
   }
}
