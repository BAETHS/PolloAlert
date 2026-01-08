package com.example.polloalert;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PolloAlert extends DialogFragment {
    Button btn;

    public static PolloAlert newInstance() {
        return new PolloAlert();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.polloalert, container, false);
        btn = view.findViewById(R.id.btn_cerrar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (getActivity() != null) {
                    getActivity().finish();   // Cierra la Activity (y con ella la app)
                }
            }
        });

        return view;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Pollo Alert");
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                // Hacer que el dialog ocupe casi toda la pantalla (opcional)
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;  // Ancho completo
                params.height = WindowManager.LayoutParams.WRAP_CONTENT; // Alto según contenido
                window.setAttributes(params);

                window.setBackgroundDrawableResource(android.R.color.white);
            }
        }
    }

    // Opcional: hacer que no se cierre al tocar fuera (si quieres forzar interacción)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);  // No se cierra tocando fuera ni con back
    }
}