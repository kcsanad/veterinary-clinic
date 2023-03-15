package com.greatit.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import com.greatit.components.Result;

@RestController
@RequestMapping(path = "/clinic", produces="application/json")
@CrossOrigin(origins="*")
public class ClinicController {
    
    @Autowired
    private Result result;

    @GetMapping(value = "/cronjob")
    public ResponseEntity<Result> GetResult() {
        result.Reset();
        result.Process();

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Result> resp = new ResponseEntity<>(result,headers,HttpStatus.OK);
         
        return resp;
    }
}
