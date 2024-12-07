/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FriendManagementBackend;

/**
 *
 * @author daree
 */
public class Friends {
    
    String Userid1; 
    String Userid2;  

    public Friends(String Userid1, String Userid2) {
        this.Userid1 = Userid1;
        this.Userid2 = Userid2;
    }
   

    public void setUserid1(String Userid1) {
        this.Userid1 = Userid1;
    }

    public void setUserid2(String Userid2) {
        this.Userid2 = Userid2;
    }

    public String getUserid1() {
        return Userid1;
    }

    public String getUserid2() {
        return Userid2;
    }
    
    
    
}
