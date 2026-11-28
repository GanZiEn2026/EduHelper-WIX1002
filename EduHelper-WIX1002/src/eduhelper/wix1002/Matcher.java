package eduhelper.wix1002;

public class Matcher {

    // Finds all tutors matching the student's courseCode and within budget
    public Tutor[] findMatchingTutors(Student student, Tutor[] allTutors, int tutorCount)
            throws NoTutorFound {

        if (student == null) {
            throw new NoTutorFound("Student record cannot be null.");
        }
        if (allTutors == null || tutorCount <= 0) {
            throw new NoTutorFound("No tutors are currently registered in the system.");
        }

        // First pass: count how many tutors match courseCode and budget
        int matchCount = 0;
        for (int i = 0; i < tutorCount; i++) {
            Tutor t = allTutors[i];
            if (t != null
                    && t.courseCode.equalsIgnoreCase(student.courseCode)
                    && t.hourlyRate <= student.budget) {
                matchCount++;
            }
        }

        if (matchCount == 0) {
            throw new NoTutorFound(
                "No tutor found for course " + student.courseCode
                + " within budget RM" + student.budget + "."
            );
        }

        // Second pass: copy matching tutors into an exact-size plain array
        Tutor[] matched = new Tutor[matchCount];
        int index = 0;
        for (int i = 0; i < tutorCount; i++) {
            Tutor t = allTutors[i];
            if (t != null
                    && t.courseCode.equalsIgnoreCase(student.courseCode)
                    && t.hourlyRate <= student.budget) {
                matched[index] = t;
                index++;
            }
        }

        // Sort matched tutors: highest diagnosticScore first, then highest average rating
        for (int i = 0; i < matched.length - 1; i++) {
            for (int j = 0; j < matched.length - 1 - i; j++) {
                boolean swap = false;
                if (matched[j].diagnosticScore < matched[j + 1].diagnosticScore) {
                    swap = true;
                } else if (matched[j].diagnosticScore == matched[j + 1].diagnosticScore
                        && matched[j].getAverageRating() < matched[j + 1].getAverageRating()) {
                    swap = true;
                }

                if (swap) {
                    Tutor temp = matched[j];
                    matched[j] = matched[j + 1];
                    matched[j + 1] = temp;
                }
            }
        }

        return matched;
    }

    // Returns the single best-ranked tutor for the student
    public Tutor findBestTutor(Student student, Tutor[] allTutors, int tutorCount)
            throws NoTutorFound {
        Tutor[] ranked = findMatchingTutors(student, allTutors, tutorCount);
        return ranked[0];
    }
}
