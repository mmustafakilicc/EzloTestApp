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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.ezlo.ezlotestapp.R;
import com.ezlo.ezlotestapp.TestApplication;
import com.ezlo.ezlotestapp.data.model.view.Device;
import com.ezlo.ezlotestapp.databinding.DeviceDetailFragmentBinding;
import com.ezlo.ezlotestapp.databinding.DeviceListFragmentBinding;
import com.ezlo.ezlotestapp.di.module.viewmodel.ViewModelFactory;

import javax.inject.Inject;

public class DeviceDetailFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    private DeviceDetailFragmentBinding binding;

    public DeviceDetailFragment() {
        super(R.layout.device_detail_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DeviceViewModel deviceViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(DeviceViewModel.class);
        deviceViewModel.getLiveDataSelectedDevice().observe(getViewLifecycleOwner(), this::bindDevice);
        deviceViewModel.getLiveDataEdit().observe(getViewLifecycleOwner(), this::bindEdit);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.device_detail_fragment, container, false);
        return binding.getRoot();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((TestApplication)requireActivity().getApplicationContext()).appComponent.inject(this);
        super.onAttach(context);
    }

    private void bindDevice(Device device){
        binding.setDevice(device);
    }

    private void bindEdit(Boolean editable){
        binding.setEditable(editable != null && editable);
    }
}