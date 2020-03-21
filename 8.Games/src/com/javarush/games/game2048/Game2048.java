package com.javarush.games.game2048;
import com.javarush.engine.cell.*;


public class Game2048 extends Game  {
    private static final int SIDE = 4;
    private int[][] gameField = new int [SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score;
    //boolean isChange = false;

    private int getMaxTileValue ()  {
        int max = 0;
        for(int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++)   {
                if( gameField[j][i] > max)
                    max = gameField[j][i];
            }
        }
        return max;
    }

    private void rotateClockwise () {
        int[][] secondGameField = new int [SIDE][SIDE];
        for(int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++)   {
                secondGameField[i][j] = gameField[gameField.length - j - 1][i];
            }
        }
        for(int i = 0; i < SIDE; i++) {
            System.arraycopy(secondGameField[i], 0, gameField[i], 0, SIDE);
        }
    }

    private void moveLeft ()    {
        boolean compress1 = false,compress2 = false, merge = false;
        for (int[] ints : gameField) {
            if(compress1||compress2||merge) {
                compressRow(ints);
                mergeRow(ints);
                compressRow(ints);
            }
            else    {
                compress1 = compressRow(ints);
                merge = mergeRow(ints);
                compress2 = compressRow(ints);
            }
        }
        if (compress1 || compress2 || merge)
            createNewNumber();
    /*
        boolean compress1 = false,compress2 = false, merge = false;
        for(int i = 0; i < gameField.length; i++)   {
            int[] row = new int [gameField[i].length];
            for(int j = 0; j < gameField[i].length; j++)   {
                row[j] = gameField[i][j];
            }
            if(compress1||compress2||merge) {
                compressRow(row);
                mergeRow(row);
                compressRow(row);
            }
            else    {
                compress1 = compressRow(row);
                merge = mergeRow(row);
                compress2 = compressRow(row);
            }

            for(int j = 0; j < gameField[i].length; j++)   {
                gameField[i][j] = row[j];
            }
        }
        if(compress1||compress2||merge)
            createNewNumber();*/
    }

    private void moveRight()    {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp()   {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }
    /*
    private boolean mergeRow(int[] row) {
        boolean mergeRow = false;

        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0 && row[i]==row[i+1]) {
                row[i] = row[i] + row[i+1];
                row[i+1] = 0;
                mergeRow = true;
            }
        }
        return mergeRow;
    }*/
    private boolean mergeRow(int[] row){
        boolean tmpB = false;
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] == row[i+1]&&(row[i]!=0)) {
                row[i] *= 2;
                score += row[i];
                setScore(score);
                row[i+1] = 0;
                i++;
                tmpB = true;
            }
        }
        return tmpB;
    }

    private boolean compressRow(int[] row) {
        boolean compressRow = false;
        int[] newRow = new int[row.length];
        int x = 0;

        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                newRow[x] = row[i];
                if (i != x) {
                    compressRow = true;
                }
                x++;
            }
        }
        if(compressRow) {
            while(x<row.length)  {
                newRow[x]=0;
                x++;
            }
        }
        for (int i = 0; i < row.length; i++) {
            row[i] = newRow[i];
        }
        return compressRow;
    }

    private Color getColorByValue(int value)   {
        switch(value)   {
            case 0: return Color.WHITE;
            case 2: return Color.YELLOW;
            case 4: return Color.BLUE;
            case 8: return Color.GREEN;
            case 16: return Color.ORANGE;
            case 32: return Color.AQUAMARINE;
            case 64: return Color.LIGHTBLUE;
            case 128: return Color.LIME;
            case 256: return Color.HOTPINK;
            case 512: return Color.KHAKI;
            case 1024: return Color.DARKVIOLET;
            case 2048: return Color.NAVY;
            default: return Color.BLACK;
        }
    }

    private void setCellColoredNumber (int x, int y, int val) {
        if (val != 0)
            setCellValueEx(x,y,getColorByValue(val),Integer.toString(val));
        else
            setCellValueEx(x,y,getColorByValue(val),"");
    }

    private void win()   {
        showMessageDialog(Color.DARKGREEN,  "Победка",
                Color.DEEPPINK, 50);
        isGameStopped = true;
    }

    private void gameOver ()    {
        showMessageDialog(Color.DARKGREEN,  "Неть" ,
                Color.DEEPPINK, 50);
        isGameStopped = true;
    }

    private boolean canUserMove ()  {

        for (int x=0; x<SIDE; x++){
            for(int y=0; y<SIDE; y++){

                if (gameField[x][y] == 0){
                    return true;
                }

                if(x < SIDE-1){
                    if (gameField[x][y] == gameField[x + 1][y]){
                        return true;
                    }
                }

                if(y < SIDE-1){
                    if (gameField[x][y] ==gameField[x][y + 1]){
                        return true;
                    }
                }

            }
        }
        return false;
    }
        /*
        boolean hasZero = false;
        boolean hasMove = false;
        boolean answer = true;

        for(int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++)   {
                if( gameField[j][i] == 0)   {
                    hasZero = true;
                    break;
                }
            }
        }
        if (!hasZero) {
            for (int i = 0; i < SIDE - 1; i++) {
                for (int j = 0; j < SIDE - 1; j++) {
                    if (gameField[i][j] == gameField[i + 1][j] ||
                            gameField[i][j] == gameField[i][j + 1])
                        hasMove = true;
                }
            }
        }
        if (hasZero || hasMove)
            answer = false;
        return answer;
    }*/


    private void createNewNumber()  {
        if(getMaxTileValue() == 2048)   win();
        for(;;){
            int randomX = getRandomNumber(SIDE);
            int randomY = getRandomNumber(SIDE);
            if(gameField[randomX][randomY]==0){
                int num = getRandomNumber(10);
                if (num == 9)   {
                    gameField[randomX][randomY] = 4;
                    break;
                }
                else    {
                    gameField[randomX][randomY] = 2;
                    //gameField[randomX][randomY] = 1024;
                    break;
                }
            }
        }
    }

    public void initialize ()   {
        setScreenSize(SIDE,SIDE);
        createGame();
        drawScene();
        
    }
    
    private void createGame ()  {
        gameField = new int [SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();
    }
    
    private void drawScene ()    {
        for(int i = 0;i<SIDE;i++) {
            for (int j = 0 ;j < SIDE ; j++ )   {
                setCellColoredNumber(i,j,gameField[j][i]);
            }
        }
    }

    public void onKeyPress(Key key) {
        if(isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
            }
            return;
        }
        if(!(canUserMove())) {
            gameOver();
            return;
        }
        if (key == Key.LEFT) {
            moveLeft();
            //if(isChange)
            //createNewNumber();
            drawScene();
        }
        if (key == Key.RIGHT) {
            moveRight();
            drawScene();
        }
        if (key == Key.UP) {
            moveUp();
            drawScene();
        }
        if (key == Key.DOWN) {
            moveDown();
            drawScene();
        }
    }
}
















