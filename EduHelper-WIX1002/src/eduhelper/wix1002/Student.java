/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhelper.wix1002;

/**
 *
 * @author User
 */

//student under user
//store extra data: subject, budget, two test score
public class Student extends User {
    //protected= sutdent class and same file class all can use these string
    String subject;
    double budget;
    int test1score;
    int test2score;
    
    //public= othere java file can change use and change these
    public Student(String id, String userName, String pw, String course,
                   String subj, double money){
        //super: give data to the User constructor
        super(id, userName, pw, course);
        subject = subj;
        budget = money;
        test1score =0;
        test2score =0;
           
    }
    
    //own version of getDoleSummary() from User
    @Override
    public String getRoleSummary(){
        return "Student: " + name + " (" + userId + ")"
             + " | Subject: " + subject
             + " | Budget: RM" + budget
             + " | Pre-Test: " + test1score + "/5"
             + " | Post-Test: " + test2score + "/5";
    }
    
    //save the test1 test2 score
    public void setTestScores(int t1, int t2){
        test1score = t1;
        test2score = t2;
    }
    
    
    
    
}
