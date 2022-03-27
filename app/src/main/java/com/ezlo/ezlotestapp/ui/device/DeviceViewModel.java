package com.ezlo.ezlotestapp.ui.device;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ezlo.ezlotestapp.data.model.api.DeviceListResponse;
import com.ezlo.ezlotestapp.data.model.view.Device;
import com.ezlo.ezlotestapp.data.remote.repository.DeviceRepository;
import com.ezlo.ezlotestapp.utils.GlobalEnums;
import com.ezlo.ezlotestapp.utils.mapper.DeviceMapper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DeviceViewModel extends ViewModel {

    private final DeviceRepository deviceRepository;

    @Inject
    public DeviceViewModel(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    private MutableLiveData<List<Device>> liveDataDeviceList;
    public MutableLiveData<List<Device>> getLiveDataDeviceList() {
        if (liveDataDeviceList == null) {
            liveDataDeviceList = new MutableLiveData<>();
            loadDeviceList();
        }
        return liveDataDeviceList;
    }

    private MutableLiveData<Device> liveDataSelectedDevice;
    public MutableLiveData<Device> getLiveDataSelectedDevice() {
        if (liveDataSelectedDevice == null) {
            liveDataSelectedDevice = new MutableLiveData<>();
        }
        return liveDataSelectedDevice;
    }

    public void setSelectedDevice(Device device) {
        getLiveDataSelectedDevice().setValue(device);
    }

    private MutableLiveData<Boolean> liveDataEdit;
    public MutableLiveData<Boolean> getLiveDataEdit() {
        if (liveDataEdit == null) {
            liveDataEdit = new MutableLiveData<>();
        }
        return liveDataEdit;
    }

    public void setEditable(boolean isEditable){
        getLiveDataEdit().setValue(isEditable);
    }

    private MutableLiveData<Boolean> liveDataIsLoading;
    public MutableLiveData<Boolean> getLiveDataIsLoading() {
        if (liveDataIsLoading == null) {
            liveDataIsLoading = new MutableLiveData<>();
        }
        return liveDataIsLoading;
    }

    private MutableLiveData<GlobalEnums.ActionClick> liveDataActionClick;
    public MutableLiveData<GlobalEnums.ActionClick> getLiveDataActionClick(){
        if(liveDataActionClick == null){
            liveDataActionClick = new MutableLiveData<>();
        }
        return liveDataActionClick;
    }
    public void setActionClick(GlobalEnums.ActionClick actionClick){
        getLiveDataActionClick().setValue(actionClick);
    }

    private void loadDeviceList() {
        getLiveDataIsLoading().setValue(true);
        deviceRepository.getDeviceList()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeviceListResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull DeviceListResponse response) {
                        DeviceMapper deviceMapper = new DeviceMapper();
                        getLiveDataDeviceList().postValue(deviceMapper.mapToViewList(response.getDevices()));
                        getLiveDataIsLoading().postValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("wqeqwe", "qweqweqw");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
