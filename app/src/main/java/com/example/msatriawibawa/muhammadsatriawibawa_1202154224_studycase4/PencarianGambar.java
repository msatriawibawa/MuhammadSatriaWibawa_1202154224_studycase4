package com.example.msatriawibawa.muhammadsatriawibawa_1202154224_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;

public class PencarianGambar extends AppCompatActivity {

    //Membuat Variable
    private EditText mUrl;
    private ImageView mImage;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian_gambar);

        // Declarasi berdasarkan id
        mUrl = findViewById(R.id.inputUrl);
        mImage = findViewById(R.id.ImageView);

    }

    public void StartGambar(View view) {
        //Menambil data dari EditText
        String urlGambar = mUrl.getText().toString();
        //Mengeksekusi link url yang diisi di EditText
        new DownloadImage().execute(urlGambar);
    }

    //Membuat class proses AsysncTask untuk menampilkan Gambar
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        //Menampilkan ProgressDialog pada UI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Membuat progress dialog
            mDialog = new ProgressDialog(PencarianGambar.this);
            //Memasukan judul dan text pada Progress Dialog
            mDialog.setTitle("Search Image");
            mDialog.setMessage("Loading...");

            //Menampilakan Progress Dialog
            mDialog.show();
        }

        //Melakukan proses di Background untuk download Gambar
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                //Melakukan download image dari URL yang dimasukkan pada EditText
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            //Set bitmap ke dalam ImageView
            mImage.setImageBitmap(result);
            //Menghilangkan tampilan progress dialog
            mDialog.dismiss();
        }
    }
}
