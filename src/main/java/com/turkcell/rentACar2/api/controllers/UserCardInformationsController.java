package com.turkcell.rentACar2.api.controllers;

import com.turkcell.rentACar2.business.abstracts.UserCardInformationService;
import com.turkcell.rentACar2.business.dtos.UserCardInformationDto.UserCardInformationGetDto;
import com.turkcell.rentACar2.business.dtos.UserCardInformationDto.UserCardInformationListDto;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.CreateUserCardInformationRequest;
import com.turkcell.rentACar2.business.requests.UserCardInformationRequest.UpdateUserCardInformationRequest;
import com.turkcell.rentACar2.core.utilities.results.DataResult;
import com.turkcell.rentACar2.core.utilities.results.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/userCardInformations")
@AllArgsConstructor
public class UserCardInformationsController {
    private UserCardInformationService userCardInformationService;

    @GetMapping("/getAll")
    public DataResult<List<UserCardInformationListDto>> getAll() {
        return this.userCardInformationService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<UserCardInformationGetDto> getById(@PathVariable int id) {
        return this.userCardInformationService.getById(id);
    }

    @PostMapping("/add/{customerId}")
    public Result add(@RequestBody @Valid CreateUserCardInformationRequest createUserCardInformationRequest, @PathVariable int customerId) {
        return this.userCardInformationService.add(createUserCardInformationRequest, customerId);
    }

    @PutMapping("/update/{id}")
    public Result update(@PathVariable int id, @RequestBody @Valid UpdateUserCardInformationRequest updateUserCardInformationRequest) {
        return this.userCardInformationService.update(id, updateUserCardInformationRequest);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        return this.userCardInformationService.delete(id);
    }
}
