/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author yaras
 */
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostDataBase {

    FactoryContent F = new FactoryContent();
     Management management = new Management();
    private static PostDataBase instance; // Singleton instance
    private ArrayList<Post> postList = new ArrayList<>();

    private PostDataBase() 
    {
        postList=ReadPostsFromFile();
    }

    public static PostDataBase getInstance() {
        if (instance == null)
        {
       instance = new PostDataBase();
        }
        return instance;
    }

  
    public void SavePostsToFile(ArrayList<Post> Posts) 
    {
   
        JSONArray postsArray = new JSONArray();
        for (Post p : Posts) {
            JSONObject j = new JSONObject();
            j.put("contentID", p.getContentID());
            j.put("authorID", p.getAuthorID());
            j.put("content", p.getContent());
            j.put("timeStamp", p.getTimestamp());
            j.put("imagePath", p.getImagePath());
            postsArray.put(j);
        }
        try {
            FileWriter file = new FileWriter("posts.json");
            file.write(postsArray.toString(4));
            file.close();
        } catch (IOException ex) {
            System.out.println("Error saivng posts to file.");
            // Logger.getLogger(PostDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Post> ReadPostsFromFile() {
        try {
            
            String json = new String(Files.readAllBytes(Paths.get("posts.json")));
            JSONArray postsArray = new JSONArray(json);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postJson = postsArray.getJSONObject(i);
                String contentID = postJson.getString("contentID");
                String authorID = postJson.getString("authorID");
                String content = postJson.getString("content");
                String timeStamp = postJson.getString("timeStamp");
                // String imagePath = postJson.getString("imagePath");
                String imagePath = "";
                try {
                    imagePath = postJson.getString("imagePath");
                } catch (JSONException e) {
                    // Handle the case where "imagePath" is missing
                    imagePath = "";
                }
               // Post post = new Post();
               //lazm tst5dme createfactory
               Post post = (Post) F.createContent("post");
                post.setContentID(contentID);
                post.setAuthorID(authorID);
                post.setContent(content);
                post.setTimestamp(LocalDateTime.parse(timeStamp, formatter));
                post.setImagePath(imagePath);
                postList.add(post);
            }
        } catch (IOException e) {
            System.err.println("Error reading posts from file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return postList;
    }

    public ArrayList<Post> ViewUserPosts(String userId) {
    
        ArrayList<Post> userPosts = new ArrayList<>();
        for (Post p : postList)
        {
            if (userId.equals(p.getAuthorID()))
            {
                userPosts.add(p);
            }
        }
        return userPosts;
    }

    public ArrayList<Post> ViewFriendsPosts(String userId)
    {
    ArrayList<String> friendsIds = management.getUserFriendsIDs(userId);
    
    ArrayList<Post> friendsPosts = new ArrayList<>();
    for (Post post : postList) {
        if (friendsIds.contains(post.getAuthorID())) { 
            friendsPosts.add(post);
        }
    }
    
    return friendsPosts;
}
    
    
//msh fhma @yara 7ata method de leh w le ehhh
/*
    public void removedPosts(String contentID) {
        posts = ReadPostsFromFile();
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getContentID().equals(contentID)) {
                posts.remove(i); // Remove the post by matching ID
                break;
            }
        }
        SavePostsToFile(posts);
    }

   */ 
     public void addPost(Post p) {
        postList.add(p);
        SavePostsToFile(postList);
    }
    public void deletePost(Post p) {
        postList.remove(p);
        SavePostsToFile(postList);
    }

    public ArrayList<Post> getPosts() {
      
        return postList;
    }
}
