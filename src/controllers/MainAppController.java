package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.task;

/**
 *
 *
 * @author AL
 */
public class MainAppController implements Initializable {

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private RadioMenuItem Arial;
    @FXML
    private ToggleGroup fontfamily;
    @FXML
    private RadioMenuItem Georgia;
    @FXML
    private RadioMenuItem Verdana;
    @FXML
    private RadioMenuItem Times_New_Roman;
    @FXML
    private RadioMenuItem fontSize12;
    @FXML
    private ToggleGroup fontSize;
    @FXML
    private RadioMenuItem fontSize14;
    @FXML
    private RadioMenuItem fontSize16;
    @FXML
    private RadioMenuItem fontNormalItem;
    @FXML
    private ToggleGroup FontStyle;
    @FXML
    private RadioMenuItem fontBoldItem;
    @FXML
    private RadioMenuItem fontItalicItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField searchUserField;
    @FXML
    private ListView<Object> taskListView;
    @FXML
    private Label totalTasksLabel;
    @FXML
    private Label openTasksLabel;
    @FXML
    private Label closedTasksLabel;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField addedBy;
    @FXML
    private Button addTaskBtn;

    Map<Integer, task> taskMap = new HashMap<>();
    @FXML
    private HBox searchFiled;
    @FXML
    private Label countLable;
    @FXML
    private Button countBtn;
    @FXML
    private Label highestLable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datePicker.setEditable(false);    // cannot write the date 
        statusComboBox.getItems().addAll("Closed", "Open");   
  
