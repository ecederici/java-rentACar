package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.ColorService;
import com.turkcell.rentACar2.business.dtos.ColorGetDto;
import com.turkcell.rentACar2.business.dtos.ColorListDto;
import com.turkcell.rentACar2.entities.concretes.Color;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
    private ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }
    @GetMapping("/getAll")
    public List<ColorListDto> getAll() {
        return this.colorService.getAll();
    }

    @PostMapping("/add")
    public void add(Color color) {
        this.colorService.add(color);
   }

   @GetMapping("/getById")
   public ColorGetDto getById(int id) {
        return this.colorService.getById(id);
   }
}
