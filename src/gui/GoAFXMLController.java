/*
 * Copyright (C) 2018 Szysz
 */
package gui;

import automata_1.BoundaryCondition;
import automata_1.CellularAutomat2D;
import automata_1.CellularAutomataPatterns;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
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
    private Label speedLabel;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Slider speedSlider;

    private CellularAutomat2D ca;
    private AnimationThread animationThread;

    private GraphicsContext gc;
    
    private CellularAutomataPatterns patterns;

    public void startAction() {
        try{
        if (animationThread == null || !animationThread.isAlive()) {
            CellViewer[] cv = {
                new GuiCellViewer(this, (int) scrollBar.getValue()), 
                //new ConsoleCellViewer()
            };

            if (ca == null) {
                patterns = new CellularAutomataPatterns((int) canvas.getWidth() / (int) scrollBar.getValue(),(int) canvas.getHeight()/ (int) scrollBar.getValue());
                if (comboBox.getValue().equals("Periodyczny")) {
                    ca = new CellularAutomat2D(patterns.getOscilator(),(int) canvas.getWidth() / (int) scrollBar.getValue(),(int) canvas.getHeight()/ (int) scrollBar.getValue(), cv, BoundaryCondition.PERIODIC);
                } else {
                    ca = new CellularAutomat2D(patterns.getCannon(),(int) canvas.getWidth() / (int) scrollBar.getValue(),(int) canvas.getHeight()/ (int) scrollBar.getValue(), cv, BoundaryCondition.CLOSED);
                }
            }
            

            animationThread = new AnimationThread(this);
            animationThread.setDaemon(true);
            animationThread.setIsStopped(false);
            animationThread.setSpeed((int) speedSlider.getValue());
            speedSlider.valueProperty().addListener(listener -> {
                animationThread.setSpeed((int) speedSlider.getValue());
            });
            animationThread.start();
        }
        }catch(ArrayIndexOutOfBoundsException ex){
            
        }
    }

    public void pauseAction() {
        if (animationThread != null) {
            animationThread.setIsStopped(true);
        }
    }

    public void stopAction() {
        if (animationThread != null) {
            animationThread.setIsStopped(true);

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            ca = null;
        }
    }

    public void dragAction() {
        cellSize.setText(String.valueOf((int) scrollBar.getValue()));
    }

    public void view() {
        ca.view();
    }

    public CellularAutomat2D getCa() {
        return ca;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
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
        scrollBar.valueProperty().addListener(listener -> {
            cellSize.setText(String.valueOf((int) scrollBar.getValue()));
        });

        speedSlider.valueProperty().addListener(listener -> {
            speedLabel.setText(String.valueOf((int) speedSlider.getValue())+"x");
        });

        comboBox.getItems().addAll(
                "Zamknięty",
                "Periodyczny"
        );
        comboBox.getSelectionModel().selectFirst();

        gc = canvas.getGraphicsContext2D();
    }

}
