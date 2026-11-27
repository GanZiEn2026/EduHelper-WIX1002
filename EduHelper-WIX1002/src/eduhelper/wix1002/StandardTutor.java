/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhelper.wix1002;

/**
 *
 * @author User
 */
public class StandardTutor extends Tutor {
    
    public StandardTutor(String id, String userName, String pw, String course,
                         int score, double rate) throws InvalidInputException {
        super(id, userName, pw, course, score, rate);
        
        //rule: StandardTutor need a score from 50 to 79 ***rule can be changes
        if (score <50 || score > 79){
            throw new InvalidInputException(
            "StandardTutor needs a diagnostic score between 50 and 79. Got " + score + ".");
        }
    }
    
    //Standard tutor max limit fees
    public double getMaxAllowedRate(){
        return 10.0;
    }
    
    
}
