/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;


import java.util.ArrayList;


/**
 *
 * @author daree
 */
public abstract class Database<T> {
    
   
    
  public abstract void saveToFile(ArrayList<T> list);
  public abstract ArrayList<T> loadFromFile();
    
  
    
}