        try {   // load the csvFile to listView
            taskMap
                    = Files.lines(Paths.get("C:\\Users\\AL\\Documents\\NetBeansProjects\\Task\\src\\data\\taskData.csv"))
                            .skip(1)
                            .map(line -> line.split(","))
                            .map(data -> new task(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]))
                            .collect(Collectors.toMap(
                                    task::getId, // key
                                    c -> c // value
                            ));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        taskListView.getItems().addAll(taskMap.values());
        totalTasksLabel.setText(taskMap.size() + " Task");
        count();

    }

    @FXML  // adding new task
    private void addTaskBtnHandle(ActionEvent event) {
        try {
              //data from fields  
            int newId = taskMap.size() + 1;
            String title = titleField.getText();
            String user = addedBy.getText();
            String status = (statusComboBox.getValue() != null) ? statusComboBox.getValue() : null;
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : null;

            task temp = new task(newId, title, status, user, date);
            String tempCsv = temp.toCSV();  // to save to the csv file 
            saveToFile(tempCsv); 
            taskMap.put(newId, temp);  //adding to map
            taskListView.getItems().add(temp);
            totalTasksLabel.setText(taskMap.size() + " Task"); // edite the total
            clear(); // clearing the fields
            count();  // count number of closed an dopen task
        } catch (IllegalArgumentException ex) {
            showAlert("warning", "warning", "invalid Data", ex.getMessage());

        }
    }
        // writing the new task to the csv file
    private void saveToFile(String csvString) {
        try (FileWriter fw = new FileWriter("C:\\Users\\AL\\Documents\\NetBeansProjects\\Task\\src\\data\\taskData.csv", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.println(csvString);
            System.out.println("saved");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void clear() {  // clearing fields
        titleField.setText("");
        addedBy.setText("");
        statusComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        highestLable.setText("");
        countLable.setText("");
        searchUserField.clear();

    }

    @FXML
    private void searchUserBtn(ActionEvent event) {  // search for task by user
        String name = searchUserField.getText();
        List<String> list = taskMap.values().stream()
                .filter(value -> value.getAddedBy().equalsIgnoreCase(name))
                .sorted(Comparator.comparingInt(data -> data.getId()))
                .map(data -> data.getTitel())
                .collect(Collectors.toList());
        taskListView.getItems().clear();
        taskListView.getItems().addAll(list);
        countLable.setText(list.size() + " ");

    }

    private void count() {  // count closed and open tasks
        long openCount = taskMap.values().stream()
                .filter(t -> t.getState().equalsIgnoreCase("open"))
                .count();

        long closedCount = taskMap.values().stream()
                .filter(t -> t.getState().equalsIgnoreCase("closed"))
                .count();

        openTasksLabel.setText("Open Tasks: " + openCount);
        closedTasksLabel.setText("Closed Tasks: " + closedCount);
    }

    // menuBar
    @FXML
    private void exitHandle(ActionEvent event) {
        ((Stage) menuBar.getScene().getWindow()).close();

    }

    @FXML
    private void fontFamilyHandle(ActionEvent event) {
        RadioMenuItem source = (RadioMenuItem) event.getSource();
        if (source.getId().equals("Arial")) {
            rootPane.setStyle("-fx-font-family:Arial;");
        } else if (source.getId().equals("Georgia")) {
            rootPane.setStyle("-fx-font-family:Georgia;");
        } else if (source.getId().equals("Verdana")) {
            rootPane.setStyle("-fx-font-family:Verdana;");
        } else if (source.getId().equals("Times_New_Roman")) {
            rootPane.setStyle("-fx-font-family:'Times new roman';");
        }
    }

    @FXML
    private void fontSizeHandle(ActionEvent event) {
        RadioMenuItem source = (RadioMenuItem) event.getSource();
        if (source.getId().equals("fontSize12")) {
            rootPane.setStyle("-fx-font-size:12" + "px;");
        } else if (source.getId().equals("fontSize14")) {
            rootPane.setStyle("-fx-font-size:14" + "px;");
        } else if (source.getId().equals("fontSize16")) {
            rootPane.setStyle("-fx-font-size:16" + "px;");
        }

    }

    @FXML
    private void fontStyleHandle(ActionEvent event) {
        RadioMenuItem source = (RadioMenuItem) event.getSource();

        if (source.getId().equals("fontBoldItem")) {
            rootPane.setStyle("-fx-font-weight: bold;");
        } else if (source.getId().equals("fontItalicItem")) {
            rootPane.setStyle("-fx-font-style:italic;");
        } else if (source.getId().equals("fontNormalItem")) {
            rootPane.setStyle("-fx-font-weight:italic;");

        }
    }

    @FXML
    private void aboutHandle(ActionEvent event) {
        showAlert("information", "About", "Task Management System", "Version:1.0\nRaghad Saqallah");

    }

    public void showAlert(String type, String title, String header, String content) {
        Alert alert = null;
        if (type.equals("information")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else if (type.equals("warning")) {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
 // end of menuBar
    
    @FXML
    private void highestNohandle(ActionEvent event) {  // user with highest number of tasks
        Map<String, Long> tempMap = taskMap.values().stream()
                .collect(Collectors.groupingBy(task::getAddedBy, Collectors.counting()));

        List<Map.Entry<String, Long>> sortedList = tempMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());

        Map.Entry<String, Long> topEntry = sortedList.get(0);

        String name = topEntry.getKey();
        highestLable.setText(name);
    }

    @FXML
    private void ResetBtn(ActionEvent event) {  // resit the app
        clear();
        taskListView.getItems().clear();
        taskListView.getItems().addAll(taskMap.values());
        totalTasksLabel.setText(taskMap.size() + " Task");
        count();
    }

    @FXML
    private void filterBtn(ActionEvent event) {  // filtering for the task start with the litter a and length 7
        List<task> filter = taskMap.values().stream()
                .filter(t -> t.getTitel().toLowerCase().startsWith("a"))
                .filter(t -> t.getTitel().length() == 7)
                .collect(Collectors.toList());
        taskListView.getItems().clear();
        taskListView.getItems().addAll(filter);
    }

    @FXML
    private void topFourBtn(ActionEvent event) {  // last 4 task added 
       List<task> list =  taskMap.values().stream()
                .sorted(Comparator.comparing(task::getCreationDate))
                .limit(4)
                .collect(Collectors.toList());
       
        taskListView.getItems().clear();
        taskListView.getItems().addAll(list);
    }

}
