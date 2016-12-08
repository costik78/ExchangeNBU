package ua.ibis.nbuapi.views;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Created by conti on 01.12.2016.
 */
public class IndexingCellValueFactory<S, T> implements Callback<TableColumn.CellDataFeatures<S, Number>, ObservableValue<Number>> {
    @Override
    public ObservableValue<Number> call(TableColumn.CellDataFeatures<S, Number> param) {
        return new ReadOnlyObjectWrapper<>(param.getTableView().getItems().indexOf(param.getValue()) + 1);
    }
}
