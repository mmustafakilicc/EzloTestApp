package com.ezlo.ezlotestapp.ui.device.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.ezlo.ezlotestapp.data.model.api.DeviceNetworkEntity;
import com.ezlo.ezlotestapp.data.model.view.Device;
import com.ezlo.ezlotestapp.databinding.ItemDeviceListBinding;
import com.ezlo.ezlotestapp.databinding.ItemSwipeMenuBinding;

import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //for swipe view type
    private enum DeviceViewType{
        VIEW(1),
        EDIT(2);

        private final int value;
        DeviceViewType(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    private int swipeVisiblePosition;
    private final List<Device> deviceList;
    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;
    private EditButtonListener editButtonListener;

    public DeviceListAdapter(List<Device> deviceList) {
        this.deviceList = deviceList;
        swipeVisiblePosition = -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == DeviceViewType.EDIT.getValue()){
            ItemSwipeMenuBinding binding = ItemSwipeMenuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SwipeOptionsViewHolder(binding);
        }
        ItemDeviceListBinding binding = ItemDeviceListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DeviceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof SwipeOptionsViewHolder){
            ((SwipeOptionsViewHolder)holder).bind(deviceList.get(position), editButtonListener);
        }else {
            ((DeviceViewHolder)holder).bind(deviceList.get(position), itemClickListener, itemLongClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(deviceList.get(position).isSwipeVisible()){
            return DeviceViewType.EDIT.getValue();
        }
        return DeviceViewType.VIEW.getValue();
    }

    //region item controls
    //to show devices
    public static class DeviceViewHolder extends RecyclerView.ViewHolder {
        private final ItemDeviceListBinding binding;

        public DeviceViewHolder(@NonNull ItemDeviceListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Device device, ItemClickListener itemClickListener, ItemLongClickListener itemLongClickListener) {
            binding.setDevice(device);
            binding.executePendingBindings();
            if(itemClickListener != null){
                binding.getRoot().setOnClickListener(view -> itemClickListener.onClick(device));
            }
            if(itemLongClickListener != null){
                binding.getRoot().setOnLongClickListener(view -> {
                    itemLongClickListener.onLongClick(device, getAdapterPosition());
                    return true;
                });
            }
        }
    }

    public interface ItemClickListener{
        void onClick(Device device);
    }

    public interface ItemLongClickListener{
        void onLongClick(Device device, int position);
    }

    public void addDeviceClickListener(ItemClickListener itemClickListener){
        this.itemClickListener  = itemClickListener;
    }

    public void addDeviceLongClickListener(ItemLongClickListener itemLongClickListener){
        this.itemLongClickListener = itemLongClickListener;
    }

    public void deleteItem(int position){
        deviceList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, deviceList.size());
    }

    @BindingAdapter("android:src")
    public static void setImageSource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
    //endregion

    //region swipe controls
    public interface EditButtonListener{
        void onClick(Device device);
    }
    public void addEditListener(EditButtonListener editButtonListener){
        this.editButtonListener = editButtonListener;
    }

    //to show edit icon
    public static class SwipeOptionsViewHolder extends RecyclerView.ViewHolder{
        private final ItemSwipeMenuBinding binding;
        public SwipeOptionsViewHolder(@NonNull ItemSwipeMenuBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Device device, EditButtonListener editButtonListener){
            binding.textViewSMIEdit.setOnClickListener(view -> {
                editButtonListener.onClick(device);
                device.setSwipeVisible(false);
            });
        }
    }

    public void showOptions(int position){
        if(swipeVisiblePosition > -1){
            deviceList.get(swipeVisiblePosition).setSwipeVisible(false);
            notifyItemChanged(swipeVisiblePosition);
        }
        deviceList.get(position).setSwipeVisible(true);
        notifyItemChanged(position);
        swipeVisiblePosition = position;
    }

    public void hideOptions(){
        if(swipeVisiblePosition != -1){
            deviceList.get(swipeVisiblePosition).setSwipeVisible(false);
            notifyItemChanged(swipeVisiblePosition);
            swipeVisiblePosition = -1;
        }
    }

    public boolean isOptionsShown(){
        return swipeVisiblePosition != -1;
    }
    //endregion
}
