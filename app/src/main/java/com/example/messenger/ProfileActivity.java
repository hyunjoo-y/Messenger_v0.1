package com.example.messenger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

public class ProfileActivity extends AppCompatActivity {
    String name = "", birthday = "", profileHash = "";
    ImageView imageView;
    EditText IdText;
    private int REQUEST_CODE = 1;
    Web3j web3j;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilie);
        findViewById(R.id.ProfileimageView).setOnClickListener(onClickListener);
        findViewById(R.id.ProfileRegisterButton).setOnClickListener(onClickListener);
        imageView = (ImageView) findViewById(R.id.ProfileimageView);
        IdText = (EditText) findViewById((R.id.IdEditText));

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ProfileRegisterButton:
                    try{
                        blockId();
                        //saveTextFile();
                        //uploadFile();
                    }catch (Exception e){
                        Log.d("Profile","ID ERROR!");
                    }


                    break;
                case R.id.ProfileimageView:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, REQUEST_CODE);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    saveBitmapToJpeg(img);
                    // 이미지 표시
                    imageView.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void blockId() throws Exception {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void run() {
                String name = IdText.getText().toString();

                web3j = Web3j.build(new HttpService("http://10.0.2.2:8501"));
                admin = Admin.build(new HttpService("http://10.0.2.2:8501"));

                // profileContract profileContract = new profileContract();
                String account = "0xef46e8cafe1b76c92c13824cea5e2e7b1301284e";
                String address = "0x8279165e9272a94942978C29634CE17b4026e3cd";
                String privatekey ="64f7e5794aa5b986182130e59c38ba7c93580c47d912fcf71f546ab339d2ead4";
                BigInteger gasLimit= BigInteger.valueOf(0x48943e);
                BigInteger gasPrice = BigInteger.valueOf(4300000);
                BigInteger one = BigInteger.valueOf(1);

                Credentials credentials = Credentials.create(privatekey);
                // byte[] hash = profileHash.getBytes();
                try {

                    ClientTransactionManager transactionManager = new ClientTransactionManager(web3j, account);

                    StoreId_sol_StoreId id = StoreId_sol_StoreId.load(address, admin,transactionManager,gasPrice,gasLimit);
                    TransactionReceipt transactionReceipt = id._adduser(name).send();
                    String b = transactionReceipt.getBlockNumber().toString();
                    //StoreId_sol_StoreId ids = StoreId_sol_StoreId.load()
                    Log.d("Profile",b);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                //StoreId_sol_StoreId id = StoreId_sol_StoreId.deploy(web3j,);
                //CompletableFuture<TransactionReceipt> transactionReceipt = id._adduser(name).sendAsync();
                //String result = "Successful transaction. Gas used: ${transactionReceipt?.get()?.blockNumber}  ${transactionReceipt?.get()?.gasUsed}";
                //Log.d("Profile", result);
                // RemoteFunctionCall<TransactionReceipt> transactionReceipt = Hash.sendHash(one, hash);
                /*String result = null;
                try {
                    result = "Successful transaction. Transaction hashCode: " + transactionReceipt.get().getBlockNumber();
                    Log.d("Profile",result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/






                //CompletableFuture<TransactionReceipt> transactionReceipt = Hash.sendHash(one, hash).sendAsync();
                //String result = "Successful transaction. Gas used: ${transactionReceipt?.get()?.blockNumber}  ${transactionReceipt?.get()?.gasUsed}";
                //Log.d("Profile", result);
                // RemoteFunctionCall<TransactionReceipt> transactionReceipt = Hash.sendHash(one, hash);
                // String result = "Successful transaction. Transaction hashCode: " + transactionReceipt.hashCode();
                //Log.d("Profile",result);
            }}).start();

    }
    public void saveBitmapToJpeg(Bitmap bitmap) {   // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getFilesDir(), "Profile.png");    // 파일 경로와 이름 넣기
        try {
            tempFile.createNewFile();   // 자동으로 빈 파일을 생성하기
            FileOutputStream out = new FileOutputStream(tempFile);  // 파일을 쓸 수 있는 스트림을 준비하기
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);   // compress 함수를 사용해 스트림에 비트맵을 저장하기
            out.close();    // 스트림 닫아주기
            Toast.makeText(getApplicationContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
        }
    }



    private void saveTextFile(){
        name = ((EditText) findViewById(R.id.IdEditText)).getText().toString();
        birthday = ((EditText) findViewById(R.id.editTextDate)).getText().toString();
        String path = getFilesDir().toString();
        //File dir = new File(path);
        Log.d("profile", path);
       //if(!dir.exists())
         //   dir.mkdirs();
        //File file = new File(dir,filename);
        try{
            String contents = name + "\n" + birthday;
            BufferedWriter bw = new BufferedWriter(new FileWriter(path + "profile.txt",false));
            bw.write(contents);
            bw.newLine();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Log.d("Profile","success!");

    }



    private void uploadFile(){
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run(){
        try {
            IPFS ipfs = new IPFS("/dnsaddr/ipfs.infura.io/tcp/5001/https");
            System.out.println("connected");
            NamedStreamable.FileWrapper file1 = new NamedStreamable.FileWrapper( new File( "/data/data/com.example.messenger/files/Profile.png" ));
            NamedStreamable.FileWrapper file2 = new NamedStreamable.FileWrapper( new File( "/data/data/com.example.messenger/filesprofile.txt" ));

            NamedStreamable.DirWrapper dir = new NamedStreamable.DirWrapper("Profile", Arrays.asList(file1, file2));
            List<MerkleNode> response = ipfs.add(dir);
            response.forEach(merkleNode ->
                            profileHash = merkleNode.hash.toBase58());
                    //System.out.println( "Hase(base 58): " + merkleNode.name.get() + " - " + merkleNode.hash.toBase58()));
            System.out.println( "Hase(base 58): " + profileHash);
            registerHash();
           // MerkleNode response = ipfs.add(file).get(0);
         //   System.out.println("Hash (base 58): " + response.hash.toBase58());
        } catch (IOException ex) {
            throw new RuntimeException("Error whilst communicating with the IPFS node", ex);
        }catch (NetworkOnMainThreadException ex){
            Log.d("Profile","ERROR!");
        } catch (Exception e) {
            e.printStackTrace();
        }
            }
    }).start();
    }


    private void registerHash() throws Exception {
        new Thread(new Runnable() {
            public void run() {

                web3j = Web3j.build(new HttpService("http://10.0.2.2:8501"));
                admin = Admin.build(new HttpService("http://10.0.2.2:8501"));

                // profileContract profileContract = new profileContract();
                String account = "0xef46e8cafe1b76c92c13824cea5e2e7b1301284e";
                String address = "0x8279165e9272a94942978C29634CE17b4026e3cd";

                BigInteger gasLimit= BigInteger.valueOf(20_000_000_000L);
                BigInteger gasPrice = BigInteger.valueOf(4300000);
                BigInteger one = BigInteger.valueOf(1);
                byte[] hash = profileHash.getBytes();
                StoreHash_sol_StoreHash Hash = StoreHash_sol_StoreHash.load(address,web3j, Credentials.create(account), gasPrice, gasLimit);
                CompletableFuture<TransactionReceipt> transactionReceipt = Hash.sendHash(one, hash).sendAsync();
                //String result = "Successful transaction. Gas used: ${transactionReceipt?.get()?.blockNumber}  ${transactionReceipt?.get()?.gasUsed}";
                //Log.d("Profile", result);
               // RemoteFunctionCall<TransactionReceipt> transactionReceipt = Hash.sendHash(one, hash);
                String result = "Successful transaction. Transaction hashCode: " + transactionReceipt.hashCode();
                Log.d("Profile",result);
            }}).start();

    }

}

