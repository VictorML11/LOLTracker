package me.torciv.loltracker;

import java.util.ArrayList;
import java.util.Arrays;

public enum Side {

    BLUE(427),
    RED(1001);

    private int xStart;
    private ArrayList<Integer> points;

    Side(int xStart) {
        this.xStart = xStart;
        points = new ArrayList<>(Arrays.asList(320,348,396,324,472,500,548,576,624,652));
    }


    public boolean isPointInsideX(int x){
        return xStart <= x && x <= xStart+25;
    }

    public boolean isPointInsideY(int yloc, int y){
        return yloc <= y && y <= yloc + 25;
    }

    public int isSummoner(int x, int y){
        if(this.isPointInsideX(x)){
            for (int i = 0, pointsSize = points.size(); i < pointsSize; i++) {
                if (this.isPointInsideY(points.get(i), y)) {
                    return i+1;
                }
            }
        }

        return -1;

    }
}
