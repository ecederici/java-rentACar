package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.ColorService;
import com.turkcell.rentACar2.business.dtos.ColorDto.ColorGetDto;
import com.turkcell.rentACar2.business.dtos.ColorDto.ColorListDto;
import com.turkcell.rentACar2.business.requests.ColorRequest.CreateColorRequest;
import com.turkcell.rentACar2.business.requests.ColorRequest.UpdateColorRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
    private final ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }
    @GetMapping("/getAll")
    public DataResult<List<ColorListDto>> getAll() {
        return this.colorService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
       return this.colorService.add(createColorRequest);
   }

   @GetMapping("/getById/{id}")
   public DataResult<ColorGetDto> getById(@PathVariable int id) {
        return this.colorService.getById(id);
   }

   @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateColorRequest updateColorRequest) {
       return this.colorService.update(id, updateColorRequest);
   }

   @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
       return this.colorService.delete(id);
   }
}
