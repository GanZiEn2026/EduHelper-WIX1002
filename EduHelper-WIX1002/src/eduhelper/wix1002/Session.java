package eduhelper.wix1002;

public class Session {

    String sessionId;
    Student student;
    Tutor tutor;
    double agreedPrice;
    int hours;
    int test1Score;
    int test2Score;
    int rating;
    String status; // "NEGOTIATING", "ACCEPTED", "COMPLETED", "CANCELLED"

    // Constructor when a new session starts in the NEGOTIATING stage
    public Session(String id, Student s, Tutor t, double price, int sessionHours) {
        sessionId = id;
        student = s;
        tutor = t;
        agreedPrice = price;
        hours = sessionHours;
        test1Score = (s != null) ? s.test1score : 0;
        test2Score = 0;
        rating = 0;
        status = "NEGOTIATING";
    }

    // Overloaded constructor used by FileManager when loading saved sessions from sessions.txt
    public Session(String id, Student s, Tutor t, double price, int sessionHours,
                   int t1, int t2, int savedRating, String savedStatus) {
        sessionId = id;
        student = s;
        tutor = t;
        agreedPrice = price;
        hours = sessionHours;
        test1Score = t1;
        test2Score = t2;
        rating = savedRating;
        status = savedStatus;
    }

    // Updates agreed price and marks the booking as ACCEPTED after negotiation succeeds
    public void acceptSession(double finalPrice, int sessionHours) {
        agreedPrice = finalPrice;
        hours = sessionHours;
        status = "ACCEPTED";
    }

    // Marks the session as CANCELLED if negotiation fails
    public void cancelSession() {
        status = "CANCELLED";
    }

    // Records Pre-Test 1 and Post-Test 2 scores on both Session and Student
    public void recordTestScores(int t1, int t2) {
        test1Score = t1;
        test2Score = t2;
        if (student != null) {
            student.setTestScores(t1, t2);
        }
    }

    // Records student rating (1-5 stars), updates Tutor's cumulative rating, and sets status to COMPLETED
    public void completeSession(int stars) throws InvalidInputException {
        if (stars < 1 || stars > 5) {
            throw new InvalidInputException("Rating must be between 1 and 5 stars. Got: " + stars);
        }
        rating = stars;
        status = "COMPLETED";
        if (tutor != null) {
            tutor.updateRating(stars);
        }
    }

    // Calculates total session fee (hourly agreedPrice * hours)
    public double getTotalCost() {
        return agreedPrice * hours;
    }

    // Calculates learning score improvement (Post-Test 2 minus Pre-Test 1)
    public int getScoreImprovement() {
        return test2Score - test1Score;
    }

    // Displays formatted session details in the console
    public String getSessionSummary() {
        String studentInfo = (student != null) ? student.name + " (" + student.userId + ")" : "Unknown Student";
        String tutorInfo = (tutor != null) ? tutor.name + " (" + tutor.userId + ")" : "Unknown Tutor";
        return "Session [" + sessionId + "] | Status: " + status
             + "\n   Student: " + studentInfo + " | Tutor: " + tutorInfo
             + "\n   Rate: RM" + agreedPrice + "/hr x " + hours + " hr(s) = RM" + getTotalCost()
             + "\n   Pre-Test 1: " + test1Score + "/5 | Post-Test 2: " + test2Score + "/5"
             + " (Improvement: +" + getScoreImprovement() + ")"
             + "\n   Rating: " + (rating > 0 ? rating + "/5 stars" : "Not rated yet");
    }

    // Formats the session as a pipe-delimited line for sessions.txt
    public String toFileString() {
        String sId = (student != null) ? student.userId : "NONE";
        String tId = (tutor != null) ? tutor.userId : "NONE";
        return sessionId + "|" + sId + "|" + tId + "|" + agreedPrice + "|"
             + hours + "|" + test1Score + "|" + test2Score + "|" + rating + "|" + status;
    }
}
