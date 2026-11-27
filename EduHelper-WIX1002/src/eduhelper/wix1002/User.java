/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhelper.wix1002;

/**
 *
 * @author User
 */

//parent class for(Student;Tutor)
public abstract class User {
    
    String userId; //T001/S002
    String name;
    String password;
    String courseCode;//WIX1002
    
    public User(String id, String userName, String pw, String course){
        userId = id;
        name = userName;
        password = pw;
        courseCode = course;
    }
    
    //ever subclass write its own method (polymorphism)
    public abstract String getRoleSummary();
    
    //check if password typed is correct
    public boolean checkPassword(String input){
        if(input.equals(password)){
            return true;
        }else{
            return false;
        }
    }
    
}  