package com.sharedpreferencesornek.akilinc.sharedpreferencesornek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class AsilSayfa extends AppCompatActivity {

    // Session Manager Class
    SessionManager session;

    //TextViewlerimiz
    TextView tvEmail;
    TextView tvSifre;

    //Çıkış butonumuz
    Button btnCikis;

    //sessiondan alacağımız Stringlerimizi kaydedeceğimiz Stringler
    String email, sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asil_sayfa);

        //TextView tanımlamaları
        tvEmail = findViewById(R.id.textViewemail);
        tvSifre = findViewById(R.id.textViewsifre);
        btnCikis = findViewById(R.id.buttonCikis);

        // session verileri için sınıfımızı çağırdık
        session = new SessionManager(getApplicationContext());

        // sessiondan kullanıcı verilerini almak için nesnemizi oluşturduk.
        HashMap<String, String> user = session.getUserDetails();

        //keylerine göre user nesnemizden verilerimizi çağırdık ve ekledik.
        email = user.get(SessionManager.KEY_EMAIL);
        sifre = user.get(SessionManager.KEY_SIFRE);


        //session'ın varlığını sorguluyoruz. eğer boş ise giriş sayfasına yönlendiriyoruz.
        if (email.isEmpty() || sifre.isEmpty()){

            Toast.makeText(getApplicationContext(), "Lütfen Giriş Yapınız.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        else{

            //session boş değil ise TextViewlerimize session bilgilerini yazdırıyoruz.
            tvEmail.setText(email);
            tvSifre.setText(sifre);
        }

        //çıkış butonunun onClickListener'ı
        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Session çıkış fonksiyonunu çalıştırıyoruz.
                session.logoutUser();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //çıkış yapıldıktan sonra giriş sayfasında geri butonu sorununu çözmek için flagler ayarladık.
                i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP | i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}
