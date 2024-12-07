/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContentCreationBackend;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author yaras
 */
public abstract class Content {

    protected String contentID;
    protected String authorID;
    protected String content;
    protected String imagePath;
    protected LocalDateTime timestamp;

    public Content() {
//        contentID = UUID.randomUUID().toString();
//        this.authorID = authorID;
//        this.content = content;
//        this.timestamp = timestamp;
//String authorID, String content, LocalDateTime timestamp
    }

    public String getContentID() {
        return contentID;
    }
    
    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    
    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
