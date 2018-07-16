package com.sharedpreferencesornek.akilinc.sharedpreferencesornek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //session durumu
    SessionManager session;

    //EditText'lerimiz
    EditText etEmail;
    EditText etSifre;
    Button btnGiris;

    //EditTextlerdeki verileri kaydedeceğimiz Stringler
    String email, sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session Manager tanımladık.
        session = new SessionManager(getApplicationContext());

        //activity mizin başında hemen login durumunu kontrol ediyoruz, eğer session varsa giriş ekranını geçiyoruz.
        if (session.isLoggedIn() == true){

            startActivity(new Intent(MainActivity.this, AsilSayfa.class));
        }

        //xml dosyasındaki objelerin tanımlamalarını yaptık.
        etEmail = findViewById(R.id.editTextEmail);
        etSifre = findViewById(R.id.editTextSifre);
        btnGiris = findViewById(R.id.buttonGiris);

        //button'ın onClickListener'ı
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //editTextlerimizin boş olup olmadığını denetliyoruz.
                if (etEmail.getText().toString().isEmpty() || etSifre.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Alanlar Boş Bırakılamaz.", Toast.LENGTH_LONG).show();
                }

                else{

                    //EditTextler boş değil ise Stringlerimize atadık.
                    email = etEmail.getText().toString();
                    sifre = etSifre.getText().toString();

                    //login kontrolünü yapacağımız fonksiyona Stringlerimizi yolladık.
                    loginCheck(email, sifre);
                }
            }
        });
    }

    //giriş kontrolünü yapacağımız fonksiyon.
    //Burada normalde veritabanından verilerle kıyaslamamız daha mantıklı olacaktır.
    //Bizim konumuz SharedPreferences olduğu için basit iki değer ile kıyasladık.
    private void loginCheck(String email, String sifre){

        //EditTextlerdeki verilerle olmasını istediğimiz verileri kıyasladık.
        if (email.equals("ahmet@ahmetkilinc.net") && sifre.equals("ahmet")){

            Toast.makeText(getApplicationContext(), "Giriş Başarılı.", Toast.LENGTH_LONG).show();

            //giriş başarılı ise Session'a verilerimizi yolladık.
            session.createLoginSession(email, sifre);

            //sonraki sayfaya yönlendirdik.
            startActivity(new Intent(MainActivity.this, AsilSayfa.class));
        }

        else{

            //giriş başarısız mesajı yazdırdık.
            Toast.makeText(getApplicationContext(), "Email veya Sifre Hatalı. Tekrar Deneyin.", Toast.LENGTH_LONG).show();
        }
    }
}
