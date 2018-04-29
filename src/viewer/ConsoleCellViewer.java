/*
 * Copyright (C) 2018 Szysz
 */
package viewer;

import automata_1.Cell;

/**
 *
 * @author Szysz
 */
public class ConsoleCellViewer implements CellViewer {

    int iteration, i,j;

    public ConsoleCellViewer() {
        iteration = 0;
        i = 0;
        j = 0;
        System.out.println("");
    }

    @Override
    public void view(Cell cell, int i,int j, int iteration) {
        
        
        if (this.iteration != iteration) {
            System.out.println("\n\n");
            this.iteration++;
            this.i = 0;
        }
        if (this.i != i) {
            System.out.println("");
            this.i++;
        }
        if (cell.getStatus() == 1) {
            System.out.print("1");
        } else {
            System.out.print("0");
        }

    }

}
