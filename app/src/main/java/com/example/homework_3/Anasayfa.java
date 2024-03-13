package com.example.homework_3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Anasayfa  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.anasayfa);
    }
}
