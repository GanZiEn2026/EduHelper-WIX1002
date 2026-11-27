/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhelper.wix1002;

/**
 *
 * @author User
 */
public class GoldTutor extends Tutor {
    
    public GoldTutor(String id, String userName, String pw, String course,
                     int score, double rate) throws InvalidInputException {
        
        super(id, userName, pw, course, score, rate);
        
        if (score <80){
            throw new InvalidInputException(
            "GoldTutor needs a diagnostic score of 80 or more. Got " + score + ".");
        }
    }
    
    //gold tutor max limit of tution fees
    public double getMaxAllowedRate(){
        return 15.0;
    }
    
}
