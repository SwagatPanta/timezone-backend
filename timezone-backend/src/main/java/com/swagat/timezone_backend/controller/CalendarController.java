package com.swagat.timezone_backend.controller;

import com.swagat.timezone_backend.service.BsToAdConverter;
import com.swagat.timezone_backend.service.DateConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
//    private final BsToAdConverter bsToAdConverter;
    @Autowired
    private DateConversionService dateConversionService;
    public CalendarController(DateConversionService dateConversionService) {
        this.dateConversionService = dateConversionService;
    }


    // Convert BS to AD
   // /bs-to-ad?bsYear=2081&bsMonth=12&bsDay=5
    @GetMapping("/bs-to-ad")
    public String convertBsToAd(@RequestParam int bsYear, @RequestParam int bsMonth, @RequestParam int bsDay) {
        LocalDate adDate = dateConversionService.convertBsToAd(bsYear, bsMonth, bsDay);
        return adDate.toString();
    }
//    @GetMapping("/bs-to-ad")
//    public Map<String, String> convertBsToAd(@RequestParam String bsDate) {
//        LocalDate adDate = bsToAdConverter.convertBsToAd(bsDate);
//        Map<String, String> response = new HashMap<>();
//        response.put("bsDate", bsDate);
//        response.put("adDate", adDate.toString());
//        return response;
//    }
//
//    @GetMapping("/ad-to-bs")
//    public Map<String, String> convertAdToBs(@RequestParam String adDate) {
//        String bsDate = bsToAdConverter.convertAdToBs(LocalDate.parse(adDate));
//        Map<String, String> response = new HashMap<>();
//        response.put("adDate", adDate);
//        response.put("bsDate", bsDate);
//        return response;
//    }
}
