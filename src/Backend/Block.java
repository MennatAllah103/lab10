/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author daree
 */
public class Block {
    
    String Blocker; 
    String  Blocked; 

    public Block(String Blocker, String Blocked) {
        this.Blocker = Blocker;
        this.Blocked = Blocked;
    }

    public String getBlocker() {
        return Blocker;
    }

    public void setBlocker(String Blocker) {
        this.Blocker = Blocker;
    }

    public String getBlocked() {
        return Blocked;
    }

    public void setBlocked(String Blocked) {
        this.Blocked = Blocked;
    }
    
    
    
    
}
