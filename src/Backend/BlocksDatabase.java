/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FriendManagementBackend;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author daree
 */
public class BlocksDatabase {
    
    
    private static BlocksDatabase BlocksDB=null;
    ArrayList<BlockedUsers> blocks = new ArrayList<>();

    private BlocksDatabase() {
        
        blocks=loadFile();
    }
    
       public static BlocksDatabase getinstance() {
        if (BlocksDB == null) {
            BlocksDB = new BlocksDatabase();
        }

        return BlocksDB;

    }
       
        public ArrayList<BlockedUsers> loadFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("blocks.json")));
            JSONArray blocksArray = new JSONArray(json);
            for (int i = 0; i < blocksArray.length(); i++) {
                JSONObject blockJson = blocksArray.getJSONObject(i);
                String Blocker = blockJson.getString("Blocker");
                String Blocked = blockJson.getString("Blocked");
                blocks.add(new BlockedUsers(Blocker, Blocked));

            }

           
        } catch (IOException ex) {
            Logger.getLogger(RequestsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return blocks;
    }

        
         public void saveFile(ArrayList<BlockedUsers> blocks) {
        JSONArray blocksArray = new JSONArray();
        for (BlockedUsers b : blocks) {
            JSONObject j = new JSONObject();
            j.put("Blocker", b.Blocker);
            j.put("Blocked", b.Blocked);
            blocksArray.put(j);

        }

        try {
            FileWriter file = new FileWriter("blocks.json");
            file.write(blocksArray.toString(4));
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(BlocksDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<BlockedUsers> getALLBlockedUsers() {
        
      //  ArrayList<BlockedUsers> blocks=BlocksDB.loadFile();
        return blocks;
    }
    
    
    
}
