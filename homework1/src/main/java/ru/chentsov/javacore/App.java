package ru.chentsov.javacore;

import ru.chentsov.javacore.homework1.Course;
import ru.chentsov.javacore.homework1.Member;
import ru.chentsov.javacore.homework1.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();
        members.add(new Member("Player_One"));
        members.add(new Member("Player_Two"));
        members.add(new Member("Player_Three"));
        members.add(new Member("Player_Four"));

        Team team = new Team("GB", members);
        Course course = new Course();

        course.doIt(team);
        team.showAllMembers();
        team.showResults();
    }

}