package com.ezlo.ezlotestapp.utils.mapper;

public interface ModelMapper <NetworkModel, ViewModel>{

    NetworkModel mapFromView(ViewModel viewModel);

    ViewModel mapToView(NetworkModel networkModel);
}
