package com.turkcell.rentACar2.core.utilities.mapping;
import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forDto();
    ModelMapper forRequest();

}
