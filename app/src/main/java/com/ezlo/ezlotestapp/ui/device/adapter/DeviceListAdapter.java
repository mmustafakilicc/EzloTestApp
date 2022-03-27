package com.ezlo.ezlotestapp.ui.device.adapter;

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

import java.util.List;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {

    private final List<Device> deviceList;
    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public DeviceListAdapter(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDeviceListBinding binding = ItemDeviceListBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new DeviceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        holder.bind(deviceList.get(position), itemClickListener, itemLongClickListener);
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
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

    static class DeviceViewHolder extends RecyclerView.ViewHolder {
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

    public void deleteItem(int position){
        deviceList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, deviceList.size());
    }

    @BindingAdapter("android:src")
    public static void setImageSource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}
