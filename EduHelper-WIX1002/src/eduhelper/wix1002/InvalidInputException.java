/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhelper.wix1002;

/**
 *
 * @author User
 */
public class InvalidInputException extends Exception {
    
    //Message explains what when wrong
    public InvalidInputException(String message){
        super(message);
    }
    
}
