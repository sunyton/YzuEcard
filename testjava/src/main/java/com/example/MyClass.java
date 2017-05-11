package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyClass {


    //    private String url = "http://api.xzxyun.com/";
//
//    public void testCookie() throws IOException {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder().url("http://api.xzxyun.com/")
//                .get()
//                .addHeader("upgrade-insecure-requests", "1")
//                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
//                .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//                .addHeader("accept-encoding", "gzip, deflate, sdch")
//                .addHeader("accept-language", "en,zh;q=0.8,zh-CN;q=0.6,en-US;q=0.4,zh-TW;q=0.2")
//                .build();
//        Response response = okHttpClient.newCall(request).execute();
//
//
//        if (response.isSuccessful()) {
//            System.out.print(response.header("Set-Cookie").split(";")[0]);
//
//
//        }
//
//
//    }
//
//    public void testCookie2() {
//
//        final Map<HttpUrl, List<Cookie>> cokie = new HashMap<>();
//
//
//    }
    private String mString;


    public void out() {
        System.out.print(mString);
    }

    public void addstring() {
        mString = "sssss";

    }


    public static void main(String[] args) throws IOException {
//        MyClass myClass = new MyClass();
//        myClass.testCookie();
//        Ine ine =  myClass.new Ine();
        MyClass myClass = new MyClass();
        myClass.addstring();
        myClass.out();


    }

//    public class Ine {
//        public void pr() {
//            System.out.print("url");
//
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        File input = new File("D:\\WorkSpace\\Android\\Ecard\\testjava\\src\\main\\java\\com\\example\\sss.html");
//        Document document = Jsoup.parse(input, "UTF-8", "");
//        Elements elements = document.select("td.second");
//        List<String> list = new ArrayList<>();
//        for (Element ele : elements) {
//            String s = ele.text();
//            list.add(s);
//
//        }
//        System.out.println(list.toString());
//
//    }
}
