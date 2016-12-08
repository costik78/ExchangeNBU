package ua.ibis.nbuapi.views;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import ua.ibis.nbuapi.util.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by conti on 06.12.2016.
 */
public class DateCellFactory<S, T> implements Callback<TableColumn<S, LocalDate>, TableCell<S, LocalDate>> {

    @Override
    public TableCell<S, LocalDate> call(TableColumn<S, LocalDate> param) {

        return new TableCell<S, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(Formatter.cellDate));
                }
            }
        };
    }
}
