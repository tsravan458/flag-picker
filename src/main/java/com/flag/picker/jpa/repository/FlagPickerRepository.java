package com.flag.picker.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flag.picker.jpa.model.Continent;

@Repository
public interface FlagPickerRepository extends JpaRepository<Continent, Long> {

    @Query(value = "SELECT cn.continent,ct.country_name,ct.flag FROM CONTINENT cn JOIN COUNTRY ct on cn.id=ct.continent_id order  by ct.id asc", nativeQuery = true)
    List<Object[]> returnAllInfo();

    @Query(value = "SELECT ct.country_name,ct.flag FROM CONTINENT cn JOIN COUNTRY ct on cn.id=ct.continent_id where cn.continent=:continent order  by ct.id asc", nativeQuery = true)
    List<Object[]> returnByContinent(@Param("continent") String continent);

}
