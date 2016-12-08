package ua.ibis.nbuapi.util;

import java.time.format.DateTimeFormatter;

/**
 * Created by conti on 07.12.2016.
 */
public class Formatter {

    public static final DateTimeFormatter nbuFileDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static final DateTimeFormatter cellDate = DateTimeFormatter.ofPattern("dd LLLL yyyy");

}
