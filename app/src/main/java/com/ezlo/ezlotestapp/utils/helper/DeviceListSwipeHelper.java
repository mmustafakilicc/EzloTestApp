package com.ezlo.ezlotestapp.utils.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ezlo.ezlotestapp.ui.device.adapter.DeviceListAdapter;

public class DeviceListSwipeHelper extends ItemTouchHelper.SimpleCallback {

    private final DeviceListAdapter deviceListAdapter;
    public DeviceListSwipeHelper(DeviceListAdapter deviceListAdapter, int dragDirs, int swipeDirs){
        super(dragDirs, swipeDirs);
        this.deviceListAdapter = deviceListAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
