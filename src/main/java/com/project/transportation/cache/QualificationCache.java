package com.project.transportation.cache;

import com.project.transportation.model.Qualification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualificationCache {

    // Map to store qualificationId -> points
    private static Map<Integer, Integer> qualificationPointsCache = new HashMap<>();

    // Method to load qualification data into cache
    public static void loadQualificationsIntoCache(List<Qualification> qualifications) {
        qualificationPointsCache.clear(); // Clear existing cache
        for (Qualification qualification : qualifications) {
            qualificationPointsCache.put(qualification.getId(), qualification.getPoints());
        }
    }

    // Method to get qualification points by ID
    public static int getQualificationPoints(Integer qualificationId) {
        return qualificationPointsCache.getOrDefault(qualificationId, 0); // Return 0 if qualification not found
    }

    public static void clearCache() {
        qualificationPointsCache.clear();
    }
}