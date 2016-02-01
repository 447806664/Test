package com.test.okhttp;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by 123 on 2016/2/1.
 */
public class AsyncGet {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("服务器端错误: " + response);
                }

                System.out.println(response.body().string());
            }
        });
    }
}
