package com.harrisonbrock.carsapi.logging;

import lombok.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CarMessage implements Serializable {
    private final String text;
    private final String dataFormatter;

    public CarMessage(String text) {
        this.text = text;
        Date date = new Date();
        String formatter = "yyyy-MM-dd hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(formatter);
        dataFormatter = dateFormat.format(date);
    }
}
