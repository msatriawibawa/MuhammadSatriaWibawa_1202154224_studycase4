package com.example.msatriawibawa.muhammadsatriawibawa_1202154224_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListNamaMahasiswa extends AppCompatActivity {

    // Buat Variab;e Baru
    private ListView mListView;
    private ProgressBar mLoading;
    private ListItem listItem;

    //Array berisi nama Mahasiswa
    private String[] Mahasiswa = {"Dina", "Rian", "Gina", "Osas", "Reno", "Lina", "Roi", "Shifa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_mahasiswa);

        // Declarasi berdasarkan id
        mListView = findViewById(R.id.listView);
        mLoading = findViewById(R.id.progressBar);

        //Membuat Adapter untuk menyimpan data nama mahasiswa dari array di atas
        ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>());

        mListView.setAdapter(adapter);
    }

    // Memulai AsyncTask ketika button di klik
    public void StartNama(View view) {
        listItem = new ListItem();
        listItem.execute();
    }

    //Membuat class proses AsysncTask untuk memasukan item ke dalam listview
    public class ListItem extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int mCounter = 1;
        ProgressDialog mDialog = new ProgressDialog(ListNamaMahasiswa.this);

        //Mnampilkan ProgressBar di UI
        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            //Menentukan jenis Progress Dialog dan menampilkannya
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //Menentukan judul text yang muncul pada ProgressBar
            mDialog.setTitle("Loading Data");
            mDialog.setProgress(0);

            //Membuat Tombol Cancel
            mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listItem.cancel(true);
                    //Menampilkan progress bar pada layar dialog setelah diklik cancel
                    mLoading.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            //Menampilakan ProgressBar
            mDialog.show();
        }

        //Melakukan proses di Background untuk data Array Mahasiswa
        @Override
        protected Void doInBackground(Void... params) {
            for (String data : Mahasiswa){
                publishProgress(data);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Pemisalan bila melakukan Cancel pada saat progressbar muncul
                if(isCancelled()){
                    listItem.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            //Membuat integer untuk Proses Loading dan Perhitungan Persen
            Integer loading = (int) ((mCounter / (float) Mahasiswa.length) * 100);
            mLoading.setProgress(loading);

            //Meemasang status loading pada ProgressDialog
            mDialog.setProgress(loading);

            //Menambahkan tanda Persen pada ProgressDialog
            mDialog.setMessage(String.valueOf(loading + "%"));
            mCounter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Membuat Progress Bar menghilang
            mLoading.setVisibility(View.GONE);

            mDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}