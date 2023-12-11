package helperMethods;

import java.util.ArrayList;

public class Utilities {


    public static int[][] ArrayListConversion(ArrayList<Integer> list,int ySize,int xSize){
        int[][] newArray=new int[ySize][xSize];

        for(int i=0;i < newArray.length;i++){
            for(int j = 0; j<newArray[i].length;j++){
                int index=i*ySize+j;
                newArray[i][j]=list.get(index);
            }
        }
        return newArray;

    }

    public static int[] TwoDArraytoOneDArray(int [][] twoDArray){
        int[] oneDArray= new int[twoDArray.length*twoDArray.length];

        for(int i=0;i < twoDArray.length;i++){
            for(int j = 0; j<twoDArray[i].length;j++){
                int index=i * twoDArray.length+j;
                oneDArray[index]=twoDArray[i][j];
            }
        }
        return oneDArray;
    }

    public static int getHypoDistance(float x1,float y1,float x2,float y2){
        float xDiff =Math.abs(x1-x2);
        float yDiff =Math.abs(y1-y2);

        return (int)Math.hypot(xDiff,yDiff);
    }



}
