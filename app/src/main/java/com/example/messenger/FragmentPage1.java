package com.example.messenger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FragmentPage1 extends Fragment {
    private static final String TAG = "Friends";
    private ArrayList<FriendItem> friends;
    private ListView listView;
    private SearchView searchview;
    private FriendAdapter friendAdapter;
    private ArrayList<FriendItem> frList;
    private TextView friendcnt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);


        friends = new ArrayList<>();
        try {
            //File file = new File(String.valueOf(Environment.getExternalStoragePublicDirectory("/Download" )));
            // File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download");
            File file = new File("/storage/emulated/0/Download");
            if(!file.exists()){ // 폴더 없을 경우
                file.mkdir(); // 폴더 생성
            }
            //입력 스트림 생성
           // FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(new FileReader(file +  "/friends.txt"));
            String line = "";
            String name ="", state = "";
            int slice = 0;
            while((line = bufReader.readLine()) != null){
                slice = line.indexOf("/");
                name = line.substring(0,slice);
                state = line.substring(slice+1);
                friends.add(new FriendItem(R.drawable.yeri,name,state));
            };
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            frList = new ArrayList<FriendItem>();
            frList.addAll(friends);
            bufReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*friends.add(new FriendItem(R.drawable.hyunjoo,"양현주","세상아 덤벼라"));
        friends.add(new FriendItem(R.drawable.daun,"김다운","아 배고프다"));
        friends.add(new FriendItem(R.drawable.yeri,"박예리","내 이름은 박예리"));
        friends.add(new FriendItem(R.drawable.nadong,"나동준","아는 척 하지마"));
        */
        //copy


        listView = (ListView)rootView.findViewById(R.id.FriendList);
        friendcnt = (TextView)rootView.findViewById(R.id.freindCount);

        friendAdapter = new FriendAdapter(getContext(),friends);
        String cnt = String.valueOf(friends.size());
        friendcnt.setText(cnt);
        listView.setAdapter(friendAdapter);


        return rootView;
    }


    @Override
    public void onResume(){
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.top_nav,menu);


        SearchView searchView = (SearchView)menu.findItem(R.id.search_friend).getActionView();


        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setQueryHint("친구 이름을 입력하세요.");
        MenuItem item_like = menu.add(0,0,0,"이름");




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }

        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.search_friend){
            return true;
        }else if(id == R.id.add_friend){
            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
            ad.setIcon(R.drawable.users_21941);
            ad.setTitle("친구 추가");
            ad.setMessage("아이디 입력");

            final EditText et = new EditText(getContext());
            ad.setView(et);

            ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 친구 아이디 입력 -> IPFS 연결 -> 파일 불러와서 저장

                    dialog.dismiss();
                }
            });


            ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            ad.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        friends.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            friends.addAll(frList);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < frList.size(); i++)
            {
                // frList 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (frList.get(i).getName().toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    friends.add(frList.get(i));
                }
            }

        }
        String cnt = String.valueOf(friends.size());
        friendcnt.setText(cnt);

        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        friendAdapter.notifyDataSetChanged();
    }


}