package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.ColorService;
import com.turkcell.rentACar2.business.dtos.ColorGetDto;
import com.turkcell.rentACar2.business.dtos.ColorListDto;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperManager;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar2.entities.concretes.Color;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;
    private ModelMapperService modelMapperService;

    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
    }
    @Override
    public List<ColorListDto> getAll() {
        List<Color> result = this.colorDao.findAll();
        List<ColorListDto> response = result.stream().map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public void add(Color color) {
        if(this.colorDao.findByColorName(color.getColorName()) != null) {
            System.out.println("hata");
        } else {
            this.colorDao.save(color);
        }
    }

    @Override
    public ColorGetDto getById(int id) {
        Color color = this.colorDao.getById(id);
        ColorGetDto response = this.modelMapperService.forDto().map(color,ColorGetDto.class);
        return response;
    }
}
