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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LENOVO
 */
public class GroupDataBase {
    private static ArrayList<Group> groups=new ArrayList<>();
    public static GroupDataBase database=null;
    UserDataBase userData=UserDataBase.getDatabase();
    GroupManagement manager=new GroupManagement();
    
     private GroupDataBase() {
        groups=ReadGroupsfromFile();
    }

          public static GroupDataBase getInstance()
     {
       if(database==null)
           database=new GroupDataBase();
          
           return database;
     }
     private ArrayList<Group> ReadGroupsfromFile() {
        ArrayList<Group>groups=new ArrayList<>();
         try {
            String json = new String(Files.readAllBytes(Paths.get("groups.json")));
            JSONArray groupsArray = new JSONArray(json);
            for (int i = 0; i < groupsArray.length(); i++) {
                JSONObject groupJson = groupsArray.getJSONObject(i);
                String name=groupJson.getString("name");
                String description=groupJson.getString("description");
                String photo=groupJson.getString("photo");
                String groupId=groupJson.getString("groupId");
                String primaryAdminId=groupJson.getString("PrimaryAdminId");
               User primaryAdmin=userData.getUserById(primaryAdminId);
                
              Group g= new Group(name,description,photo,primaryAdmin) ;
              g.setGroupId(groupId);
              
            JSONArray membersArray = groupJson.optJSONArray("members");
                if (membersArray != null) {
                    for (int j = 0; j < membersArray.length(); j++) {
                        User member = userData.getUserById(membersArray.getString(j));
                        manager.addMember(member, g.getName());
                    }
                }
                
             JSONArray adminsArray = groupJson.optJSONArray("admins");
                if (adminsArray != null) {
                    for (int j = 0; j < adminsArray.length(); j++) {
                        User admin = userData.getUserById(adminsArray.getString(j));
                        g.promoteToAdmin(admin);
                    }
                }    
               
                groups.add(g);   
            }  
          } catch (IOException e) {
            System.err.println("Error reading users from file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return groups;      
                
                
    }
   public static void saveGroupToFile(ArrayList<Group>groups) 
  {
    JSONArray groupsArray = new JSONArray();
    for (Group g : groups) {
       JSONObject j = new JSONObject();
            j.put("name", g.getName());
            j.put("description", g.getDescription());
            j.put("photo", g.getPhoto());
            j.put("groupId", g.getGroupId());
            j.put("PrimaryAdminId", g.getPrimaryAdmin().getUserId());
            
            JSONArray membersArray = new JSONArray();
            for (User member : g.getMembers()) {
                membersArray.put(member.getUserId());
            }
            j.put("members", membersArray);
            
            
            JSONArray adminsArray = new JSONArray();
            for (User admin : g.getAdmins()) {
                adminsArray.put(admin.getUserId());
            }
            j.put("admins", adminsArray);

            groupsArray.put(j);
       
  }
    try {
            FileWriter file = new FileWriter("groups.json");
            file.write(groupsArray.toString(4));
            file.close();
        } catch (IOException e) {
            System.out.println("Error saving users to file");
        }
}
  
   public boolean addGroup(Group g) {
    // Ensure the current groups are loaded from the file (if not already loaded)
    if (groups.isEmpty()) {
        groups = ReadGroupsfromFile();
    }

    // Check if the group already exists
    for (Group existingGroup : groups) {
        if (existingGroup.getName().equals(g.getName())) {
            System.err.println("Group already exists.");
            return false;
        }
    }
    
    groups.add(g);
    saveGroupToFile(groups);
    return true;
}

     public Group getGroupById(String groupId) {
        for (Group g : groups) {
            if (g.getGroupId().equals(groupId)) {
                return g;
            }
        }
        return null;
    }
  
         public Group getGroupByname(String name) {
        for (Group g : groups) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }
     
     
     
     
     
     
  public void deleteGroup(String groupId) {
        Group g = getGroupById(groupId);
        if (g != null) {
            groups.remove(g);
            saveGroupToFile(groups);
        }
    }       
}
