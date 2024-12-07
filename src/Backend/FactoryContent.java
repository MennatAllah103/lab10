/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContentCreationBackend;

/**
 *
 * @author LENOVO
 */
public class FactoryContent {
    public Content createContent(String type)
    {
        if(type.equalsIgnoreCase("post"))
            return new Post();
        if(type.equalsIgnoreCase("story"))
            return new Story();
        return null;
    }
}
