package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.ColorService;
import com.turkcell.rentACar2.business.constants.messages.BusinessMessages;
import com.turkcell.rentACar2.business.dtos.ColorDto.ColorGetDto;
import com.turkcell.rentACar2.business.dtos.ColorDto.ColorListDto;
import com.turkcell.rentACar2.business.requests.ColorRequest.CreateColorRequest;
import com.turkcell.rentACar2.business.requests.ColorRequest.UpdateColorRequest;
import com.turkcell.rentACar2.core.utilities.exception.BusinessException;
import com.turkcell.rentACar2.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import com.turkcell.rentACar2.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACar2.core.utilities.results.SuccessResult;
import com.turkcell.rentACar2.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar2.entities.concretes.Color;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {
    private final ColorDao colorDao;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<ColorListDto>> getAll() {
        List<Color> result = this.colorDao.findAll();

        List<ColorListDto> response = result.stream().map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(response, BusinessMessages.DATA_LISTED);
    }

    @Override
    public Result add(CreateColorRequest createColorRequest) {
       Color color = this.modelMapperService.forRequest().map(createColorRequest,Color.class);

       checkIfColorNameExist(color.getName());

       color.setName(createColorRequest.getName().toUpperCase(Locale.ROOT));
       this.colorDao.save(color);

       return new SuccessResult(BusinessMessages.DATA_ADDED);
    }

    @Override
    public DataResult<ColorGetDto> getById(int id) {
        checkIfColorIdExist(id);

        Color color = this.colorDao.getById(id);
        ColorGetDto response = this.modelMapperService.forDto().map(color,ColorGetDto.class);

        return new SuccessDataResult<>(response,BusinessMessages.DATA_BROUGHT + id);
    }

    @Override
    public Result update(int id, UpdateColorRequest updateColorRequest) {
        checkIfColorIdExist(id);

        Color color = this.colorDao.getById(id);

        checkIfColorNameExist(updateColorRequest.getName());

        color.setName(updateColorRequest.getName().toUpperCase(Locale.ROOT));

        this.colorDao.save(color);

        return new SuccessResult(BusinessMessages.DATA_UPDATED + id);
    }

    @Override
    public Result delete(int id) {
         this.checkIfColorIdExist(id);

         this.colorDao.deleteById(id);

         return new SuccessResult(BusinessMessages.DATA_DELETED + id);
    }

    public void checkIfColorIdExist(int id) {
        if (!this.colorDao.existsById(id)) {
            throw new BusinessException(BusinessMessages.COLOR_DOES_NOT_EXISTS);
        }
    }

    private void checkIfColorNameExist(String name) {
        if (this.colorDao.existsByName(name.toUpperCase(Locale.ROOT))) {
            throw new BusinessException(name + BusinessMessages.COLOR_ALREADY_EXISTS);
        }
    }
}
