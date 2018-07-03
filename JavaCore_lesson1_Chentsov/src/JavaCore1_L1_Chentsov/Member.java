package JavaCore1_L1_Chentsov;

public class Member {
    private boolean passedTheCourse;
    private String name;


    public Member(String name) {
        this.name = name;
        passedTheCourse = true;
    }


    public String getName() {
        return name;
    }


    public void setPassedTheCourse(boolean passedTheCourse) {
        this.passedTheCourse = passedTheCourse;
    }


    public boolean isPassedTheCourse() {
        return passedTheCourse;
    }

    @Override
    public String toString() {
        return name;
    }
}
