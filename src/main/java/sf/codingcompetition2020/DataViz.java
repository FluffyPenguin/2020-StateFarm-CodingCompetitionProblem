package sf.codingcompetition2020;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DataViz extends Application {

    /**
     * @param primaryStage the primary stage used for the javaFx scene
     */
    //private static CodingCompCsvUtil csvUtil;
    private int current;
    private static int[] pos1 = {150, 550};
    private static int[] pos2 = {150, 600};
    private static List<Object> currentItems;

    private final String agentListKey = "agentList.csv";
    private final String claimListKey = "claimList.csv";
    private final String customerListKey = "customerList.csv";

    private CodingCompCsvUtil csvUtil;

    public void start(Stage primaryStage) {
        csvUtil = new CodingCompCsvUtil();
        Pane pane = new Pane();
        FlowPane root = new FlowPane();

        currentItems = new ArrayList<>();

        VBox vBox = new VBox();

        ObservableList<String> options = FXCollections.observableArrayList(
                "getAgentCountInArea",
                "getAgentsInAreaThatSpeakLanguage",
                "countCustomersFromAreaThatUseAgent",
                "getCustomersRetainedForYearsByPlcyCostAsc",
                "getLeadsForInsurance",
                "getVendorsWithGivenRatingThatAreInScope",
                "getUndisclosedDrivers",
                "getAgentIdGivenRank",
                "getCustomersWithClaims"
        );

        final ComboBox comboBox = new ComboBox(options);

        onComboBoxChange(comboBox, vBox);

        comboBox.setTranslateX(150);
        comboBox.setTranslateY(500);

        vBox.getChildren().add(comboBox);
        root.getChildren().add(vBox);


        Scene scene = new Scene(root, 600, 800);

        primaryStage.setTitle("Data Viz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void onComboBoxChange(ComboBox comboBox, VBox vBox) {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (currentItems.size() != 0) {
                    while (currentItems.size() != 0) {
                        vBox.getChildren().remove(currentItems.get(0));
                        currentItems.remove(0);
                    }
                }
                if (comboBox.getValue().equals("getAgentCountInArea")) {
                    String[] areaNames = {"area-1", "area-2", "area-3", "area-4", "area-5"};
                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                    for (int i = 0; i < areaNames.length; i++) {
                        pieChartData.addAll(new PieChart.Data(areaNames[i], csvUtil.getAgentCountInArea(agentListKey, areaNames[i])));
                    }
                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Agents per Area");
                    (vBox).getChildren().add(chart);
                    currentItems.add(chart);
                } else if (comboBox.getValue().equals("getAgentsInAreaThatSpeakLanguage")) {
                    ObservableList<String> options = FXCollections.observableArrayList(
                            "Language",
                            "Area"
                    );
                    ObservableList<String> options2 = FXCollections.observableArrayList(

                    );

                    final ComboBox chooseConstant1 = new ComboBox(options);
                    chooseConstant1.setTranslateX(pos1[0]);
                    chooseConstant1.setTranslateY(pos1[1]);

                    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            String[] arr1 = {"Arabic", "Hindi", "Mandarin", "French", "Russian", "English", "Spanish"};
                            String[] arr2 = {"area-1", "area-2", "area-3", "area-4", "area-5"};
                            if (chooseConstant1.getValue().equals("Language")) {
                                for (int i = 0; i < arr1.length; i++) {
                                    options2.add(arr1[i]);
                                }
                            } else {
                                for (int i = 0; i < arr2.length; i++) {
                                    options2.add(arr2[i]);
                                }
                            }
                            ComboBox chooseConstant2 = new ComboBox(options2);
                            chooseConstant2.setTranslateX(pos2[0]);
                            chooseConstant2.setTranslateY(pos2[1]);

                            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent e) {
                                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                                    if (chooseConstant1.getValue().equals("Language")) {
                                        String current = chooseConstant2.getValue();
                                        for (int i = 0; i < arr1.length; i++) {
                                            pieChartData.addAll(new PieChart.Data(arr1[i], csvUtil.getAgentsInAreaThatSpeakLanguage(agentListKey, arr1[i], current)));
                                        }
                                    } else {
                                        String current = chooseConstant2.getValue();
                                        for (int i = 0; i < arr1.length; i++) {
                                            pieChartData.addAll(new PieChart.Data(arr2[i], csvUtil.getAgentsInAreaThatSpeakLanguage(agentListKey, arr2[i], current)));
                                        }
                                    }
                                    final PieChart chart = new PieChart(pieChartData);
                                    chart.setTitle("Agents per Area");
                                    (vBox).getChildren().add(chart);
                                    currentItems.add(chart);
                                }
                            };
                            chooseConstant2.setOnAction(event);
                            Label label2 = new Label("Enter a valid langage/area");
                            label2.setTranslateX(pos2[0]);
                            label2.setTranslateY(pos2[1]);

                            vBox.getChildren().addAll(label2, chooseConstant2);
                            currentItems.add(chooseConstant2);
                            currentItems.add(label2);
                        }
                    };

                    chooseConstant1.setOnAction(event);

                    Label label1 = new Label("Enter Value to Hold Constant");

                    label1.setTranslateX(pos1[0]);
                    label1.setTranslateY(pos1[1]);


                    vBox.getChildren().addAll(label1, chooseConstant1);

                    currentItems.add(chooseConstant1);
                    currentItems.add(label1);


                    // ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                    // pieChartData.addAll(new PieChart.Data("test", 13));
                    // final PieChart chart = new PieChart(pieChartData);
                    // chart.setTitle("Agents per Area");
                    // vBox.getChildren().add(chart);
                } else if (comboBox.getValue().equals("countCustomersFromAreaThatUseAgent")) {
                    final TextField chooseConstant1 = new TextField();
                    chooseConstant1.setTranslateX(pos1[0]);
                    chooseConstant1.setTranslateY(pos1[1]);

                    Label label1 = new Label("Enter First and Last name of an Agent (Press Enter to submit)");
                    label1.setTranslateX(pos1[0]);
                    label1.setTranslateY(pos1[1]);

                    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            int split = chooseConstant1.getValue().indexOf(" ");
                            String firstName = chooseConstant1.getValue().substring(0, split);
                            String lastName = chooseConstant2.getValue().substring(split + 1);
                            String[] areaNames = {"area-1", "area-2", "area-3", "area-4", "area-5"};
                            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                            for (int i = 0; i < areaNames.length; i++) {
                                pieChartData.addAll(new PieChart.Data(areaNames[i], csvUtil.countCustomersFromAreaThatUseAgent(agentListKey, areaNames[i], firstName, lastName)));
                            }
                            final PieChart chart = new PieChart(pieChartData);
                            chart.setTitle("Area distribution for Agent: " + firstName + " " + lastName);
                            (vBox).getChildren().add(chart);
                            currentItems.add(chart);

                        }
                    };
                    chooseConstant1.setOnAction(event);

                    vBox.getChildren().addAll(label1, chooseConstant1);
                    currentItems.add(chooseConstant1);
                    currentItems.add(label1);
                }
            }
        };
        comboBox.setOnAction(event);
    }
}
