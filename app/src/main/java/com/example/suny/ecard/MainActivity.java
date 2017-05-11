package com.example.suny.ecard;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.suny.ecard.adapter.RecyAdapter;
import com.example.suny.ecard.database.WeekData;
import com.example.suny.ecard.fragment.OneweekFragment;
import com.example.suny.ecard.utils.Constant;
import com.example.suny.ecard.utils.SendHttpHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity {

    private List listData;

    private String input;
    TextView tv;
    private List<WeekData> weekDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = Connector.getDatabase();




        final Intent intent = getIntent();
        String text = intent.getStringExtra("session");
        Log.i("cookie", text);

        SendHttpHelper.sendHttp(Constant.weekUrl, text, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                input = response.body().string();
//                Log.i("data2", input);

                listData = handleList(input);
                Log.i("input", String.valueOf(listData));

                for (int i = 0; i < listData.size(); i = i + 5) {
                    WeekData weekData = new WeekData();
                    weekData.setData((String) listData.get(i));
                    weekData.setCost((String) listData.get(i + 3));
                    weekData.setLocation((String) listData.get(i + 1));
                    weekData.save();

                }

            }
        });
        weekDatas = DataSupport.findAll(WeekData.class);

//        FragmentManager fm = getSupportFragmentManager();
//        OneweekFragment fragment = (OneweekFragment) fm.findFragmentById(R.id.fragment1);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyAdapter adapter = new RecyAdapter(weekDatas, this);
        rv.setAdapter(adapter);

    }

    public List handleList(String input) {
        Document document = Jsoup.parse(input);
        Elements elements = document.select("td.second");
        List<String> list = new ArrayList<>();
        for (Element ele : elements) {
            String s = ele.text().trim();
            list.add(s);
        }
        return list;

    }

}
