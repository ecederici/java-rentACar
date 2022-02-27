package com.turkcell.rentACar2.business.concretes;

import com.turkcell.rentACar2.business.abstracts.ColorService;
import com.turkcell.rentACar2.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACar2.entities.concretes.Color;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

@Service
public class ColorManager implements ColorService {
    private ColorDao colorDao;

    public ColorManager(ColorDao colorDao) {
        this.colorDao = colorDao;
    }
    @Override
    public List<Color> getAll() {
        return this.colorDao.findAll();
    }

    @Override
    public void add(Color color) {
        if(this.colorDao.findByColorName(color.getColorName()) != null) {
            System.out.println("hata");
        } else {
            this.colorDao.save(color);
        }
    }
}
