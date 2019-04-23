package com.flag.picker;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.flag.picker.jpa.model.Continent;
import com.flag.picker.jpa.model.Country;
import com.flag.picker.service.FlagPickerService;

@Component
public class LoadDataOnStartup {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataOnStartup.class);

    private final FlagPickerService service;

    public LoadDataOnStartup(FlagPickerService service) {
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    @Bean
    CommandLineRunner runner() {
        return args -> {
            LOGGER.info("Loading Data from continents.json on startup... STARTED!");
            JSONParser jsonParser = new JSONParser();
            final String filePath = "static/json/continents.json";
            try (FileReader reader = new FileReader(getClass().getClassLoader().getResource(filePath).getFile())) {
                Object obj = jsonParser.parse(reader);
                JSONArray jsonArray = (JSONArray) obj;
                jsonArray.forEach(arrayObj -> parseObject((JSONObject) arrayObj));
                LOGGER.info("Loading Data from continents.json on startup...COMPLETED!");
            } catch (FileNotFoundException e) {
                LOGGER.error("Unable to find the file => '{}', with exception => '{}',", filePath, e.getMessage());
            } catch (IOException e) {
                LOGGER.error("Exception => '{}'", e.getMessage());
            }

        };
    }

    @SuppressWarnings("unchecked")
    private void parseObject(JSONObject jsonObject) {
        String name = (String) jsonObject.get("continent");
        Set<Country> countries = new HashSet<>();
        JSONArray jsonArray = (JSONArray) jsonObject.get("countries");
        for (Object object : jsonArray) {
            Map<String, String> response = (Map<String, String>) object;
            Country country = new Country();
            country.setFlag((String) response.get("flag"));
            country.setName((String) response.get("name"));
            countries.add(country);
        }
        Continent continent = new Continent();
        continent.setContinent(name);
        continent.setCountries(countries);
        service.save(continent);
    }

}
