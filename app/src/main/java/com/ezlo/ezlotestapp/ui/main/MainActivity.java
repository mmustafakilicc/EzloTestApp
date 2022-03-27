package com.ezlo.ezlotestapp.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ezlo.ezlotestapp.R;
import com.ezlo.ezlotestapp.TestApplication;
import com.ezlo.ezlotestapp.data.model.view.User;
import com.ezlo.ezlotestapp.databinding.ActivityMainBinding;
import com.ezlo.ezlotestapp.di.module.viewmodel.ViewModelFactory;
import com.ezlo.ezlotestapp.ui.device.DeviceViewModel;
import com.ezlo.ezlotestapp.utils.GlobalEnums;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Inject
    public ViewModelFactory viewModelFactory;

    private Menu appMenu;
    private DeviceViewModel deviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((TestApplication) getApplicationContext()).appComponent.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initialize();
    }

    //action is used for saving name changes
    private void initialize() {
        deviceViewModel = new ViewModelProvider(this, viewModelFactory).get(DeviceViewModel.class);
        deviceViewModel.getLiveDataEdit().observe(this, this::controlEditMode);
        User user = new User();
        user.setName("Mustafa Kılıç");
        binding.setUser(user);
        setSupportActionBar(binding.materialToolbarMain);
    }

    private void controlEditMode(Boolean isEditable) {
        if (isEditable != null && isEditable) {
            appMenu.findItem(R.id.actionSave).setVisible(true);
            appMenu.findItem(R.id.actionCancel).setVisible(true);
        }else{
            appMenu.findItem(R.id.actionSave).setVisible(false);
            appMenu.findItem(R.id.actionCancel).setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        appMenu = menu;
        MenuItem itemSave = menu.findItem(R.id.actionSave);
        MenuItem itemCancel = menu.findItem(R.id.actionCancel);
        if (itemSave != null) {
            itemSave.setVisible(false);
        }
        if (itemCancel != null) {
            itemCancel.setVisible(false);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionSave:
                deviceViewModel.setActionClick(GlobalEnums.ActionClick.SAVE);//send data to device detail fragment
                return true;
            case R.id.actionCancel:
                deviceViewModel.setActionClick(GlobalEnums.ActionClick.CANCEL);//send data to device detail fragment
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //if back button is used when device detail fragment is visible, set all data to the default values
    @Override
    public void onBackPressed() {
        deviceViewModel.setEditable(false);
        deviceViewModel.setActionClick(GlobalEnums.ActionClick.INACTIVE);
        super.onBackPressed();
    }
}