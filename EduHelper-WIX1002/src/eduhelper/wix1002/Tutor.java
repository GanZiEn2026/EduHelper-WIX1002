/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eduhelper.wix1002;

/**
 *
 * @author User
 */
//tutor is parent class for goldtuto and standardtuto
//tutor also sub class of User
public abstract class Tutor extends User {
    
    int diagnosticScore;
    double hourlyRate;
    double ratingSum;
    int ratingCount;
    
    public Tutor(String id, String userName, String pw, String course,
                 int score, double rate) throws InvalidInputException {
        
        super(id, userName, pw ,course);
        
        //rule: tutor score above 50 to register ***need to change!!!
        if(score <50){
            throw new InvalidInputException(
            "Diagnostic score "+score+" is below 50. Tutor is not qualified.");
        }
        if (score > 100) {
            throw new InvalidInputException("Diagnostic score cannot be more than 100.");
        }
        
        diagnosticScore = score;
        hourlyRate = rate;
        ratingSum = 0;
        ratingCount = 0;
    }
    
    //Goldtutor retrun 15.0 and StandardTutor return 10.0
    public abstract double getMaxAllowedRate();
    
    //own version of getRoleSummary() from User
    @Override
    public String getRoleSummary(){
        return "Tutor: " + name + " (" + userId + ")"
             + " | Rate: RM" + hourlyRate + "/hr"
             + " | Average rating: " + getAverageRating() + "/5";
    }
    
    //Add star rating (1 to 5) to the totals
    public void updateRating(int stars){
        if (stars >= 1 && stars <= 5) {
            ratingSum = ratingSum + stars;
            ratingCount = ratingCount + 1;
        }
    }
    
    //Average stars
    public double getAverageRating(){
        if(ratingCount==0){
            return 0;
        }
        return ratingSum/ratingCount;
    }
    
    //check new hourly rate against the tutor fees limit
    public void setHourlyRate(double newRate) throws InvalidInputException{
        if(newRate>getMaxAllowedRate()){
            throw new InvalidInputException(
            "Rate RM" + newRate + " is too high. Maximum allowed is RM" + getMaxAllowedRate() + ".");
        }
        hourlyRate = newRate;
    }
    
    //User by FileManager put saved rating back when the program starts
    public void setRatingData(double sum, int count){
        ratingSum = sum;
        ratingCount = count;
    }
}
