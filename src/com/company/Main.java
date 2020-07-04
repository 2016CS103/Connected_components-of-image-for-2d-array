package com.company;
import java.util.*;


 public class Main {
    static class Pair {
        int x;
        int y;

        // Constructor
        public Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
    public static void Calculate_cc(int r, int c, int[][] arr) {
        int X_Min = 500, Y_Min = 500, X_Max = 0, Y_Max = 0;
        int cc_h = 0, cc_w = 0;
        Stack<Pair> STACK = new Stack<Pair>();
        int j = 0;
        ArrayList<Pair> points = new ArrayList<Pair>();
        int[] x_diff = {-1, 0, 1, 1, 1, 0, -1, -1};
        int[] y_diff = {1, 1, 1, 0, -1, -1, -1, 0};
        int result = 0;

        for (int i = 0; i < 8; i++) {
            try {
                int new_x = r;
                int new_y = c;
                new_x = new_x + x_diff[i];
                new_y = new_y + y_diff[i];
                result = arr[new_x][new_y];
                if (result == 0){
                    Pair p1 = new Pair(new_x, new_y);
                    STACK.push(p1);
                    /*arr[r][c] = 2;
                    Pair e = new Pair(r, c);
                    points.add(e);*/
                }
            } catch (Exception e) {
                continue;
            }
        }
        arr[r][c] = 2;
        Pair g = new Pair(r, c);
        points.add(g);

        while(!STACK.empty()){
            Pair p2 = STACK.pop();
            if(arr[p2.x][p2.y] != 2) {
                for (int i = 0; i < 8; i++) {
                    try {
                        int new_x = p2.x;
                        int new_y = p2.y;
                        new_x = new_x + x_diff[i];
                        new_y = new_y + y_diff[i];
                        result = arr[new_x][new_y];
                        if (result == 0) {
                            Pair p1 = new Pair(new_x, new_y);
                            STACK.push(p1);
                        }

                    } catch (Exception e) {

                        continue;
                    }
                }
                arr[p2.x][p2.y] = 2;
                Pair e = new Pair(p2.x, p2.y);
                points.add(e);
            }

        }

        for(Pair pair : points){
            if(X_Min > pair.x){
                X_Min = pair.x;
            }
            if(Y_Min > pair.y){
                Y_Min = pair.y;
            }
            if(X_Max < pair.x){
                X_Max = pair.x;
            }
            if(Y_Max < pair.y){
                Y_Max = pair.y;
            }
        }

        cc_h = (X_Max - X_Min) + 1;
        cc_w = (Y_Max - Y_Min) + 1;
        int[][] CC_Array = new int[cc_h][cc_w];
        for(int m = r; m < cc_h + r; m++){
            for(int k = c; k < cc_w + c; k++){
                CC_Array[m - X_Min][k - Y_Min] = arr[m][k];
            }
        }
        System.out.println("Connected_Component");
        for (int n = 0; n < CC_Array.length; n++) {
            for (int k = 0; k < CC_Array[n].length; k++) {
                System.out.print(CC_Array[n][k] + "\t");
            }
            System.out.println();
        }

    }


    public static void main(String[] args) {
	// write your code here
        /*int[][] array = new int[][]{
                new int[] { 0, 1, 1},
                new int[] { 1, 0, 1},
                new int[] { 1, 1, 1},
        };*/
        int[][] array = new int[][]{
        new int[] { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1 },
        new int[] { 1, 1, 1, 0, 1, 1, 0, 0, 0, 0},
        new int[] { 0, 0, 1, 1, 1, 1, 1, 1, 0, 0},
        new int[] { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
        new int[] { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},

        };
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    Calculate_cc(i, j, array);
                }
            }

        }
    }
}
