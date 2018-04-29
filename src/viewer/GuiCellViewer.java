/*
 * Copyright (C) 2018 Szysz
 */
package viewer;

import automata_1.Cell;
import gui.GoAFXMLController;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Szysz
 */
public class GuiCellViewer implements CellViewer {

    GoAFXMLController controller;
    int size;

    public GuiCellViewer(GoAFXMLController controller, int size) {
        this.controller = controller;
        this.size = size;

    }

    @Override
    public void view(Cell cell, int i,int j, int iteration) {
        GraphicsContext gc = controller.getCanvas().getGraphicsContext2D();

        if (cell.getStatus() == 1) {
            gc.fillRect(size * i, size * j, size, size);
        }
    }
}
