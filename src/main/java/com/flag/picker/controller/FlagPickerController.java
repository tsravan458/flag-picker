package com.flag.picker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flag.picker.dto.CountriesDTO;
import com.flag.picker.dto.FlagPickerDTO;
import com.flag.picker.service.FlagPickerService;
import com.google.common.base.Strings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/flagpicker")
@Api(value = "Retreive Flag Picker Data from continents.json", tags = "flag-picker")
public class FlagPickerController {

    private final FlagPickerService flagPickerService;

    public FlagPickerController(FlagPickerService flagPickerService) {
        this.flagPickerService = flagPickerService;
    }

    @RequestMapping(value = "/retreiveFlagData", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Retreive all records presisted in DB from provided JSON file.", notes = "Supports to retreive all records presisted in DB from provided JSON file.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Invalid input"), @ApiResponse(code = 500, message = "Internal Error") })
    public List<FlagPickerDTO> retreiveFlagData() {
        return flagPickerService.retreiveFlagData();
    }

    @RequestMapping(value = "/retreiveByContinent/{continent}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Retreive country,flag for the given continent.", notes = "Supports to retreive country,flag for the given continent.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Invalid input"), @ApiResponse(code = 500, message = "Internal Error") })
    public List<CountriesDTO> retreiveByContinent(HttpServletRequest request,
            @PathVariable(value = "continent") String continent) {
        List<CountriesDTO> returnByContinent = new ArrayList<>();
        if (!Strings.isNullOrEmpty(continent)) {
            returnByContinent = flagPickerService.returnByContinent(continent);
        }
        return returnByContinent;
    }

    @RequestMapping(value = "/retreiveCountryFlag/{countryName}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "Retreive flag for the given country.", notes = "Supports to retreive country,flag for the given country.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 400, message = "Invalid input"), @ApiResponse(code = 500, message = "Internal Error") })
    public Map<String, String> retreiveCountryFlag(HttpServletRequest request,
            @PathVariable(value = "flag") String countryName) {
        Map<String, String> response = new HashMap<>();
        if (!Strings.isNullOrEmpty(countryName)) {
            String result = flagPickerService.retreiveCountryFlag(countryName);
            if (!Strings.isNullOrEmpty(result)) {
                response.put("flag", result);
            }
        }
        return response;
    }
}
