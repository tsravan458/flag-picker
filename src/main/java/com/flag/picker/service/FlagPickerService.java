package com.flag.picker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flag.picker.dto.CountriesDTO;
import com.flag.picker.dto.FlagPickerDTO;
import com.flag.picker.jpa.model.Continent;
import com.flag.picker.jpa.model.Country;
import com.flag.picker.jpa.repository.CountryRepository;
import com.flag.picker.jpa.repository.FlagPickerRepository;

@Service
public class FlagPickerService {

    private final FlagPickerRepository flagPickerRepository;

    private final CountryRepository countryRepository;

    public FlagPickerService(FlagPickerRepository flagPickerRepository, CountryRepository countryRepository) {
        this.flagPickerRepository = flagPickerRepository;
        this.countryRepository = countryRepository;
    }

    public List<FlagPickerDTO> retreiveFlagData() {
        List<FlagPickerDTO> flagPickerDTOs = new ArrayList<>();
        List<Object[]> returnAllInfo = flagPickerRepository.returnAllInfo();
        for (Object[] objects : returnAllInfo) {
            FlagPickerDTO flagPickerDTO = new FlagPickerDTO();
            flagPickerDTO.setContinent((String) objects[0]);
            flagPickerDTO.setCountryName((String) objects[1]);
            flagPickerDTO.setFlag((String) objects[2]);
            flagPickerDTOs.add(flagPickerDTO);
        }
        return flagPickerDTOs;
    }

    public List<CountriesDTO> returnByContinent(String continent) {
        List<CountriesDTO> countriesDTOs = new ArrayList<>();
        List<Object[]> returnAllInfo = flagPickerRepository.returnByContinent(continent);
        if (!returnAllInfo.isEmpty()) {
            for (Object[] objects : returnAllInfo) {
                CountriesDTO countriesDTO = new CountriesDTO();
                countriesDTO.setCountryName((String) objects[0]);
                countriesDTO.setFlag((String) objects[1]);
                countriesDTOs.add(countriesDTO);
            }
        }
        return countriesDTOs;
    }

    public String retreiveCountryFlag(String countryName) {
        String result = "";
        Country country = countryRepository.findByName(countryName);
        if (country != null) {
            result = country.getFlag();
        }
        return result;
    }

    public Continent save(Continent continent) {
        return flagPickerRepository.save(continent);
    }

    public void save(List<Continent> continents) {
        flagPickerRepository.saveAll(continents);
    }

}
