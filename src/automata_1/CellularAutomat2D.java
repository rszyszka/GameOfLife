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

    private Cell automata[][];
    private final int sizeX, sizeY;

    private final BoundaryCondition bc;

    private int iteration;

    private final CellViewer viewers[];

    public CellularAutomat2D(int[][] tab, int sizeX, int sizeY, CellViewer viewers[], BoundaryCondition bc) {

        this.bc = bc;
        this.iteration = 0;

        this.viewers = viewers;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        automata = new Cell[sizeY][sizeX];

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

    public Cell[][] getAutomata() {
        return automata;
    }

    public void setAutomata(int posX, int posY) {
        if (bc == BoundaryCondition.CLOSED) {
            if (this.automata[posY][posX].getStatus() == 1) {
                this.automata[posY][posX].setStatus(0);
                try {
                    this.automata[posY - 1][posX - 1].setAliveNeighboars(this.automata[posY - 1][posX - 1].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY - 1][posX].setAliveNeighboars(this.automata[posY - 1][posX].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY - 1][posX + 1].setAliveNeighboars(this.automata[posY - 1][posX + 1].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY][posX - 1].setAliveNeighboars(this.automata[posY][posX - 1].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY][posX + 1].setAliveNeighboars(this.automata[posY][posX + 1].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY + 1][posX - 1].setAliveNeighboars(this.automata[posY + 1][posX - 1].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY + 1][posX].setAliveNeighboars(this.automata[posY + 1][posX].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY + 1][posX + 1].setAliveNeighboars(this.automata[posY + 1][posX + 1].getAliveNeighboars() - 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }

            } else {
                this.automata[posY][posX].setStatus(1);
                try {
                    this.automata[posY - 1][posX - 1].setAliveNeighboars(this.automata[posY - 1][posX - 1].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY - 1][posX].setAliveNeighboars(this.automata[posY - 1][posX].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY - 1][posX + 1].setAliveNeighboars(this.automata[posY - 1][posX + 1].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY][posX - 1].setAliveNeighboars(this.automata[posY][posX - 1].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY][posX + 1].setAliveNeighboars(this.automata[posY][posX + 1].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY + 1][posX - 1].setAliveNeighboars(this.automata[posY + 1][posX - 1].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY + 1][posX].setAliveNeighboars(this.automata[posY + 1][posX].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
                try {
                    this.automata[posY + 1][posX + 1].setAliveNeighboars(this.automata[posY + 1][posX + 1].getAliveNeighboars() + 1);
                } catch (ArrayIndexOutOfBoundsException ex) {
                }
            }
        } else {
            if (this.automata[posY][posX].getStatus() == 1) {
                this.automata[posY][posX].setStatus(0);

                int topIndex = posY + 1;
                int botIndex = posY - 1;
                int leftIndex = posX - 1;
                int rightIndex = posX + 1;

                if (posX == 0) {
                    leftIndex = sizeX - 1;
                } else if (posX == sizeX - 1) {
                    rightIndex = 0;
                }
                if (posY == 0) {
                    topIndex = sizeY - 1;
                } else if (posY == sizeY - 1) {
                    topIndex = 0;
                }

                this.automata[topIndex][leftIndex].setAliveNeighboars(this.automata[topIndex][leftIndex].getAliveNeighboars() - 1);
                this.automata[topIndex][posX].setAliveNeighboars(this.automata[topIndex][posX].getAliveNeighboars() - 1);
                this.automata[topIndex][rightIndex].setAliveNeighboars(this.automata[topIndex][rightIndex].getAliveNeighboars() - 1);
                this.automata[posY][leftIndex].setAliveNeighboars(this.automata[posY][leftIndex].getAliveNeighboars() - 1);
                this.automata[posY][rightIndex].setAliveNeighboars(this.automata[posY][rightIndex].getAliveNeighboars() - 1);
                this.automata[botIndex][leftIndex].setAliveNeighboars(this.automata[botIndex][leftIndex].getAliveNeighboars() - 1);
                this.automata[botIndex][posX].setAliveNeighboars(this.automata[botIndex][posX].getAliveNeighboars() - 1);
                this.automata[botIndex][rightIndex].setAliveNeighboars(this.automata[botIndex][rightIndex].getAliveNeighboars() - 1);

            } else {
                this.automata[posY][posX].setStatus(1);

                int topIndex = posY + 1;
                int botIndex = posY - 1;
                int leftIndex = posX - 1;
                int rightIndex = posX + 1;

                if (posX == 0) {
                    leftIndex = sizeX - 1;
                } else if (posX == sizeX - 1) {
                    rightIndex = 0;
                }
                if (posY == 0) {
                    botIndex = sizeY - 1;
                } else if (posY == sizeY - 1) {
                    topIndex = 0;
                }

                this.automata[topIndex][leftIndex].setAliveNeighboars(this.automata[topIndex][leftIndex].getAliveNeighboars() + 1);
                this.automata[topIndex][posX].setAliveNeighboars(this.automata[topIndex][posX].getAliveNeighboars() + 1);
                this.automata[topIndex][rightIndex].setAliveNeighboars(this.automata[topIndex][rightIndex].getAliveNeighboars() + 1);
                this.automata[posY][leftIndex].setAliveNeighboars(this.automata[posY][leftIndex].getAliveNeighboars() + 1);
                this.automata[posY][rightIndex].setAliveNeighboars(this.automata[posY][rightIndex].getAliveNeighboars() + 1);
                this.automata[botIndex][leftIndex].setAliveNeighboars(this.automata[botIndex][leftIndex].getAliveNeighboars() + 1);
                this.automata[botIndex][posX].setAliveNeighboars(this.automata[botIndex][posX].getAliveNeighboars() + 1);
                this.automata[botIndex][rightIndex].setAliveNeighboars(this.automata[botIndex][rightIndex].getAliveNeighboars() + 1);
            }
        }
    }
}
