/*
 * Copyright (C) 2018 Szysz
 */
package automata_1;

/**
 *
 * @author Szysz
 */
public class Cell {

    private int status;
    private int aliveNeighboars;

    public Cell(int status, int[] neighboars) {
        this.status = status;

        aliveNeighboars = 0;

        for (int i = 0; i < neighboars.length; i++) {
            if (neighboars[i] == 1) {
                aliveNeighboars++;
            }
        }
    }

    public void nextIteration() {

        if (this.status == 0 && aliveNeighboars == 3) {
            this.status = 1;
        } else if (aliveNeighboars < 2 || aliveNeighboars > 3) {
            this.status = 0;
        }

    }

    public void update(Cell[] neighboars) {
        this.aliveNeighboars = 0;
        for (int i = 0; i < neighboars.length; i++) {
            if (neighboars[i].getStatus() == 1) {
                this.aliveNeighboars++;
            }
        }
    }

    public void update(int[] neighboars) {
        this.aliveNeighboars = 0;
        for (int i = 0; i < neighboars.length; i++) {
            if (neighboars[i] == 1) {
                this.aliveNeighboars++;
            }
        }
    }

    public int getBit(int n, int k) {
        return (n >> k) & 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int stan) {
        this.status = stan;
    }

    public int getAliveNeighboars() {
        return aliveNeighboars;
    }

    public void setAliveNeighboars(int aliveNeighboars) {
        this.aliveNeighboars = aliveNeighboars;
    }

}
