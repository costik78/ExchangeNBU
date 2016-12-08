package ua.ibis.nbuapi.task;

import ua.ibis.nbuapi.util.MyDialog;
import ua.ibis.nbuapi.xml.Currency;

import java.util.List;
import java.util.function.Function;

/**
 * Created by conti on 07.12.2016.
 */
public class ExchangeApiTask extends ExchangeTask {

    public ExchangeApiTask(String resourceLink, Function<String, List<Currency>> parse) {
        super(resourceLink, parse);
    }

    @Override
    protected void failed() {
        MyDialog.errorMessage("Не удалось соединиться с НБУ. Проверьте настройки интернета.");
    }

}
