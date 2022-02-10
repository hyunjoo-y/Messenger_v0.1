package com.example.messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.ethereum.geth.Account;
import org.ethereum.geth.KeyStore;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.methods.response.EthAccounts;

import java.io.File;
import java.security.Provider;
import java.security.Security;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth ;
    private String walletPath;
    private File walletFile;
    private KeyStore keyStore;
    private Account account;
    private KeyStore keyStores;
    private Account accounts;

    TextView AccountTextView, PrivateTextView, PublicTextView;
    Web3j web3j;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupBouncyCastle();

        findViewById(R.id.moveLoginButton).setOnClickListener(onClickListener);
        findViewById(R.id.signupNextButton).setOnClickListener(onClickListener);
        findViewById(R.id.SMScerButton).setOnClickListener(onClickListener);

        String phoneNumber = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();
        String number = ((EditText)findViewById(R.id.editTextNumber)).getText().toString();


       // AccountTextView = (TextView) findViewById(R.id.AccountTextView);
       // PrivateTextView = (TextView) findViewById(R.id.AccountTextView);


        /*
        // 권한ID를 가져옵니다
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        // 권한이 열려있는지 확인
        if (permission == PackageManager.PERMISSION_DENIED || permission2 == PackageManager.PERMISSION_DENIED) {
            // 마쉬멜로우 이상버전부터 권한을 물어본다
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 권한 체크(READ_PHONE_STATE의 requestCode를 1000으로 세팅
                requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
            }

            return;
        }*/
    }
/*
    // 권한 체크 이후로직
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        // READ_PHONE_STATE의 권한 체크 결과를 불러온다
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        if (requestCode == 1000) {
            boolean check_result = true;
            // 모든 퍼미션을 허용했는지 체크
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            // 권한 체크에 동의를 하지 않으면 안드로이드 종료
            if (check_result == true) {
            } else {
                finish();
            }
        }
    }*/


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signupNextButton:
                    //signUp();
                    myStartActivity(SignUpActivity.class);
                    break;
                case R.id.moveLoginButton:
                    myStartActivity(LoginActivity.class);
                    break;
                case R.id.SMScerButton:
                    //sendSMS();
                    break;
                }
            }
    };

   /* private void sendSMS(){
        String phoneNumber = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }*/
/*
    private void createAccountDemo() throws Exception {
        Connect();
        createKeyStore(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +  "/keystore").getPath());
        accounts = createAccount("1234");
        Log.d("main", accounts.getURL());
        Log.d("main", "account address is " + accounts.getAddress().getHex());
    }

    private void createKeyStore(String keyStoreFolderPath) {
        keyStores = new KeyStore(keyStoreFolderPath, Geth.LightScryptN, Geth.LightScryptP);
    }

    private Account createAccount(String password) throws Exception {
        return keyStores.newAccount(password);
    }*/





    public void loadAccount()throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    EthAccounts ethAccounts = web3j.ethAccounts().sendAsync().get();
                    String accounts[] = ethAccounts.getAccounts().toArray(new String[0]);
                    PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(accounts[0], "admin").sendAsync().get();
                    Log.d(TAG,accounts[0]);
                }catch (Exception e){
                    Log.d(TAG, "LoadAccount Error!");
                }

            }
        }).start();
    }



    private void myStartActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

    private void setupBouncyCastle() {
        final Provider provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
        if (provider == null) {
            // Web3j will set up the provider lazily when it's first used.
            return;
        }
        if (provider.getClass().equals(BouncyCastleProvider.class)) {
            // BC with same package name, shouldn't happen in real life.
            return;
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }


}