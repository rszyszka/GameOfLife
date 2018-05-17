/*
 * Copyright (C) 2018 Szysz
 */
package gui;

import automata_1.BoundaryCondition;
import automata_1.CellularAutomat2D;
import automata_1.CellularAutomataPattern;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    private ScrollBar cellSizeScrollBar;
    @FXML
    private Label cellSizeLabel;
    @FXML
    private Label speedLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private ComboBox<String> boundaryCondComboBox;
    @FXML
    private ComboBox<String> patternComboBox;
    @FXML
    private Slider speedSlider;
    @FXML
    private ColorPicker colorPicker;

    private CellularAutomat2D ca;
    private AnimationThread animationThread;

    private GraphicsContext gc;

    private CellularAutomataPattern pattern;

    private int sizeX;
    private int sizeY;

    private boolean isStarted = false;

    public void startAction() {
        cellSizeScrollBar.setDisable(true);
        if (warningLabel.getText().equals("")) {
            if (animationThread == null || !animationThread.isAlive()) {
                CellViewer[] cv = {
                    new GuiCellViewer(this, (int) cellSizeScrollBar.getValue()), //new ConsoleCellViewer()
                };

                if (ca == null) {

                    if (boundaryCondComboBox.getValue().equals("Periodyczny")) {
                        ca = new CellularAutomat2D(pattern.getPattern(), sizeX, sizeY, cv, BoundaryCondition.PERIODIC);
                    } else {
                        ca = new CellularAutomat2D(pattern.getPattern(), sizeX, sizeY, cv, BoundaryCondition.CLOSED);
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
                isStarted = true;
            }
        }
    }

    public void pauseAction() {
        if (animationThread != null) {
            animationThread.setIsStopped(true);
        }
    }

    public void mouseClickedAction(MouseEvent e) {
        
            int size = (int) cellSizeScrollBar.getValue();
            int posX, posY;
            posX = (int) e.getX() / size;
            if (posX >= sizeX) {
                posX = sizeX-1;
            }else if (posX <0) {
                posX = 0;
            }
            posY = (int) e.getY() / size;
            if (posY >= sizeY) {
                posY = sizeY-1;
            }else if (posY < 0) {
                posY = 0;
            }

            if (isStarted) {
                ca.setAutomata(posX, posY);
                view();
            } else {
                pattern.setCell(posX, posY);
                draw();
            }

    }

    public void stopAction() throws InterruptedException {
        cellSizeScrollBar.setDisable(false);
        isStarted = false;
        if (animationThread != null) {
            animationThread.setIsStopped(true);

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            animationThread.join();
            ca = null;

        }
        draw();
    }

    public void dragAction() {
        cellSizeLabel.setText(String.valueOf((int) cellSizeScrollBar.getValue()));
    }

    public void view() {
        if (ca != null) {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            ca.view();
        }
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

        sizeX = (int) canvas.getWidth() / (int) cellSizeScrollBar.getValue();
        sizeY = (int) canvas.getHeight() / (int) cellSizeScrollBar.getValue();

        cellSizeLabel.setText(String.valueOf((int) cellSizeScrollBar.getValue()));
        cellSizeScrollBar.valueProperty().addListener(listener -> {
            cellSizeLabel.setText(String.valueOf((int) cellSizeScrollBar.getValue()));
            warningLabel.setText("");
            sizeX = (int) canvas.getWidth() / (int) cellSizeScrollBar.getValue();
            sizeY = (int) canvas.getHeight() / (int) cellSizeScrollBar.getValue();

            try {
                pattern = new CellularAutomataPattern(sizeX, sizeY, patternComboBox.getValue());
                if (!isStarted) {
                    draw();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                warningLabel.setText("Za duży rozmiar komórki!!!");
            }
        });

        speedSlider.valueProperty().addListener(listener -> {
            speedLabel.setText(String.valueOf((int) speedSlider.getValue()) + "x");
        });

        boundaryCondComboBox.getItems().addAll(
                "Zamknięty",
                "Periodyczny"
        );
        patternComboBox.getItems().addAll(
                "Pusty",
                "Losowa",
                "Działo",
                "Oscylator",
                "Glider",
                "Stały"
        );
        boundaryCondComboBox.getSelectionModel().selectFirst();
        patternComboBox.getSelectionModel().selectFirst();

        pattern = new CellularAutomataPattern(sizeX, sizeY, patternComboBox.getValue());
        patternComboBox.valueProperty().addListener(listener -> {
            try {
                pattern = new CellularAutomataPattern(sizeX, sizeY, patternComboBox.getValue());
                if (!isStarted) {
                    draw();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                warningLabel.setText("Za duży rozmiar komórki!!!");
            }
        });

        gc = canvas.getGraphicsContext2D();
        colorPicker.setValue(Color.BLACK);
        colorPicker.valueProperty().addListener(listener -> {
            gc.setFill(colorPicker.getValue());
            if (!isStarted) {
                draw();
            }
        });
    }

    public void draw() {
        int size = (int) cellSizeScrollBar.getValue();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                if (pattern.getPattern()[i][j] == 1) {
                    gc.fillRect(size * j, size * i, size, size);
                }
            }
        }
    }

}
