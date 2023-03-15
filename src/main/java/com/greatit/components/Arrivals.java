package com.greatit.components;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.greatit.factory.YamlPropertySourceFactory;

import lombok.Data;

// example for file based configuration: 
// INPUT_FILE=file:/config/saturday-pet-arrival.yml mvn spring-boot:run

@Configuration
@ConfigurationProperties
@PropertySource(value = "${INPUT_FILE:classpath:saturday-pet-arrival.yml}", factory = YamlPropertySourceFactory.class)
@Data
public class Arrivals {
    private List<Arrival> arrivals = new ArrayList<>();

    /**
     * calcutes the number of dogs in the waiting room, how many will bark back
     * @return
     */
    public long CountDogsInWaitingRoom() {
        return this.arrivals
            .stream()
            .filter(Arrival::isDogsWaiting)
            .count();
    }

     /**
     * Reset values of Arrivals's examinated back to orignal state, before begins a new iteraton
     */
    public void Reset() {
        this.arrivals
            .forEach(c -> c.setExamined(false));
    }
}
