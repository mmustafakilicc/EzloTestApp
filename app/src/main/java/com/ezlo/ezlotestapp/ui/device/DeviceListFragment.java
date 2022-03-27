package com.ezlo.ezlotestapp.ui.device;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.ezlo.ezlotestapp.R;
import com.ezlo.ezlotestapp.TestApplication;
import com.ezlo.ezlotestapp.data.model.view.Device;
import com.ezlo.ezlotestapp.databinding.DeviceListFragmentBinding;
import com.ezlo.ezlotestapp.di.module.viewmodel.ViewModelFactory;
import com.ezlo.ezlotestapp.ui.device.adapter.DeviceListAdapter;
import com.ezlo.ezlotestapp.utils.helper.DeviceListSwipeHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class DeviceListFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    private DeviceListFragmentBinding binding;
    private DeviceViewModel deviceViewModel;
    private NavController navController;
    private DeviceListAdapter adapter;

    public DeviceListFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.device_list_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        deviceViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(DeviceViewModel.class);
        deviceViewModel.getLiveDataIsLoading().observe(getViewLifecycleOwner(), this::bindLoading);
        deviceViewModel.getLiveDataDeviceList().observe(getViewLifecycleOwner(), this::showDeviceList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((TestApplication) requireActivity().getApplicationContext()).appComponent.inject(this);
        super.onAttach(context);
    }

    private void showDeviceList(List<Device> devices) {

        Collections.sort(devices);
        adapter = new DeviceListAdapter(devices);
        adapter.addDeviceClickListener(device -> showDeviceDetail(device, false));
        adapter.addDeviceLongClickListener(this::showDeleteWarning);
        adapter.addEditListener(device -> showDeviceDetail(device, true));

        binding.recyclerViewDLF.setHasFixedSize(true);
        binding.recyclerViewDLF.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerViewDLF.setAdapter(adapter);

        DeviceListSwipeHelper deviceListSwipeHelper = new DeviceListSwipeHelper(adapter, 0, ItemTouchHelper.LEFT);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(deviceListSwipeHelper);
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewDLF);
    }

    private void showDeviceDetail(Device device, boolean isEditable) {
        deviceViewModel.setSelectedDevice(device);
        deviceViewModel.setEditable(isEditable);
        navController.navigate(R.id.action_deviceListFragment_to_deviceDetailFragment);
    }

    private void bindLoading(Boolean isLoading) {
        binding.setIsLoading(isLoading != null && isLoading);
    }

    private void showDeleteWarning(Device device, int position) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity());
        builder.setPositiveButton(R.string.ok, (dialogInterface, i) -> {
            deleteDevice(position);
            dialogInterface.dismiss();
        });
        builder.setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setMessage(getString(R.string.delete_device_q, device.getName()));
        builder.setCancelable(false);
        builder.create().show();
    }

    private void deleteDevice(int position) {
        adapter.deleteItem(position);
    }

}