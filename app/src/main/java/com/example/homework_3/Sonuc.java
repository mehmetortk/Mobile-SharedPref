package com.example.homework_3;

import android.app.Application;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sonuc extends AppCompatActivity {
    AlertDialog.Builder builder;
    SharedPreferences.Editor editor;
    String getKullanici, getTarih, getParola;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sonuc);

        TextView txtKullanici, txtParola, txtKayitTarih;
        Button btnExit;
        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        getTarih = sharedPreferences.getString("date", null);
        getKullanici = sharedPreferences.getString("username", null);
        getParola = sharedPreferences.getString("password", null);

        txtKullanici = (TextView) findViewById(R.id.txtKullanici);
        txtParola = (TextView) findViewById(R.id.txtParola);
        txtKayitTarih = (TextView) findViewById(R.id.txtKayitTarih);
        btnExit = (Button) findViewById(R.id.btnExit);

        txtKullanici.setText(getKullanici);
        txtParola.setText(getParola);
        txtKayitTarih.setText(getTarih);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(Sonuc.this);

                builder.setTitle("Uyarı").setMessage("Çıkış yapmak üzeresiniz..").setPositiveButton("Çıkış", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        finishAffinity();
                    }
                }).setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }
}