package com.example.handler_asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsycnActivity extends AppCompatActivity implements View.OnClickListener {
    //khai bao doi tuong progressBar
    private ProgressBar progressBar;
    private Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asycn);
        getViews();
    }

    private void getViews(){
        progressBar = findViewById(R.id.progressBar);
        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnShow:
                //thuc hien tinh nang cap nhat progress bar o day
                //khoi tao mot doi tuong cua lop ProgressAsyncTask
                new ProgressAsyncTask().execute();
                break;
            default:
                break;
        }
    }

    //viet lop xu ly asynctask
    private class ProgressAsyncTask extends AsyncTask<Void, Integer,String> {

        @Override
        protected String doInBackground(Void... voids) {
            //thuc thi cong viec duoi background o day
            for(int i=0;i<=100;i++){
                publishProgress(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "DONE";
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(AsycnActivity.this, s, Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //cap nhat giao dien thanh progressbar
            progressBar.setProgress(values[0]);
        }
    }
}