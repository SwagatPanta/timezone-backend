package com.swagat.timezone_backend.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BsToAdConverter {
    public LocalDate convertBsToAd(String bsDate) {
        // Simple mock implementation (actual logic needs a lookup table or formula)
        // Assuming BS 2078-01-01 maps to AD 2021-04-14
        return LocalDate.of(2021, 4, 14).plusDays(parseBsDate(bsDate));
    }

    public String convertAdToBs(LocalDate adDate) {
        // Reverse calculation logic
        LocalDate baseAdDate = LocalDate.of(2021, 4, 14);
        int daysBetween = (int) (adDate.toEpochDay() - baseAdDate.toEpochDay());
        return "2078-01-" + (daysBetween + 1);
    }

    private int parseBsDate(String bsDate) {
        String[] parts = bsDate.split("-");
        int day = Integer.parseInt(parts[2]);
        return day - 1; // Just an approximation
    }
}
