/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContentCreationBackend;

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
import FriendManagementBackend.Management;
public class PostDataBase {

    private static PostDataBase instance; // Singleton instance
    private ArrayList<Post> posts = new ArrayList<>();

    private PostDataBase() {
    }

    public static PostDataBase getInstance() {
        if (instance == null) {
//            synchronized (PostDataBase.class) { //  synchronizing a block of code on the Class object of the PostDataBase class.
//                if (instance == null) {
                    instance = new PostDataBase();
//                }
//            }
        }
        return instance;
    }

    // ArrayList<Post> posts = new ArrayList<>();
    public void SavePostsToFile(ArrayList<Post> newPosts) {
        // Read existing posts from file
        ArrayList<Post> existingPosts = ReadPostsFromFile();

        // Add new posts to the existing list
        existingPosts.addAll(newPosts);  // Add all the new posts to the existing list

        // Write the updated list of posts back to the file
        JSONArray postsArray = new JSONArray();
        for (Post p : existingPosts) {
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
                Post post = new Post();
                post.setContentID(contentID);
                post.setAuthorID(authorID);
                post.setContent(content);
                post.setTimestamp(LocalDateTime.parse(timeStamp, formatter));
                post.setImagePath(imagePath);
                posts.add(post);
            }
        } catch (IOException e) {
            System.err.println("Error reading posts from file: " + e.getMessage());
        } catch (JSONException e) {
            System.err.println("Error parsing JSON data: " + e.getMessage());
        }
        return posts;
    }

    public ArrayList<Post> ViewUserPosts(String userId) {
        Post post = new Post();
        ArrayList<Post> userPosts = new ArrayList<>();
       // ArrayList<Post> allPosts = ReadPostsFromFile();
        ArrayList<Post> allPosts = ReadPostsFromFile();
        for (Post p : allPosts) {
            if (userId.equals(p.getAuthorID())) {
                userPosts.add(p);
            }
        }
        return userPosts;
    }

    public ArrayList<Post> ViewFriendsPosts(String userId) {
    Management management = new Management();
    ArrayList<String> friendsIds = management.getUserFriendsIDs(userId);
    
    ArrayList<Post> friendsPosts = new ArrayList<>();
    ArrayList<Post> allPosts = ReadPostsFromFile();
    
    for (Post post : allPosts) {
        if (friendsIds.contains(post.getAuthorID())) { // Check if post author is a friend
            friendsPosts.add(post);
        }
    }
    
    return friendsPosts;
}


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

}
