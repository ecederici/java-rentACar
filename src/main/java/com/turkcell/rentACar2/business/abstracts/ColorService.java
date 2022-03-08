package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.business.dtos.ColorGetDto;
import com.turkcell.rentACar2.business.dtos.ColorListDto;
import com.turkcell.rentACar2.entities.concretes.Color;

import java.util.List;

public interface ColorService {
     List<ColorListDto> getAll();
     void add(Color color);
     ColorGetDto getById(int id);
}
