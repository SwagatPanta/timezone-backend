package com.swagat.timezone_backend.controller;

import com.swagat.timezone_backend.service.TimezoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TimezoneController {
    @Autowired
    private TimezoneService timezoneService;
    @GetMapping("/timezone")
    public Map<String,String> getTimezone(){
        return timezoneService.getCurrentTimezone();
    }

    //http://localhost:8080/api/mytimezone?zone=America/New_York
    @GetMapping("/mytimezone")
    public Map<String, String> getTimezone(@RequestParam(required = false) String zone) {
        ZoneId zoneId = (zone != null) ? ZoneId.of(zone) : ZoneId.systemDefault();
        return timezoneService.getCurrentTimezone1(zoneId);
    }
}
