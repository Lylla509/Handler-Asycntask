package com.example.handler_asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Message;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//Khai báo 1 đối tượng handle
    private Handler handler;
    private TextView txtNumber;
    private Button btnStart;

    private  static final int UP_NUMBER = 100;
    private  static final int NUMBER_DONE = 101;
    private boolean isUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        processHandler();
    }

    private void getViews(){
        txtNumber = findViewById(R.id.txtNumber);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    /*private void processHandler(){
        //khởi tạo đối tượng handler
        handler= new Handler(getMainLooper()){
            @Override
            public void handlerMessage(@NonNull Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case UP_NUMBER:
                        isUpdate = true;
                        txtNumber.setText(String.valueOf(msg.arg1));
                        break;
                    case NUMBER_DONE:
                        txtNumber.setText("SUCCESS");
                        isUpdate=false;
                        break;
                    default:
                        break;
                }
            }
        };
    }*/
    private void processHandler(){
        //khởi tạo đối tượng handler
        handler= new Handler(getMainLooper()){
            //@Override
            public void handlerMessage(@NonNull Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case UP_NUMBER:
                        isUpdate = true;
                        txtNumber.setText(String.valueOf(msg.arg1));
                        break;
                    case NUMBER_DONE:
                        txtNumber.setText("SUCCESS!");
                        isUpdate=false;
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public  void onClick(View view){
        //thực thi công việc khi click vào button
        switch (view.getId()){
            case R.id.btnStart: //thực hiện công việc ở đây
                if(!isUpdate)
                    updNumber();
                break;
            default:
                break;
        }
    }
    private void updNumber(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //xử lý cập nhật giá trị ở đây
                for(int i=0; i<=10;i++){
                    //khai báo message để chứa nội dung tin nhắn cần đưa vào message pool
                    Message msg = new Message();
                    //gắn công việc cho msg
                    msg.what= UP_NUMBER;
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                //gửi tin nhắn thực hiện hoàn thành công việc
                handler.sendEmptyMessage(NUMBER_DONE);
            }
        }).start();
    }
}
