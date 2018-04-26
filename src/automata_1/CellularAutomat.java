/*
 * Copyright (C) 2018 Szysz
 */
package automata_1;

import java.util.Arrays;
import viewer.CellViewer;

/**
 *
 * @author Szysz
 */
public class CellularAutomat {

    private final Cell automata[][];
    private final int size;

    private final BoundaryCondition bc;

    private int iteration;

    private final CellViewer viewers[];

    public CellularAutomat(int size, int rule, CellViewer viewers[], BoundaryCondition bc) {

        this.bc = bc;
        this.iteration = 0;

        int tab[][] = new int[size][size];
        Arrays.fill(tab, 0);
        tab[size / 2][size / 2] = 1;

        this.viewers = viewers;
        this.size = size;

        automata = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 && j == 0) {
                    int[] neighboars = {0,0,0,0,tab[i][j+1],0,tab[i+1][j+1]};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }
                else if(j == 0){
                    int[] neighboars = {0,tab[i-1][j],tab[i-1][j+1],0,tab[i][j+1],0,tab[i+1][j], tab[i+1][j+1]};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }
                else if(i==0){
                    int[] neighboars = {0,0,0,tab[i][j-1],tab[i][j+1],tab[i+1][j-1],tab[i+1][j],tab[i+1][j+1]};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }else if(i==0 && j==size-1){
                    int[] neighboars = {0,0,0,tab[i][j-1],tab[i][j+1],tab[i+1][j-1],tab[i+1][j],tab[i+1][j+1]};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }else if(j == size-1){
                    int[] neighboars = {tab[i-1][j-1],tab[i-1][j],0,tab[i][j-1],0,tab[i+1][j-1],tab[i+1][j],0};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }else if(i==size-1){
                    int[] neighboars = {tab[i-1][j-1],tab[i-1][j],tab[i-1][j+1],tab[i][j-1],tab[i][j+1],0,0,0};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }else if(i==size-1 && j ==size-1){
                    int[] neighboars = {tab[i-1][j-1],tab[i-1][j],0,tab[i][j-1],0,0,0,0};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }else if(i==size-1 && j ==0){
                    int[] neighboars = {0,tab[i-1][j],tab[i-1][j+1],0,tab[i][j+1],0,tab[i+1][j],tab[i+1][j+1]};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }else{
                    int[] neighboars = {tab[i-1][j-1],tab[i-1][j],tab[i-1][j+1],tab[i][j-1],tab[i][j+1],tab[i+1][j-1],tab[i+1][j],tab[i+1][j+1]};
                    automata[i][j] = new Cell(tab[i][j],neighboars);
                }
            }
        }
    }

    public void view() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (CellViewer cv : viewers) {
                    cv.view(automata[i][j], i, j, iteration);
                }
            }
        }
        iteration++;
    }

    public void nextIteration() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                automata[i][j].nextIteration();
            }
        }

        //TODO
        this.iteration++;
    }
}
