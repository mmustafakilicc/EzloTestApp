package com.ezlo.ezlotestapp.ui.device;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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

    public DeviceDetailFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DeviceViewModel deviceViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(DeviceViewModel.class);
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
        if (editable != null && editable) {//edit mode is active . force to open keyboard
            ((InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
            );
        }
        binding.setEditable(editable != null && editable);
    }

    private void bindActions(GlobalEnums.ActionClick actionClick) {
        if (actionClick != GlobalEnums.ActionClick.INACTIVE) {//save or cancel clicked?
            if (actionClick == GlobalEnums.ActionClick.SAVE) {
                if (binding.editTextDDFName.getText().length() <= 0) {
                    Toast.makeText(requireContext(), getString(R.string.device_length_warning), Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.getDevice().setName(binding.editTextDDFName.getText().toString());
            }
            requireActivity().onBackPressed();
        }
    }
}