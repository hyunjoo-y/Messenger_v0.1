package com.example.messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextTextPassword, editTextNumberPassword4;
    Web3j web3j;
    Admin admin;
    private int check = 0;
    String TAG = "SignActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        findViewById(R.id.SignUpButton).setOnClickListener(onClickListener);

       // findViewById(R.id.LoginButton).setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.SignUpButton:
                    int sck = signUp();
                    if(sck == 1){
                        myStartActivity(ProfileActivity.class);
                    }else{
                        startToast("다시 시도하세요.");
                    }


            }
        }
    };

    private int signUp() {
        try {
            Connect();
            //loadAccount();
            //createAccount();
            check = 1;
        } catch (Exception e) {
            System.out.println("Sign Up ERROR!");
        }

        return check;
    }


    public void Connect() throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run(){
                try {
                    // StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    // StrictMode.setThreadPolicy(policy);

                    web3j = Web3j.build(new HttpService("http://10.0.2.2:8501"));
                    admin = Admin.build(new HttpService("http://10.0.2.2:8501"));

                    Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
                    System.out.println(web3ClientVersion.getWeb3ClientVersion());

                    // 계좌 불러오기
                    EthAccounts ethAccounts = web3j.ethAccounts().sendAsync().get();
                    String accounts[] = ethAccounts.getAccounts().toArray(new String[0]);

                    Log.d(TAG,"Accounts: " + ethAccounts.getAccounts().toString());
                    // unlock
                    PersonalUnlockAccount personalUnlockAccount = admin.personalUnlockAccount(accounts[0],"admin").sendAsync().get();
                }catch (Exception e){
                    Log.d(TAG, "Connect ERROR!");
                }
            }
        }).start();
    }
    /*private void createAccountDemo() throws Exception {
        createKeyStore(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +  "/ETH/privatechain/keystore").getPath());
        account = createaccount("a1234567");
        Log.d("main", account.getURL());
        Log.d("main", "account address is " + account.getAddress().getHex());
    }

    private void createKeyStore(String keyStoreFolderPath) {
        keyStore = new KeyStore(keyStoreFolderPath, Geth.LightScryptN, Geth.LightScryptP);
    }

    private Account createaccount(String password) throws Exception {
        return keyStore.newAccount(password);
    }*/

    private void createAccount() throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run()   {
                String pri = "", pub = "", address = "";
                String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
                try {
                    String walletPath = Environment.getExternalStorageDirectory() + "/Download/keystore";
                    File file = new File(walletPath);
                    if(!file.exists())
                        file.mkdir();
                    String wallet = WalletUtils.generateNewWalletFile(password, file, true);
                    Credentials credentials = WalletUtils.loadCredentials(password,walletPath +"/"+ wallet);
                   // Credentials credentials = WalletUtils.loadCredentials(password,walletPath +"/"+ wallet);
                    ECKeyPair keyPair = credentials.getEcKeyPair();
                    pri = keyPair.getPrivateKey().toString();
                    pub = keyPair.getPublicKey().toString();
                    address = credentials.getAddress();

                    NewAccountIdentifier newAccountIdentifier = admin.personalNewAccount(password).send();
                    Log.d(TAG,newAccountIdentifier.getResult());

                    Log.d(TAG,"pri " + pri + "\n"+"pub " + pub + "\n"+"address " + address + "\n");
                    Log.d(TAG, "Balance: " + Convert.fromWei(web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));

                    PersonalListAccounts personalListAccounts = admin.personalListAccounts().send();
                    List<String> addressList = personalListAccounts.getAccountIds();
                    Log.d(TAG,addressList.get(0));
                    /*
                    String address = newAccountIdentifier.getAccountId();
                    Credentials credentials = Credentials.create(address);
                        ECKeyPair keyPair = credentials.getEcKeyPair();
                        BigInteger privateKey = keyPair.getPrivateKey();
                        BigInteger publicKey = keyPair.getPublicKey();
                        System.out.println("new account address " + address);
                    //AccountTextView.setText("Account: " + address);
                    //PrivateTextView.setText("Private Key : " + credentials.getEcKeyPair().getPrivateKey().toString(16));
                    //PublicTextView.setText("Public Key: " + credentials.getEcKeyPair().getPublicKey().toString(16));
*/
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (NullPointerException e){
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (CipherException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                }
            }
                /*
                try {
                    String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();

                    //권한 체크
                    int REQUEST_EXTERNAL_STORAGE = 1;
                    String[] PERMISSIONS_STORAGE = {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    };
                    int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (permission != PackageManager.PERMISSION_GRANTED) {
                        // We don't have permission so prompt the user
                        ActivityCompat.requestPermissions(
                                MainActivity.this,
                                PERMISSIONS_STORAGE,
                                REQUEST_EXTERNAL_STORAGE
                        );
                    }
                    File rootPath = new File(Environment.getExternalStorageDirectory(), "Download/myfolder");
                     if (!rootPath.exists())
                            rootPath.mkdirs();
                    walletPath = Environment.getExternalStorageDirectory() + "/Download";
                   // walletPath = Environment.getStorageDirectory() + "/DOWNLOAD";
                    // String filename = WalletUtils.generateNewWalletFile(password, new File(walletPath), false);
                   // File file = new File("C:/docker-ethereum-master/nodes/node1/keystore");

                    String filename = WalletUtils.generateNewWalletFile(password, new File(walletPath), false);
                    Credentials credentials = WalletUtils.loadCredentials(password,  walletPath + "/" + filename);
                    Log.d(TAG,walletPath);

                    AccountTextView.setText("Account : " + credentials.getAddress());
                   Log.d(TAG,"Private Key: " + credentials.getEcKeyPair().getPrivateKey().toString(16));
                    Log.d(TAG, "SUCCESS!");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (CipherException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (NoSuchProviderException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                }*/

        }).start();
    }
    private void myStartActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
