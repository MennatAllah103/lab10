/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author daree
 */
public class FriendDatabase {
    
    private static FriendDatabase friendsDB=null;
    ArrayList<Friend> friends=new ArrayList<>();

    private FriendDatabase() {
        
        friends = loadFile();
    }
    
    
    public static FriendDatabase getinstance()
    {
        if(friendsDB==null)
            friendsDB=new FriendDatabase();
        
        return friendsDB;
        
    }
    
   
    public ArrayList<Friend> loadFile(){ 
           try {
          String  json = new String(Files.readAllBytes(Paths.get("friends.json")));
            JSONArray friendsArray = new JSONArray(json);
             for (int i = 0; i < friendsArray.length(); i++)
    {
        JSONObject friendsJson = friendsArray.getJSONObject(i);
                String Userid1  = friendsJson.getString("Userid1");
                String Userid2  = friendsJson.getString("Userid2");
                friends.add(new Friend(Userid1,Userid2));
               
    }
             
          
             
           } 
           
           catch (IOException ex) {
            Logger.getLogger(FriendDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
 
     
         return friends;

}
 
    public void saveFile(ArrayList<Friend> friends)
    {
        JSONArray friendsArray = new JSONArray();
        for(Friend f : friends)
        {
            JSONObject j = new JSONObject();
            j.put("Userid1", f.getUserid1());
            j.put("Userid2", f.getUserid2());
            friendsArray.put(j);
            
        }
        
         
        try {
          FileWriter  file = new FileWriter("friends.json");
          file.write(friendsArray.toString(4));
          file.close();
        } catch (IOException ex) {
            Logger.getLogger(FriendDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
    }

    public ArrayList<Friend> getALLFriends() {
        
      
        return friends;
    }
    
  
    
}