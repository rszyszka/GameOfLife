/*
 * Copyright (C) 2018 Szysz
 */
package gui;

import automata_1.BoundaryCondition;
import automata_1.CellularAutomat2D;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import viewer.CellViewer;
import viewer.ConsoleCellViewer;
import viewer.GuiCellViewer;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class GoAFXMLController implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private ScrollBar scrollBar;
    @FXML
    private Label cellSize;
    @FXML
    private ComboBox<String> comboBox;

    CellularAutomat2D ca;

    public void startAction() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        CellViewer[] cv = {
            new GuiCellViewer(this, (int) scrollBar.getValue()), //new ConsoleCellViewer()
        };

        if (comboBox.getValue().equals("Periodyczny")) {
            ca = new CellularAutomat2D((int) canvas.getWidth() / (int) scrollBar.getValue(), cv, BoundaryCondition.PERIODIC);
        } else {
            ca = new CellularAutomat2D((int) canvas.getWidth() / (int) scrollBar.getValue(), cv, BoundaryCondition.CLOSED);
        }

        view();

    }

    public void pauseAction() {

    }

    public void stopAction() {

    }

    public void dragAction() {
        cellSize.setText(String.valueOf((int) scrollBar.getValue()));
    }

    public void view() {
        ca.view();
//        for (int i = 0; i < (int) canvas.getWidth() / (int) scrollBar.getValue(); i++) {
//            ca.nextIteration();
//        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cellSize.setText(String.valueOf((int) scrollBar.getValue()));
        scrollBar.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            cellSize.setText(String.valueOf((int) scrollBar.getValue()));
        });

        comboBox.getItems().addAll(
                "Zamknięty",
                "Periodyczny"
        );
        comboBox.getSelectionModel().selectFirst();
    }

}
