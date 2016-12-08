package ua.ibis.nbuapi.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import ua.ibis.nbuapi.task.*;
import ua.ibis.nbuapi.util.LinkParams;
import ua.ibis.nbuapi.util.PropertiesValues;
import ua.ibis.nbuapi.xml.Currency;
import ua.ibis.nbuapi.xml.NBUParser;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Controller {

    @FXML
    MenuBar menuBar;

    @FXML
    private TableView<Currency> exchTable;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private void initialize() {
        Properties prop = PropertiesValues.get();
        setTaskBindAndStart(new ExchangeFileTask(prop.getProperty("sample.file"), NBUParser::readXML));
    }

    @FXML
    private void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void openXML(ActionEvent event) {
        runTaskOpenFile("XML-файл", "*.xml", NBUParser::readXML);
    }

    @FXML
    private void openJSON(ActionEvent event) {
        runTaskOpenFile("JSON-файл", "*.json", NBUParser::readJSON);
    }

    @FXML
    private void openURLXml(ActionEvent event) {
        String link = createUrl(false, null, null);
        setTaskBindAndStart(new ExchangeApiTask(link, NBUParser::readURLXml));
    }

    @FXML
    private void openURLJson(ActionEvent event) {
        String link = createUrl(true, null, null);
        setTaskBindAndStart(new ExchangeApiTask(link, NBUParser::readURLJson));
    }

    @FXML
    private void openURLXmlDate(ActionEvent event) {
        String dateSearch = getDateFromUser();
        if ( !dateSearch.isEmpty()) {
            String link = createUrl(false, dateSearch, null);
            setTaskBindAndStart(new ExchangeApiTask(link, NBUParser::readURLXml));
        }
    }

    @FXML
    private void openURLJsonDate(ActionEvent event) {
        String dateSearch = getDateFromUser();
        if ( !dateSearch.isEmpty() ) {
            String link = createUrl(true, dateSearch, null);
            setTaskBindAndStart(new ExchangeApiTask(link, NBUParser::readURLJson));
        }
    }

    @FXML
    private void saveXML(ActionEvent event) {
        runSaveFile("XML-файл", "*.xml", NBUParser::saveToXML);
    }

    @FXML
    private void saveJSON(ActionEvent event) {
        runSaveFile("JSON-файл", "*.json", NBUParser::saveToJSON);
    }

    // создает линк с параметрами
    private String createUrl(boolean isJson, String date, String currency) {
        Properties prop = PropertiesValues.get();
        LinkParams link = new LinkParams(prop.getProperty("nbuapi"));
        if (date != null && !date.isEmpty()) {
            link.addParam(prop.getProperty("nbuapi.date"), date);
        }
        if (currency != null && !currency.isEmpty()) {
            link.addParam(prop.getProperty("nbuapi.vvv"), currency);
        }
        if (isJson) {
            link.addParam(prop.getProperty("nbuapi.json"), null);
        }

        return link.toString();
    }

    private String getDateFromUser() {

        // создаем диалог
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Курс за дату");
        dialog.setHeaderText("Выберите дату");

        // создаем выборщик даты; по умолчанию дата текущая
        DatePicker dp = new DatePicker(LocalDate.now());

        // добавляем кнопки и элементы в диалог
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        dialog.getDialogPane().setContent(dp);

        // устанавливаем конвертер, для получения результата нужного типа
        dialog.setResultConverter(button -> {
            if(button == ButtonType.OK) {
                return dp.getValue().format(DateTimeFormatter.BASIC_ISO_DATE);
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }

    // связывает свойства элементов со свойсвами задачи и запускает задачу
    private void setTaskBindAndStart(Task<ObservableList<Currency>> task) {
        // связываем активность/пассивность элементов с ходом выполнением задачи
        menuBar.disableProperty().bind(task.runningProperty());
        exchTable.disableProperty().bind(task.runningProperty());
        progressIndicator.visibleProperty().bind(task.runningProperty());

        // связываем данные таблицы с результатом выполнения задачи
        exchTable.itemsProperty().bind(task.valueProperty());

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    private File getOpenFile(String fileHead, String fileMask) {

        FileChooser chooser = new FileChooser();

        Properties prop = PropertiesValues.get();
        chooser.setInitialDirectory(new File(prop.getProperty("dirfile", ".")));
        chooser.setTitle("Выберите " + fileHead);
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(fileHead, fileMask));

        return chooser.showOpenDialog(null);
    }

    private File getSaveFile(String fileHead, String fileMask) {

        FileChooser chooser = new FileChooser();

        Properties prop = PropertiesValues.get();
        chooser.setInitialDirectory(new File(prop.getProperty("dirfile", ".")));
        chooser.setInitialFileName(fileMask);
        chooser.setTitle("Выберите " + fileHead);
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter(fileHead, fileMask));

        return chooser.showSaveDialog(null);
    }

    private void runTaskOpenFile(String fileHead, String fileMask, Function<String, List<Currency>> parser) {
        File file = getOpenFile(fileHead, fileMask);
        if (file != null) {
            setTaskBindAndStart(new ExchangeFileTask(file.getAbsolutePath(), parser));
        }
    }

    private void runSaveFile(String fileHead, String fileMask, BiConsumer<ObservableList<Currency>, String> parser) {
        File file = getSaveFile(fileHead, fileMask);
        if (file != null) {
            parser.accept(exchTable.getItems(), file.getAbsolutePath());
        }
    }

}
