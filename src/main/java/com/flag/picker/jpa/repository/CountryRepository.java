package com.flag.picker.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flag.picker.jpa.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String name);

}
