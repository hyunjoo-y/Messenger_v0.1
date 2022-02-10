package com.example.messenger;

import android.annotation.SuppressLint;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {
    TextView chatTextView;
    ScrollView chatScrollView;
    private WifiManager.MulticastLock multicastLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        findViewById(R.id.msgSendButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.SignUpButton:
                    sendText();
                    break;
            }
        }

    };

    private void sendText(){

    }

    private void chatMessage(String msg)throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        chatTextView.append(msg);
                        chatTextView.append("\n");
                        multicastLock.acquire();
                }
            });
            }
        }).start();
    }
}



