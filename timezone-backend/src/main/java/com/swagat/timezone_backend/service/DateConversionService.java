package com.swagat.timezone_backend.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DateConversionService {
    // Reference starting point (Bikram Sambat 2000-01-01 = Gregorian 1943-04-14)
    private static final LocalDate REFERENCE_AD_DATE = LocalDate.of(1943, 4, 14);
    private static final int REFERENCE_BS_YEAR = 2000;
    private static final Map<Integer, int[]> bsDaysInMonth = new HashMap<>();

    // Initialize BS month days (example data from 2000 BS to 2100 BS)
    static {
        bsDaysInMonth.put(2000, new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bsDaysInMonth.put(2001, new int[]{31, 31, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        bsDaysInMonth.put(2002, new int[]{31, 31, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        // Add more years up to 2100 manually (or fetch from a database)
    }

    /**
     * Convert a given BS (Bikram Sambat) date to AD (Gregorian).
     */
    public LocalDate convertBsToAd(int bsYear, int bsMonth, int bsDay) {
        if (bsYear < REFERENCE_BS_YEAR || !bsDaysInMonth.containsKey(bsYear)) {
            throw new IllegalArgumentException("BS Year out of range or not supported yet.");
        }

        LocalDate adDate = REFERENCE_AD_DATE; // Start from 1943-04-14
        int currentBsYear = REFERENCE_BS_YEAR;
        int currentBsMonth = 1;
        int currentBsDay = 1;

        // Traverse years from 2000 BS to target year
        while (currentBsYear < bsYear) {
            int[] months = bsDaysInMonth.get(currentBsYear);
            for (int daysInMonth : months) {
                adDate = adDate.plusDays(daysInMonth);
            }
            currentBsYear++;
        }

        // Traverse months in the target year
        int[] targetYearMonths = bsDaysInMonth.get(bsYear);
        for (int i = 0; i < bsMonth - 1; i++) {
            adDate = adDate.plusDays(targetYearMonths[i]);
        }

        // Traverse days in the target month
        adDate = adDate.plusDays(bsDay - 1);

        return adDate;
    }
}
