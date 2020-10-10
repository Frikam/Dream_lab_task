import java.util.LinkedList;

public class GameOfLife {
    private final int width = 35;
    private final int height = 20;
    private final int[][] map = new int[height][width];

    /**
     * Функция, которая выполняет итерацию для каждой клетки таблицы
     */
    private void makeIteration() {
        LinkedList<int[]> neighbourCoordinates = new LinkedList<>();
        printMap();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int numberOfFilledNeighbourCells = countNumberOfFilledNeighbourCells(j, i);
                if ((numberOfFilledNeighbourCells == 3 && map[i][j] == 0) ||
                        ((numberOfFilledNeighbourCells < 2 || numberOfFilledNeighbourCells > 3)
                                && map[i][j] == 1)) {
                    neighbourCoordinates.add(new int[]{i, j});
                }
            }
        }

        for(int[] coordinate: neighbourCoordinates) {
            map[coordinate[0]][coordinate[1]] = (map[coordinate[0]][coordinate[1]] + 1) % 2;
        }

        makeIteration();
    }

    /**
     * Функция считает количество живых соседних клеток
     * */
    private int countNumberOfFilledNeighbourCells(int x, int y) {
        LinkedList<int[]> neighbourCoordinates = getNeighbourCoordinates(x, y);
        int numberOfFilledNeighbourCells = 0;

        for (int[] coordinate : neighbourCoordinates) {
            numberOfFilledNeighbourCells += map[coordinate[1]][coordinate[0]];
        }

        return numberOfFilledNeighbourCells;
    }

    public void startGame() {
        fillMap();
        makeIteration();
    }

    /**
     * Функция возвращает координаты соседей
     * */
    private LinkedList<int[]> getNeighbourCoordinates(int x, int y) {
        LinkedList<int[]> neighbourCoordinates = new LinkedList<>();
        if (y + 1 != height) {
            neighbourCoordinates.add(new int[]{x, y + 1});
        }
        if (y != 0) {
            neighbourCoordinates.add(new int[]{x, y - 1});
        }
        if (x + 1 != width) {
            neighbourCoordinates.add(new int[]{x + 1, y});
        }
        if (x != 0) {
            neighbourCoordinates.add(new int[]{x - 1, y});
        }
        if (x + 1 != width && y + 1 != height) {
            neighbourCoordinates.add(new int[]{x + 1, y + 1});
        }
        if (x != 0 && y + 1 != height) {
            neighbourCoordinates.add(new int[]{x - 1, y + 1});
        }
        if (x + 1 != width && y != 0) {
            neighbourCoordinates.add(new int[]{x + 1, y - 1});
        }
        if (x != 0 && y != 0) {
            neighbourCoordinates.add(new int[]{x - 1, y - 1});
        }
        return neighbourCoordinates;
    }

    /**
     * Функция заполняет таблицу стартовыми значениями
     */
    private void fillMap() {
        map[10][10] = 1;
        map[10][11] = 1;
        map[10][12] = 1;
        map[9][11] = 1;
        map[10][15] = 1;
        map[10][16] = 1;
        map[10][17] = 1;
        map[9][16] = 1;
        map[10][20] = 1;
        map[10][21] = 1;
        map[10][22] = 1;
        map[9][21] = 1;
    }

    private void printLine() {
        System.out.print(' ');
        for (int k = 0; k < width; k++) {
            System.out.print('_');
        }

        System.out.println();
    }

    private void printMap() {
        printLine();
        for (int i = 0; i < height; i++) {
            System.out.print('|');
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1) {
                    System.out.print("*");
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println('|');
        }
        printLine();
    }
}
