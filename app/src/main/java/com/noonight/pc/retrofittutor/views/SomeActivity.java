package com.noonight.pc.retrofittutor.views;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.noonight.pc.retrofittutor.R;
import com.noonight.pc.retrofittutor.database.managers.NumberStringManager;
import com.noonight.pc.retrofittutor.database.tables.NumberStringTable;
import com.noonight.pc.retrofittutor.network.retrofit2.API;
import com.noonight.pc.retrofittutor.network.retrofit2.ServiceGenerator;
import com.noonight.pc.retrofittutor.network.retrofit2.models.NumberString;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SomeActivity extends AppCompatActivity {

    private TextView tvOut;

    private NumberStringManager numberStringManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some);
        init();
    }

    void init() {
        tvOut = (TextView) findViewById(R.id.tvOut);

        numberStringManager = new NumberStringManager(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String str = preferences.getString("main_address_key", "");
        Toast.makeText(this, "from: " + str, Toast.LENGTH_SHORT).show();

        ServiceGenerator.changeApiBaseUrl(str);

        getSomeData();

    }

    private void getSomeData() {
        Callback<List<NumberString>> callBack = new Callback<List<NumberString>>() {
            @Override
            public void onResponse(Call<List<NumberString>> call, Response<List<NumberString>> response) {
                List<NumberString> someData = response.body();
                String details = "";
                for (int i = 0; i < someData.size(); i++) {
                    int number = someData.get(i).getNumber();
                    String text = someData.get(i).getText();
                    numberStringManager.insert(number, text);
                    details +=
                            "Number: " + number + "\n" +
                                    "Text: " + text + "\n\n";
                }
                tvOut.setText(details);
            }

            @Override
            public void onFailure(Call<List<NumberString>> call, Throwable t) {

            }
        };


        Call<List<NumberString>> call = ServiceGenerator.createService(API.class).getNumbersStrings();
        call.enqueue(callBack);
    }
}
