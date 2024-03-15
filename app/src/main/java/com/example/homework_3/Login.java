package com.example.homework_3;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    TextView txtuserName, txtPassword;
    CheckBox cbRemember;
    Button btnLogin2;
    String getUsername, getPassword;
    Boolean getFlag;

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);

        btnLogin2 = (Button) findViewById(R.id.btnLogin2);
        txtuserName = (TextView) findViewById(R.id.txtuserName);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);


        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        getUsername = sharedPreferences.getString("username", null);
        getPassword = sharedPreferences.getString("password", null);
        getFlag = sharedPreferences.getBoolean("flag", false);

        if (getFlag.equals(true)) {
            txtuserName.setText(getUsername);
            txtPassword.setText(getPassword);
            cbRemember.setChecked(true);
            Intent I2= new Intent(Login.this,Anasayfa.class);
            startActivity(I2);
        }


        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtuserName.getText().toString();
                String password = txtPassword.getText().toString();

                if ((!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) && (getUsername.equals(userName) && getPassword.equals(password))) {

                    if (cbRemember.isChecked()) {


                        Boolean flag = true;
                        editor = sharedPreferences.edit();
                        editor.putBoolean("flag", flag);
                        editor.apply();

                    }
                    Intent I3 = new Intent(Login.this, Anasayfa.class);
                    startActivity(I3);
                    Toast.makeText(Login.this, "Anasayfaya Yönlendiriliyorsunuz...", Toast.LENGTH_LONG).show();
                } else if (userName.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Uyarı").setMessage("Kullanıcı adı veya Parola boş olamaz!!").setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }

                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else if (!getUsername.equals(userName) || !getPassword.equals(password)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Uyarı").setMessage("Kullanıcı adı veya Parola yanlış!!").setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }

                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });

    }


}
