/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;
import java.io.File;  

/**
 *
 * @author LENOVO-PC
 */
public class searching {
    public static File[] musicplayer = new File[50];
    public static String[] musicname = new String[50];
    public static int i = 0;
    

     static void RecursivePrint(File[] arr,int index,int level)  
     {
         if(index == arr.length) 
             return; 
         //for (int i = 0; i < level; i++) 
         //  System.out.print("\t");
         if(arr[index].isFile()&&((arr[index].getName().endsWith(".mp3"))||(arr[index].getName().endsWith(".mp4"))))
          //System.out.println(arr[index].getName()); 
         { musicplayer[i]=arr[index];
          musicname[i]=arr[index].getName();
          i++;
                  } 
         
         else if(arr[index].isDirectory()) 
         { 
             //System.out.println("[" + arr[index].getName() + "]");
             RecursivePrint(arr[index].listFiles(), 0, level + 1); 
         } 
         RecursivePrint(arr,++index, level); 
    }  
}