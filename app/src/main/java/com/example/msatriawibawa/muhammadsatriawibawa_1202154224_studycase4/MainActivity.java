package com.example.msatriawibawa.muhammadsatriawibawa_1202154224_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lihatNama(View view) {
        //Membuat intent untuk berpindah layout ke ListNamaMahasiswa
        Intent i = new Intent(this, ListNamaMahasiswa.class);

        startActivity(i);

    }

    public void lihatGambar(View view) {
        //Membuat intent untuk berpindah layout ke PencarianGambar
        Intent i = new Intent(this, PencarianGambar.class);

        startActivity(i);
    }
}
