package homework1;

import java.util.List;

public class Team {

    private String name;
    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public Team(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }

    public void showAllMembers() {
        System.out.println("Team " + name + " has the following members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void showResults() {
        System.out.println("The following members in team " + name + " managed to pass the course:");
        for (Member member : members) {
            if (member.isPassedTheCourse()) {
                System.out.println(member);
            }
        }
    }
}
