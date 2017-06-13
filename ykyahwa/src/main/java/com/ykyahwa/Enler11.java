package com.ykyahwa;

public class Enler11 {


    public static void main(String[] args) {

        long st = System.currentTimeMillis();

        int max = 0;

        int size = 20;

        for (int i =0; i < size ; i++) {
            for (int y =0; y < size ; y++) {
                if (i < size -3 && y <size -3) {
                    int right = Data.numberTable[i][y] * Data.numberTable[i][y+1] * Data.numberTable[i][y+2] * Data.numberTable[i][y+3];
                    int down = Data.numberTable[i][y] * Data.numberTable[i+1][y] * Data.numberTable[i+2][y] * Data.numberTable[i+3][y];
                    int rightCross = Data.numberTable[i][y] * Data.numberTable[i+1][y+1] * Data.numberTable[i+2][y+2] * Data.numberTable[i+3][y+3];

                    if (max < right) max = right;
                    if (max < down) max = down;
                    if (max < rightCross) max = rightCross;
                }

                if (i > 2 && y < size-3) {
                    int leftCross = Data.numberTable[i][y] * Data.numberTable[i-1][y+1] * Data.numberTable[i-2][y+2] * Data.numberTable[i-3][y+3];
                    if (max < leftCross) max = leftCross;
                }
            }
        }

        long et = System.currentTimeMillis();
        System.out.println((et - st)+" ms");
        System.out.println(max);
    }

}
