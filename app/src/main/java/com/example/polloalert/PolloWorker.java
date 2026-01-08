package com.example.polloalert;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class PolloWorker extends Worker {

    public PolloWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Aquí va lo que quieres que se ejecute cada cierto tiempo
        mostrarAlertaPollo();

        return Result.success();  // o retry() si falla
    }

    private void mostrarAlertaPollo() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);  // Usa tu Activity transparente
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getApplicationContext().startActivity(intent);
    }
}