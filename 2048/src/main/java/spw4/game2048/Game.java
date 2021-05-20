package spw4.game2048;

import java.util.Random;

public class Game {
    private int[][] field;
    private int score;
    private int moves;
    private Random random;
    private boolean isOver;

    public Game() {
        random = new Random();
    }

    public int getScore() {
        return score;
    }

    public boolean isOver() {
        if (isWon())
            return true;
        else
            return isOver;
    }

    public boolean isWon() {
        for(int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (field [x][y] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Moves: ").append(moves).append("\tScore: ").append(getScore()).append("\n");
        for(int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (field[x][y] == 0)
                    sb.append(".\t");
                else
                    sb.append(field[x][y]).append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void initialize() {
        field = new int[][]{{0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0}};
        score = 0;
        moves = 0;
        isOver = false;

        try{
//            placeRandomTile();
//            placeRandomTile();
        } catch (Exception ignored) { } //ignored because calls will always succeed
    }

    private void placeRandomTile() throws NoFreeTileException{
        if (isOver())
            return;

        if (!hasEmptyField()) throw new NoFreeTileException();

        int x = random.nextInt(4);
        int y = random.nextInt(4);
        while (field[x][y] != 0) {
            x = random.nextInt(4);
            y = random.nextInt(4);
        }

        int poss = random.nextInt(10);
        if (poss < 9)
            field[x][y] = 2;
        else
            field[x][y] = 4;
    }

    private boolean hasEmptyField() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (field[x][y] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move(Direction direction) {
        boolean hasMoved = false;
        switch (direction) {
            case up: {
                for (int currentCellCol = 0; currentCellCol < 4; currentCellCol++) {
                    for (int currentCellRow = 0; currentCellRow < 4; currentCellRow++) {
                        for (int checkRow = currentCellRow + 1; checkRow < 4; checkRow++) {
                            if (field[currentCellRow][currentCellCol] == 0) {
                                if (field[checkRow][currentCellCol] != 0) {
                                    field[currentCellRow][currentCellCol] = field[checkRow][currentCellCol];
                                    field[checkRow][currentCellCol] = 0;
                                    hasMoved = true;
                                }
                            } else {
                                if (field[currentCellRow][currentCellCol] == field[checkRow][currentCellCol]) {
                                    field[currentCellRow][currentCellCol] = field[currentCellRow][currentCellCol] * 2;
                                    score+= field[currentCellRow][currentCellCol];
                                    field[checkRow][currentCellCol] = 0;
                                    hasMoved = true;
                                    break;
                                } else if (field[checkRow][currentCellCol] != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
            case down: {
                for (int currentCellCol = 3; currentCellCol >= 0; currentCellCol--) {
                    for (int currentCellRow = 3; currentCellRow >= 0; currentCellRow--) {
                        for (int checkRow = currentCellRow - 1; checkRow >= 0; checkRow--) {
                            if (field[currentCellRow][currentCellCol] == 0) {
                                if (field[checkRow][currentCellCol] != 0) {
                                    field[currentCellRow][currentCellCol] = field[checkRow][currentCellCol];
                                    field[checkRow][currentCellCol] = 0;
                                }
                            } else {
                                if (field[currentCellRow][currentCellCol] == field[checkRow][currentCellCol]) {
                                    field[currentCellRow][currentCellCol] = field[currentCellRow][currentCellCol] * 2;
                                    field[checkRow][currentCellCol] = 0;
                                    score+= field[currentCellRow][currentCellCol];
                                    break;
                                } else if (field[checkRow][currentCellCol] != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
            case left: {
                for (int currentCellRow = 0; currentCellRow < 4; currentCellRow++) {
                    for (int currentCellCol = 0; currentCellCol < 4; currentCellCol++) {
                        for (int checkCol = currentCellCol + 1; checkCol < 4; checkCol++) {
                            if (field[currentCellRow][currentCellCol] == 0) {
                                if (field[currentCellRow][checkCol] != 0) {
                                    field[currentCellRow][currentCellCol] = field[currentCellRow][checkCol];
                                    field[currentCellRow][checkCol] = 0;
                                }
                            } else {
                                if (field[currentCellRow][currentCellCol] == field[currentCellRow][checkCol]) {
                                    field[currentCellRow][currentCellCol] = field[currentCellRow][currentCellCol] * 2;
                                    score += field[currentCellRow][currentCellCol];
                                    field[currentCellRow][checkCol] = 0;
                                    break;
                                } else if (field[currentCellRow][checkCol] != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
            case right: {
                for (int currentCellRow = 3; currentCellRow >= 0; currentCellRow--) {
                    for (int currentCellCol = 3; currentCellCol >= 0; currentCellCol--) {
                        for (int checkCol = currentCellCol - 1; checkCol >= 0; checkCol--) {
                            if (field[currentCellRow][currentCellCol] == 0) {
                                if (field[currentCellRow][checkCol] != 0) {
                                    field[currentCellRow][currentCellCol] = field[currentCellRow][checkCol];
                                    field[currentCellRow][checkCol] = 0;
                                }
                            } else {
                                if (field[currentCellRow][currentCellCol] == field[currentCellRow][checkCol]) {
                                    field[currentCellRow][currentCellCol] = field[currentCellRow][currentCellCol] * 2;
                                    score += field[currentCellRow][currentCellCol];
                                    field[currentCellRow][checkCol] = 0;
                                    break;
                                } else if (field[currentCellRow][checkCol] != 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            }
        }

        if (hasMoved) {
            try{
                moves++;
                placeRandomTile();
            } catch (NoFreeTileException exception) {
                isOver = true;
            }
        }
    }

    private static class NoFreeTileException extends Exception { }
}
