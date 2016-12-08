package ua.ibis.nbuapi.task;

import ua.ibis.nbuapi.util.MyDialog;
import ua.ibis.nbuapi.xml.Currency;

import java.util.List;
import java.util.function.Function;

/**
 * Created by conti on 07.12.2016.
 */
public class ExchangeFileTask extends ExchangeTask {

    public ExchangeFileTask(String resourceLink, Function<String, List<Currency>> parse) {
        super(resourceLink, parse);
    }

    @Override
    protected void failed() {
        MyDialog.errorMessage("Ошибка чтения файла");
    }

}
