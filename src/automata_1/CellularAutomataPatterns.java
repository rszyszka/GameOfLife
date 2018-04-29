/*
 * Copyright (C) 2018 Szysz
 */
package automata_1;

import java.util.Arrays;

/**
 *
 * @author Szysz
 */
public class CellularAutomataPatterns {

    private final int[][] oscilator, cannon, glider, unchangingStructure, inconstantStructure;

    public CellularAutomataPatterns(int sizeX, int sizeY) {
        this.oscilator = new int[sizeY][sizeX];
        this.cannon = new int[sizeY][sizeX];
        this.glider = new int[sizeY][sizeX];
        this.unchangingStructure = new int[sizeY][sizeX];
        this.inconstantStructure = new int[sizeY][sizeX];

        for (int i = 0; i < sizeY; i++) {
            Arrays.fill(oscilator[i], 0);
            Arrays.fill(cannon[i], 0);
            Arrays.fill(glider[i], 0);
            Arrays.fill(unchangingStructure[i], 0);
            Arrays.fill(inconstantStructure[i], 0);
        }

        oscilator[sizeY / 2][sizeX / 2] = 1;
        oscilator[sizeY / 2][sizeX / 2 + 1] = 1;
        oscilator[sizeY / 2][sizeX / 2 - 1] = 1;

        cannon[sizeY / 5 + 2][sizeX / 2 - 18] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 - 18] = 1;
        cannon[sizeY / 5 + 2][sizeX / 2 - 17] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 - 17] = 1;
        cannon[sizeY / 5][sizeX / 2 + 16] = 1;
        cannon[sizeY / 5 + 1][sizeX / 2 + 16] = 1;
        cannon[sizeY / 5][sizeX / 2 + 17] = 1;
        cannon[sizeY / 5 + 1][sizeX / 2 + 17] = 1;
        cannon[sizeY / 5 + 2][sizeX / 2 - 2] = 1;
        cannon[sizeY / 5 + 1][sizeX / 2 - 3] = 1;
        cannon[sizeY / 5][sizeX / 2 - 5] = 1;
        cannon[sizeY / 5][sizeX / 2 - 6] = 1;
        cannon[sizeY / 5 + 1][sizeX / 2 - 7] = 1;
        cannon[sizeY / 5 + 2][sizeX / 2 - 8] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 - 8] = 1;
        cannon[sizeY / 5 + 4][sizeX / 2 - 8] = 1;
        cannon[sizeY / 5 + 5][sizeX / 2 - 7] = 1;
        cannon[sizeY / 5 + 6][sizeX / 2 - 6] = 1;
        cannon[sizeY / 5 + 6][sizeX / 2 - 5] = 1;
        cannon[sizeY / 5 + 5][sizeX / 2 - 3] = 1;
        cannon[sizeY / 5 + 4][sizeX / 2 - 2] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 - 2] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 - 4] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 - 1] = 1;
        cannon[sizeY / 5 + 2][sizeX / 2 + 2] = 1;
        cannon[sizeY / 5 + 1][sizeX / 2 + 2] = 1;
        cannon[sizeY / 5][sizeX / 2 + 2] = 1;
        cannon[sizeY / 5][sizeX / 2 + 3] = 1;
        cannon[sizeY / 5 + 1][sizeX / 2 + 3] = 1;
        cannon[sizeY / 5 + 2][sizeX / 2 + 3] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 + 4] = 1;
        cannon[sizeY / 5 + 3][sizeX / 2 + 6] = 1;
        cannon[sizeY / 5 + 4][sizeX / 2 + 6] = 1;
        cannon[sizeY / 5 - 1][sizeX / 2 + 6] = 1;
        cannon[sizeY / 5 - 2][sizeX / 2 + 6] = 1;
        cannon[sizeY / 5 - 1][sizeX / 2 + 4] = 1;

        glider[sizeY / 2][sizeX / 2] = 1;
        glider[sizeY / 2][sizeX / 2 + 1] = 1;
        glider[sizeY / 2][sizeX / 2 + 2] = 1;
        glider[sizeY / 2 + 1][sizeX / 2] = 1;
        glider[sizeY / 2 + 2][sizeX / 2 + 1] = 1;

        unchangingStructure[sizeY / 2][sizeX / 2 + 3] = 1;
        unchangingStructure[sizeY / 2 - 1][sizeX / 2 + 1] = 1;
        unchangingStructure[sizeY / 2 - 1][sizeX / 2 + 2] = 1;
        unchangingStructure[sizeY / 2 + 1][sizeX / 2 + 1] = 1;
        unchangingStructure[sizeY / 2 + 1][sizeX / 2 + 2] = 1;
    }

    public int[][] getOscilator() {
        return oscilator;
    }

    public int[][] getCannon() {
        return cannon;
    }

    public int[][] getGlider() {
        return glider;
    }

    public int[][] getUnchangingStructure() {
        return unchangingStructure;
    }

    public int[][] getInconstantStructure() {
        return inconstantStructure;
    }

}
