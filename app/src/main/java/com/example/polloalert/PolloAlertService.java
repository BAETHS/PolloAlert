package com.example.polloalert;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;

import java.util.logging.Handler;

public class PolloAlertService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PeriodicWorkRequest request;
        request = new PeriodicWorkRequest.Builder(PolloWorker.class, 10, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "pollo_alert_periodico",                  // Nombre único
                ExistingPeriodicWorkPolicy.KEEP,          // Si ya existe, no crear otro
                request);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
