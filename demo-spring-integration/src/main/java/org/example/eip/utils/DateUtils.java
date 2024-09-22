package org.example.eip.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.eip.constants.Constants.FORMAT_DATE_HEURE_EN;

public final class DateUtils {

    private DateUtils(){}

    /**
     * Retourne le timestamp courant au format string
     * @return le timestamp courant
     */
    public static String currentTimeStamp(){
        return dateToString(LocalDateTime.now(), FORMAT_DATE_HEURE_EN);
    }
    public static String dateToString(LocalDateTime  date, String pattern){
        try{
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern(pattern);
            return formatter.format(date);
        }catch (Exception e){
            return null;
        }
    }
}
