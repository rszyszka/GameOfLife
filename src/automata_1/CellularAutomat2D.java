/*
 * Copyright (C) 2018 Szysz
 */
package automata_1;

import viewer.CellViewer;

/**
 *
 * @author Szysz
 */
public class CellularAutomat2D {

    private final Cell automata[][];
    private final int sizeX, sizeY;

    private final BoundaryCondition bc;

    private int iteration;

    private final CellViewer viewers[];

    public CellularAutomat2D(int[][] tab,int sizeX,int sizeY, CellViewer viewers[], BoundaryCondition bc) {

        this.bc = bc;
        this.iteration = 0;

        this.viewers = viewers;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        automata = new Cell[sizeX][sizeX];

        if (bc == BoundaryCondition.CLOSED) {
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    if (i == 0 && j == 0) {
                        int[] neighboars = {0, 0, 0, 0, tab[i][j + 1], 0, tab[i + 1][j + 1]};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (i == 0 && j == sizeX - 1) {
                        int[] neighboars = {0, 0, 0, tab[i][j - 1], 0, tab[i + 1][j - 1], tab[i + 1][j], 0};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (i == sizeY - 1 && j == 0) {
                        int[] neighboars = {0, tab[i - 1][j], tab[i - 1][j + 1], 0, tab[i][j + 1], 0, 0, 0};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (i == sizeY - 1 && j == sizeX - 1) {
                        int[] neighboars = {tab[i - 1][j - 1], tab[i - 1][j], 0, tab[i][j - 1], 0, 0, 0, 0};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (j == 0) {
                        int[] neighboars = {0, tab[i - 1][j], tab[i - 1][j + 1], 0, tab[i][j + 1], 0, tab[i + 1][j], tab[i + 1][j + 1]};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (i == 0) {
                        int[] neighboars = {0, 0, 0, tab[i][j - 1], tab[i][j + 1], tab[i + 1][j - 1], tab[i + 1][j], tab[i + 1][j + 1]};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (j == sizeX - 1) {
                        int[] neighboars = {tab[i - 1][j - 1], tab[i - 1][j], 0, tab[i][j - 1], 0, tab[i + 1][j - 1], tab[i + 1][j], 0};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else if (i == sizeY - 1) {
                        int[] neighboars = {tab[i - 1][j - 1], tab[i - 1][j], tab[i - 1][j + 1], tab[i][j - 1], tab[i][j + 1], 0, 0, 0};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    } else {
                        int[] neighboars = {tab[i - 1][j - 1], tab[i - 1][j], tab[i - 1][j + 1], tab[i][j - 1], tab[i][j + 1], tab[i + 1][j - 1], tab[i + 1][j], tab[i + 1][j + 1]};
                        automata[i][j] = new Cell(tab[i][j], neighboars);
                    }
                }
            }
        } else if (bc == BoundaryCondition.PERIODIC) {
            int topIndex, bottomIndex, leftIndex, rightIndex;

            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    topIndex = i - 1;
                    bottomIndex = i + 1;
                    leftIndex = j - 1;
                    rightIndex = j + 1;

                    if (i == 0) {
                        topIndex = sizeY - 1;
                    }
                    if (i == (sizeY - 1)) {
                        bottomIndex = 0;
                    }
                    if (j == 0) {
                        leftIndex = sizeX - 1;
                    }
                    if (j == (sizeX - 1)) {
                        rightIndex = 0;
                    }

                    int[] neighboars = {
                        tab[topIndex][leftIndex], tab[topIndex][j], tab[topIndex][rightIndex],
                        tab[i][leftIndex], tab[i][rightIndex],
                        tab[bottomIndex][leftIndex], tab[bottomIndex][j], tab[bottomIndex][rightIndex]
                    };
                    automata[i][j] = new Cell(tab[i][j], neighboars);

                }
            }
        }
    }

    public void view() {

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                for (CellViewer cv : viewers) {
                    cv.view(automata[i][j], i, j, iteration);
                }
            }
        }
        iteration++;
    }

    public void nextIteration() {

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                automata[i][j].nextIteration();
            }
        }

