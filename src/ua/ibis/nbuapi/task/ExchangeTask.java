package ua.ibis.nbuapi.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import ua.ibis.nbuapi.xml.Currency;

import java.util.List;
import java.util.function.Function;

/**
 *  ласс дл€ задани€ по получению списка валют. ¬ зависимости от передаваемой в конструкторе функции
 * может извлекать данные из разных источников, на которые указывает @resourceLink
 * Created by conti on 07.12.2016.
 */
public class ExchangeTask extends Task<ObservableList<Currency>> {

    private String resourceLink;
    private Function<String, List<Currency>> parser;

    public ExchangeTask(String resourceLink, Function<String, List<Currency>> parser) {
        this.resourceLink = resourceLink;
        this.parser = parser;
    }

    @Override
    protected ObservableList<Currency> call() throws Exception {
        List<Currency> currencies = parser.apply(resourceLink);
        return FXCollections.observableList(currencies);
    }

}
