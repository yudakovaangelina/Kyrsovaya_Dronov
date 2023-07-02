package com.mirea.kt.android.kyrsovaya_dronov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.mirea.kt.android.kyrsovaya_dronov.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    int result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnEnter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText inpupLog = findViewById(R.id.etLogin);
        EditText inputPswd = findViewById(R.id.etPassword);

        String login = inpupLog.getText().toString();
        String pswd = inputPswd.getText().toString();

        if (!login.isEmpty() && !pswd.isEmpty()) {

            CalcApp.getServerApi().getCalcInfoAll(login, pswd, "RIBO-02-21").enqueue(new Callback<CalcInfoResponse>() {
                @Override
                public void onResponse(Call<CalcInfoResponse> call, Response<CalcInfoResponse> response) {
                    CalcInfoResponse sir = response.body();

                    int result = sir.getResult();
                    if (result == 1) {
                        Intent actIntent = new Intent(getApplicationContext(), CalculatorActivity.class);
                        startActivity(actIntent);

                    } else if (result == -1) {

                        Toast.makeText(getApplicationContext(), "Неверный пароль или логин", Toast.LENGTH_LONG).show();
                        Log.i("simple_app_tag", "Неверный пароль или логин");


                    }
                }

                @Override
                public void onFailure(Call<CalcInfoResponse> call, Throwable t) {
                    Log.i("simple_app_tag", "error");
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "Необходимо заполнить оба поля", Toast.LENGTH_LONG).show();
        }
    }
}