        if (bc == BoundaryCondition.CLOSED) {
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    if (i == 0 && j == 0) {
                        int[] neighboars = {0, 0, 0, 0, automata[i][j + 1].getStatus(), 0, automata[i + 1][j + 1].getStatus()};
                        automata[i][j].update(neighboars);
                    } else if (i == 0 && j == sizeX - 1) {
                        int[] neighboars = {0, 0, 0, automata[i][j - 1].getStatus(), 0, automata[i + 1][j - 1].getStatus(), automata[i + 1][j].getStatus(), 0};
                        automata[i][j].update(neighboars);
                    } else if (i == sizeY - 1 && j == 0) {
                        int[] neighboars = {0, automata[i - 1][j].getStatus(), automata[i - 1][j + 1].getStatus(), 0, automata[i][j + 1].getStatus(), 0, 0, 0};
                        automata[i][j].update(neighboars);
                    } else if (i == sizeY - 1 && j == sizeX - 1) {
                        int[] neighboars = {automata[i - 1][j - 1].getStatus(), automata[i - 1][j].getStatus(), 0, automata[i][j - 1].getStatus(), 0, 0, 0, 0};
                        automata[i][j].update(neighboars);
                    } else if (j == 0) {
                        int[] neighboars = {0, automata[i - 1][j].getStatus(), automata[i - 1][j + 1].getStatus(), 0, automata[i][j + 1].getStatus(), 0, automata[i + 1][j].getStatus(), automata[i + 1][j + 1].getStatus()};
                        automata[i][j].update(neighboars);
                    } else if (i == 0) {
                        int[] neighboars = {0, 0, 0, automata[i][j - 1].getStatus(), automata[i][j + 1].getStatus(), automata[i + 1][j - 1].getStatus(), automata[i + 1][j].getStatus(), automata[i + 1][j + 1].getStatus()};
                        automata[i][j].update(neighboars);
                    } else if (j == sizeX - 1) {
                        int[] neighboars = {automata[i - 1][j - 1].getStatus(), automata[i - 1][j].getStatus(), 0, automata[i][j - 1].getStatus(), 0, automata[i + 1][j - 1].getStatus(), automata[i + 1][j].getStatus(), 0};
                        automata[i][j].update(neighboars);
                    } else if (i == sizeY - 1) {
                        int[] neighboars = {automata[i - 1][j - 1].getStatus(), automata[i - 1][j].getStatus(), automata[i - 1][j + 1].getStatus(), automata[i][j - 1].getStatus(), automata[i][j + 1].getStatus(), 0, 0, 0};
                        automata[i][j].update(neighboars);
                    } else {
                        int[] neighboars = {automata[i - 1][j - 1].getStatus(), automata[i - 1][j].getStatus(), automata[i - 1][j + 1].getStatus(), automata[i][j - 1].getStatus(), automata[i][j + 1].getStatus(), automata[i + 1][j - 1].getStatus(), automata[i + 1][j].getStatus(), automata[i + 1][j + 1].getStatus()};
                        automata[i][j].update(neighboars);
                    }
                }
            }
        } else if (bc == BoundaryCondition.PERIODIC) {
            int topIndex, bottomIndex, leftIndex, rightIndex;

            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    topIndex = i - 1;
                    bottomIndex = i + 1;
                    leftIndex = j - 1;
                    rightIndex = j + 1;

                    if (i == 0) {
                        topIndex = sizeY - 1;
                    }
                    if (i == (sizeY - 1)) {
                        bottomIndex = 0;
                    }
                    if (j == 0) {
                        leftIndex = sizeX - 1;
                    }
                    if (j == (sizeX - 1)) {
                        rightIndex = 0;
                    }

                    int[] neighboars = {
                        automata[topIndex][leftIndex].getStatus(), automata[topIndex][j].getStatus(), automata[topIndex][rightIndex].getStatus(),
                        automata[i][leftIndex].getStatus(), automata[i][rightIndex].getStatus(),
                        automata[bottomIndex][leftIndex].getStatus(), automata[bottomIndex][j].getStatus(), automata[bottomIndex][rightIndex].getStatus()
                    };
                    automata[i][j].update(neighboars);

                }
            }
        }
    }
}
