package com.turkcell.rentACar2.business.abstracts;

import com.turkcell.rentACar2.entities.concretes.Color;

import java.util.List;

public interface ColorService {
     List<Color> getAll();
     void add(Color color);
}
