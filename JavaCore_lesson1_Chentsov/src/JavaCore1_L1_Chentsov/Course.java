package JavaCore1_L1_Chentsov;

import java.util.Random;

public class Course {
    private int[] obstacles;

    public Course() {
        obstacles = new int[] {4, 11, 7};
    }

    public int[] getObstacles() {
        return obstacles;
    }

    public void doIt(Team team) {
        System.out.println("Members of the team " + team.getName() + " have just started the course!");

        for (Member member : team.getMembers()) {
            //can current member pass every obstacle?
            for (int i = 0; i < obstacles.length; i++) {
                int maxPerformance = 50;
                int currentPerformance = new Random().nextInt(maxPerformance);

                if (currentPerformance <= obstacles[i]) {
                    member.setPassedTheCourse(false);
                    break;
                }

            }
        }
    }


}
