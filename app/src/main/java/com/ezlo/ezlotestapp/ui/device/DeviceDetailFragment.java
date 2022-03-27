package com.ezlo.ezlotestapp.ui.device;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.ezlo.ezlotestapp.R;
import com.ezlo.ezlotestapp.TestApplication;
import com.ezlo.ezlotestapp.data.model.view.Device;
import com.ezlo.ezlotestapp.databinding.DeviceDetailFragmentBinding;
import com.ezlo.ezlotestapp.di.module.viewmodel.ViewModelFactory;
import com.ezlo.ezlotestapp.utils.GlobalEnums;

import javax.inject.Inject;

public class DeviceDetailFragment extends Fragment {

    @Inject
    public ViewModelFactory viewModelFactory;

    private DeviceDetailFragmentBinding binding;
    private NavController navController;

    private DeviceViewModel deviceViewModel;

    public DeviceDetailFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        deviceViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(DeviceViewModel.class);
        deviceViewModel.getLiveDataSelectedDevice().observe(getViewLifecycleOwner(), this::bindDevice);
        deviceViewModel.getLiveDataEdit().observe(getViewLifecycleOwner(), this::bindEdit);
        deviceViewModel.getLiveDataActionClick().observe(getViewLifecycleOwner(), this::bindActions);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.device_detail_fragment, container, false);
        return binding.getRoot();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((TestApplication) requireActivity().getApplicationContext()).appComponent.inject(this);
        super.onAttach(context);
    }

    private void bindDevice(Device device) {
        binding.setDevice(device);
    }

    private void bindEdit(Boolean editable) {
        if(editable != null && editable){
            ((InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
            );
        }
        binding.setEditable(editable != null && editable);
    }

    private void bindActions(GlobalEnums.ActionClick actionClick){
        if(actionClick == GlobalEnums.ActionClick.SAVE){
            binding.getDevice().setName(binding.editTextDDFName.getText().toString());
            deviceViewModel.setEditable(false);
            navController.navigate(R.id.action_deviceDetailFragment_to_deviceListFragment);
        }else if(actionClick == GlobalEnums.ActionClick.CANCEL){
            navController.navigate(R.id.action_deviceDetailFragment_to_deviceListFragment);
            deviceViewModel.setActionClick(GlobalEnums.ActionClick.INACTIVE);
            deviceViewModel.setEditable(false);
        }
    }
}