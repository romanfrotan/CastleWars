package helperMethods;

import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave {

    public static BufferedImage getSpriteAtlas() {

        BufferedImage img =null;


        InputStream is= LoadSave.class.getClassLoader().getResourceAsStream("spriteatlas.png");
        try {
            img = ImageIO.read(is);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    //make a text file to save and read

    public static void CreateFile(){
        File txtfile = new File("res/testTextFile.txt");

    try{
        txtfile.createNewFile();
    }catch (IOException e){
        e.printStackTrace();
    }

    }

    // creating new level with default values

    public static void CreateLevel (String name,int[] idArr){
        File newLevel = new File("res/"+name+".txt");

        if(newLevel.exists()){
            System.out.println("File- "+name+"already exists");
            return;
        }else {
            try {
                newLevel.createNewFile();
        }catch (IOException e){
                e.printStackTrace();
            }
            WriteToFile(newLevel,idArr);
        }
    }

    private static void WriteToFile(File f, int[] idArr){

        try{
            PrintWriter pw = new PrintWriter(f);
            for(Integer i:idArr) {
                pw.println(i);//prints text to file
            }
            pw.close();

        } catch(FileNotFoundException e ){
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> ReadFromFile(File file) {
        ArrayList<Integer> list =new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                list.add(Integer.valueOf(sc.nextLine())); //reads numbers from txt file then adds to array.
            }
            sc.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return list;
   }

   public static int[][] GetLevelData(String name) {
       File levelFile = new File("res/" + name + ".txt");

       if (levelFile.exists()) {
           ArrayList<Integer> list = ReadFromFile(levelFile);
           //create the helper method in helperMethods to turn 1d array to 2d array.
           return Utilz.ArrayListConversion(list,20,20);
       } else {
           System.out.println("Save File- " + name + " is not found.");
           return null;
       }

   }

   public static void SaveLevel(String name,int[][] idArray){
       File levelFile = new File("res/" + name + ".txt");

       if(levelFile.exists()) {
          WriteToFile(levelFile,Utilz.TwoDArraytoOneDArray(idArray));
       }else {
           System.out.println("File- "+name+"doest not exist");
       }

   }

}
