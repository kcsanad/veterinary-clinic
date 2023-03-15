package com.greatit.components;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Component
@Data
public class Result {
    @Expose
    String rdate = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    @Expose 
    int barks = 0;
    @Expose 
    int hissis = 0;
    @Expose 
    int verbalInterruptions = 0;

    @JsonIgnore 
    @Autowired
    @ToString.Exclude private Arrivals arrivals;

    @JsonIgnore 
    private final Logger logger = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Result.class);
    
    /**
     * Processing the Arrivals input YAML
     * @return - the Result which contains business case defined calculation
     */
    public Result Process() {
        Iterator<Arrival> arrivalIterator = arrivals.getArrivals().iterator();

        // Going through the List, and send them to the examination room, one by one
        while (arrivalIterator.hasNext()) {
            arrivalIterator
                .next()
                .GoToExamination(this, arrivals);
        }

        return this;
    }

    /**
     * Provides the Result as JSON, display to STDOUT
     */
    public void Display() {
        Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
        logger.info(gson.toJson(this));
    }

    /**
     * Reset values of Result back to orignal state, before begins a new iteraton
     */
    public void Reset() {
        this.setBarks(0);
        this.setHissis(0);
        this.setVerbalInterruptions(0);
        arrivals.Reset();
    }
}
