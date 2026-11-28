/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

// How the frontend uses it (example buttons):
//   1. Text field + "Make Offer" button  -> call makeOffer()
//      - it returns the tutor's counter-offer, OR the deal is done
//        (check session.status, it becomes "ACCEPTED")
//   2. "Accept Counter-Offer" button     -> call acceptCounter()
//   3. After 3 offers with no deal, makeOffer() throws
//      NegotiationFailedException and the session becomes "CANCELLED".
package eduhelper.wix1002;

//runs the price between student and tutor
//max 3 rounds: each round student give an offer
//tutor answers: accept, counter or reject
public class Negotiator {
    
    int maxRounds = 3;
    int round =0;
    double tutorMax =0;
    
    //run full negotiation
    //if both side agree, return the agreed hourly price and update the session: ACCEPTED
    //if money cannot work: throw NegotiatonFailedException
    //if 3 rounds pass with no deal, throws NegotiationFailedException
    
    public void startNegotiation(Student s, Tutor t, Session session){
        
        round=0;
        tutorMax = t.getMaxAllowedRate();
        session.status ="NEGOTIATING";
    }
        // The student makes (or changes) an offer.
        // Returns the tutor's counter-offer if there is no deal yet.
        // If the tutor accepts the offer, the session status becomes
        // "ACCEPTED" and the returned value is the agreed price.
        
        public double makeOffer(Student s, Tutor t, double offer, Session session)
            throws BudgetTooLowException, NegotiationFailedException {
   
            //RULE:offer cannot be more than the tutor mex fee
            if (offer > tutorMax) {
                throw new BudgetTooLowException(
                "Your offer RM" + offer + "/hr is above the tutor's cap RM" + tutorMax + "/hr.");
        }

            // Rule: the offer cannot be more than the student's own budget.
            if (offer > s.budget) {
                throw new BudgetTooLowException(
                    "Your offer RM" + offer + "/hr is more than your budget RM" + s.budget + "/hr.");
            }

            round++;
        
            // If the offer is close enough to the tutor's rate
            // (at least 80%), the tutor accepts the deal.
            double lowestAccept = t.hourlyRate * 0.8;
            if (offer >= lowestAccept) {
                session.acceptSession(offer, session.hours);
                return offer;   // deal done, status is now ACCEPTED
            }

            // No deal yet. If this was the last allowed round, fail.
            if (round >= maxRounds) {
                session.cancelSession();
                throw new NegotiationFailedException(
                    "Negotiation failed after " + maxRounds + " offers. No deal was made.");
            }
            
            // The tutor makes a counter-offer halfway between the
            // student's offer and the tutor's rate. It can never go
            // above the tutor's cap.
            double counter = (offer + t.hourlyRate) / 2;
            if (counter > tutorMax) {
                counter = tutorMax;
            }
            return counter;
        }
        
        // The student presses "Accept Counter-Offer" to lock the deal.
        public double acceptCounter(Student s, Tutor t, double counter, Session session)
                throws BudgetTooLowException {

            // Rule: the counter must be affordable for the student.
            if (counter > s.budget) {
                throw new BudgetTooLowException(
                    "Counter RM" + counter + "/hr is more than your budget RM" + s.budget + "/hr.");
            }
            session.acceptSession(counter, session.hours);
            return counter;
        }

        // Tells the frontend how many offers are left.
        public int getRoundsLeft() {
            return maxRounds - round;
        }
}       