package helperMethods;

import java.util.ArrayList;

public class Utilz {


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



}
