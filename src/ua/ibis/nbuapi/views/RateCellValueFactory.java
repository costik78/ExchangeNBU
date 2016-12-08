package ua.ibis.nbuapi.views;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by conti on 01.12.2016.
 */
public class RateCellValueFactory<S, T> implements Callback<TableColumn<S, BigDecimal>, TableCell<S, BigDecimal>> {

    private static final NumberFormat nf = new DecimalFormat("#,##0.0000000000");

    @Override
    public TableCell<S, BigDecimal> call(TableColumn<S, BigDecimal> param) {
        return new TableCell<S, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);

                if(empty || item == null) {
                    setText(null);
                } else {
                    setText(nf.format(item));
                }
            }
        };
    }
}
