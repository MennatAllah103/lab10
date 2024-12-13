/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.Group;
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

    private ArrayList<Group> groups = new ArrayList<>();
    public static GroupDataBase groupDB = null;
    UserDataBase userDB = UserDataBase.getDatabase();
    PostDataBase postDB = PostDataBase.getInstance();
    private Group CurrentGroup;
  

    private GroupDataBase() {
        groups = ReadGroupsfromFile();
    }

    
    public static GroupDataBase getInstance() {
        if (groupDB == null) {
            groupDB = new GroupDataBase();
        }
        return groupDB;
    }

    
    private ArrayList<Group> ReadGroupsfromFile() {
   
    try {
        
        String json = new String(Files.readAllBytes(Paths.get("groups.json")));
        JSONArray groupsArray = new JSONArray(json);

        for (int i = 0; i < groupsArray.length(); i++) {
            JSONObject groupJson = groupsArray.getJSONObject(i);
            String name = groupJson.getString("name");
            String groupId = groupJson.getString("groupId");
            String primaryAdminId = groupJson.getString("PrimaryAdminId");

            Group.GroupBuilder builder = new Group.GroupBuilder()
                    .setGroupName(name)
                    .setGroupId(groupId)
                    .setPrimaryAdminId(primaryAdminId);
            
        
//description optionl
              if (groupJson.has("description")) {
                String description = groupJson.getString("description");
                builder.setDescription(description);
            }
            if (groupJson.has("photo")) {
                String photo = groupJson.getString("photo");
                builder.setGroupPhoto(photo);
            }

         
            if (groupJson.has("members")) {
                JSONArray membersArray = groupJson.getJSONArray("members");
                for (int j = 0; j < membersArray.length(); j++) {
                    builder.addMember(membersArray.getString(j));
                }
            }
         if (groupJson.has("admins")) {
                JSONArray adminsArray = groupJson.getJSONArray("admins");
                for (int j = 0; j < adminsArray.length(); j++) {
                    builder.addAdmin(adminsArray.getString(j));
                }
            }

        
            if (groupJson.has("posts")) {
                JSONArray postsArray = groupJson.getJSONArray("posts");
                for (int j = 0; j < postsArray.length(); j++) {
                    builder.addPost(postsArray.getString(j));
                }
            

         
            Group group = builder.build();
            groups.add(group);

        }
        }
    } catch (IOException e) {
        System.err.println("Error reading groups from file: " + e.getMessage());
    } catch (JSONException e) {
        System.err.println("Error parsing JSON data: " + e.getMessage());
    }
    return groups;
}
    
    
    
    public  void saveGroupToFile(ArrayList<Group> groups) {
           
    JSONArray groupsArray = new JSONArray();

  
        for (Group group : groups) {
            JSONObject groupJson = new JSONObject();

            groupJson.put("name", group.getGroupName());
            groupJson.put("description", group.getDescription());
            groupJson.put("photo", group.getGroupPhoto());
            groupJson.put("groupId", group.getGroupId());
            groupJson.put("PrimaryAdminId", group.getPrimaryAdminId());

            JSONArray membersArray = new JSONArray(group.getMembersIDs());
            groupJson.put("members", membersArray);
            //lw mfesh h save []

            JSONArray adminsArray = new JSONArray(group.getAdminsIDs());
            groupJson.put("admins", adminsArray);

            JSONArray postsArray = new JSONArray(group.getPostIDs());
            groupJson.put("posts", postsArray);

        
            groupsArray.put(groupJson);
        }

        try 
        {
            FileWriter file = new FileWriter("groups.json");
            file.write(groupsArray.toString(4));
            file.close();
        }
        catch (IOException e) {
            System.out.println("Error saving groups to file");
        }
    }

    public ArrayList<Group> getGroups() 
    {
        return groups;
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
            if (g.getGroupName().equals(name)) {
                return g;
            }
        }
        return null;
    }
   
         public Group getCurrentGroup()
         {
             return CurrentGroup;
         }

    public void setCurrentGroup(Group CurrentGroup) {
        this.CurrentGroup = CurrentGroup;
    }
         
     
}
