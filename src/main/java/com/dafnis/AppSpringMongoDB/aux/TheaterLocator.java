package com.dafnis.AppSpringMongoDB.aux;

import java.util.List;
import com.dafnis.AppSpringMongoDB.models.*;

public class TheaterLocator {

    public static double haversineDistance(Coord c1, Coord c2) {
        final int R = 6371; 
        double latDistance = Math.toRadians(c2.latitud - c1.latitud);
        double lonDistance = Math.toRadians(c2.longitud - c1.longitud);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(c1.latitud)) * Math.cos(Math.toRadians(c2.latitud))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public static Coord findClosestTheater(Coord newCoord, List<Coord> theaters) {
        Coord closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Coord theater : theaters) {
            double distance = haversineDistance(newCoord, theater);
            if (distance < minDistance) {
                minDistance = distance;
                closest = theater;
            }
        }
        return closest;
    }
}
