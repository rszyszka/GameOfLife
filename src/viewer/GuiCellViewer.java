/*
 * Copyright (C) 2018 Szysz
 */
package viewer;

import automata_1.Cell;
import gui.GoAFXMLController;

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
    public void view(Cell cell, int i, int j, int iteration) {
        if (cell.getStatus() == 1) {
            controller.getGc().fillRect(size * j, size * i, size, size);
        }
//else {
//            controller.getGc().clearRect(size * j, size * i, size, size);
//        }
    }
}
