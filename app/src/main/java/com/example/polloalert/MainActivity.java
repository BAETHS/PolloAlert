package com.example.polloalert;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.POST_NOTIFICATIONS},120);
            }
        }
        crearCanal();
        Intent intent = new Intent(this, PolloAlertService.class);
        PolloAlert dialogFragment = PolloAlert.newInstance();
        // Usa getSupportFragmentManager() para Compatibility Fragments
        dialogFragment.show(getSupportFragmentManager(), "dialog_imagen_tag");
        crearCanal();
        startService(intent);

    }

    private void crearCanal() {

        //1ER PASO: Confirmar que la versión sea mayor a O
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //2DO PASO: Crear el canal
            NotificationChannel notificationChannel = new NotificationChannel("121", "Pollo Alert", NotificationManager.IMPORTANCE_HIGH);
            //3ER PASO: Crear la descripción
            notificationChannel.setDescription("Crear notificación para pollo alert");
            //4TO PASO: Crear el notification manager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            //5TO PASO: configurar manager con canal
            notificationManager.createNotificationChannel(notificationChannel);
        }
        //6TO PASO: Crear el intent
        Intent notipollo = new Intent(this, MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(this, 0, notipollo, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        //7MO PASO: Crear la notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"121")
                .setSmallIcon(R.drawable.polloalert)
                .setContentTitle("Pollo Alert")
                .setOngoing(true)  // Impide que el usuario la descarte
                .addAction(android.R.drawable.dialog_holo_light_frame,"Activar Pollo Alert",intent)
                ;

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());  // ¡Aquí se muestra la notificación!

    }
}