package com.test.assessment.utils;


import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Component
public class UserUtils {


    public String getLocalDate() {
        String currentDate = LocalDate.now().toString();
        String formatDate = currentDate.replaceAll("-", "");
        return formatDate;

    }

    public String dateToString() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;

    }
}
