/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

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
public class BlockDatabase {
    
    
    private static BlockDatabase BlocksDB=null;
    ArrayList<Block> blocks = new ArrayList<>();

    private BlockDatabase() {
        
        blocks=loadFile();
    }
    
       public static BlockDatabase getinstance() {
        if (BlocksDB == null) {
            BlocksDB = new BlockDatabase();
        }

        return BlocksDB;

    }
       
        public ArrayList<Block> loadFile() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("blocks.json")));
            JSONArray blocksArray = new JSONArray(json);
            for (int i = 0; i < blocksArray.length(); i++) {
                JSONObject blockJson = blocksArray.getJSONObject(i);
                String Blocker = blockJson.getString("Blocker");
                String Blocked = blockJson.getString("Blocked");
                blocks.add(new Block(Blocker, Blocked));

            }

           
        } catch (IOException ex) {
            Logger.getLogger(RequestDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     return blocks;
    }

        
         public void saveFile(ArrayList<Block> blocks) {
        JSONArray blocksArray = new JSONArray();
        for (Block b : blocks) {
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
            Logger.getLogger(BlockDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Block> getALLBlockedUsers() {
        
    
        return blocks;
    }
    
    
    
}
