package com.example.homework_3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    Button btnSignUp;
    TextView txtUsername2, txtPassword2, txtRepeatPassword;
    String username, password, re_password;


    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register);
        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);

        btnSignUp = (Button) findViewById(R.id.btnSignup);
        txtUsername2 = (TextView) findViewById(R.id.txtUsername2);
        txtPassword2 = (TextView) findViewById(R.id.txtPassword2);
        txtRepeatPassword = (TextView) findViewById(R.id.txtRepeatPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);


                username = txtUsername2.getText().toString();
                password = txtPassword2.getText().toString();
                re_password = txtRepeatPassword.getText().toString();
                if(username.isEmpty()||password.isEmpty()||re_password.isEmpty()){
                    builder.setTitle("Uyarı").setMessage("Alanlar boş olamaz!!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else if(!password.equals(re_password)){
                    Toast.makeText(Register.this, "Şifreler Eşleşmiyor!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.equals(re_password)){
                        editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.apply();


                        builder.setTitle("Başarılı..").setMessage("Kayıt başarılı anasayfaya yönlendiriliyorsunuz..").setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent I1= new Intent(Register.this,Login.class);
                                startActivity(I1);
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                }

            }
        });
    }


}
