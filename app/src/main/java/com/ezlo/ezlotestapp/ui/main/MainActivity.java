package com.ezlo.ezlotestapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ezlo.ezlotestapp.R;
import com.ezlo.ezlotestapp.data.model.view.User;
import com.ezlo.ezlotestapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        User user = new User();
        user.setName("Mustafa Kılıç");
        binding.setUser(user);
    }
}