/*
 * Copyright (C) 2018 Szysz
 */
package automata_1;

import java.util.Arrays;

/**
 *
 * @author Szysz
 */
public class CellularAutomataPattern {

    private int[][] pattern;

    public CellularAutomataPattern(int sizeX, int sizeY, String pattern) {
        this.pattern = new int[sizeY][sizeX];

        for (int i = 0; i < sizeY; i++) {
            Arrays.fill(this.pattern[i], 0);
        }

        switch (pattern) {
            case "Oscylator":
                this.pattern[sizeY / 2][sizeX / 2] = 1;
                this.pattern[sizeY / 2][sizeX / 2 + 1] = 1;
                this.pattern[sizeY / 2][sizeX / 2 - 1] = 1;
                break;
            case "Działo":
                this.pattern[sizeY / 5 + 2][sizeX / 2 - 18] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 - 18] = 1;
                this.pattern[sizeY / 5 + 2][sizeX / 2 - 17] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 - 17] = 1;
                this.pattern[sizeY / 5][sizeX / 2 + 16] = 1;
                this.pattern[sizeY / 5 + 1][sizeX / 2 + 16] = 1;
                this.pattern[sizeY / 5][sizeX / 2 + 17] = 1;
                this.pattern[sizeY / 5 + 1][sizeX / 2 + 17] = 1;
                this.pattern[sizeY / 5 + 2][sizeX / 2 - 2] = 1;
                this.pattern[sizeY / 5 + 1][sizeX / 2 - 3] = 1;
                this.pattern[sizeY / 5][sizeX / 2 - 5] = 1;
                this.pattern[sizeY / 5][sizeX / 2 - 6] = 1;
                this.pattern[sizeY / 5 + 1][sizeX / 2 - 7] = 1;
                this.pattern[sizeY / 5 + 2][sizeX / 2 - 8] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 - 8] = 1;
                this.pattern[sizeY / 5 + 4][sizeX / 2 - 8] = 1;
                this.pattern[sizeY / 5 + 5][sizeX / 2 - 7] = 1;
                this.pattern[sizeY / 5 + 6][sizeX / 2 - 6] = 1;
                this.pattern[sizeY / 5 + 6][sizeX / 2 - 5] = 1;
                this.pattern[sizeY / 5 + 5][sizeX / 2 - 3] = 1;
                this.pattern[sizeY / 5 + 4][sizeX / 2 - 2] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 - 2] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 - 4] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 - 1] = 1;
                this.pattern[sizeY / 5 + 2][sizeX / 2 + 2] = 1;
                this.pattern[sizeY / 5 + 1][sizeX / 2 + 2] = 1;
                this.pattern[sizeY / 5][sizeX / 2 + 2] = 1;
                this.pattern[sizeY / 5][sizeX / 2 + 3] = 1;
                this.pattern[sizeY / 5 + 1][sizeX / 2 + 3] = 1;
                this.pattern[sizeY / 5 + 2][sizeX / 2 + 3] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 + 4] = 1;
                this.pattern[sizeY / 5 + 3][sizeX / 2 + 6] = 1;
                this.pattern[sizeY / 5 + 4][sizeX / 2 + 6] = 1;
                this.pattern[sizeY / 5 - 1][sizeX / 2 + 6] = 1;
                this.pattern[sizeY / 5 - 2][sizeX / 2 + 6] = 1;
                this.pattern[sizeY / 5 - 1][sizeX / 2 + 4] = 1;
                break;
            case "Glider":
                this.pattern[sizeY / 2][sizeX / 2] = 1;
                this.pattern[sizeY / 2][sizeX / 2 + 1] = 1;
                this.pattern[sizeY / 2][sizeX / 2 + 2] = 1;
                this.pattern[sizeY / 2 + 1][sizeX / 2] = 1;
                this.pattern[sizeY / 2 + 2][sizeX / 2 + 1] = 1;
                break;
            case "Stały":
                this.pattern[sizeY / 2][sizeX / 2] = 1;
                this.pattern[sizeY / 2][sizeX / 2 + 3] = 1;
                this.pattern[sizeY / 2 - 1][sizeX / 2 + 1] = 1;
                this.pattern[sizeY / 2 - 1][sizeX / 2 + 2] = 1;
                this.pattern[sizeY / 2 + 1][sizeX / 2 + 1] = 1;
                this.pattern[sizeY / 2 + 1][sizeX / 2 + 2] = 1;
                break;
            case "Losowa":
                for(int i = 0 ; i < sizeY; i ++)
                    for(int j = 0 ;j < sizeX; j ++)
                        this.pattern[i][j] = (int) Math.round(Math.random());
                break;
            default:
                break;
        }

    }

    public int[][] getPattern() {
        return pattern;
    }

    public void setCell(int x, int y) {
        if (this.pattern[y][x] == 1) {
            this.pattern[y][x] = 0;
        } else {
            this.pattern[y][x] = 1;
        }
    }
}
