package main.java.oop_design_systems.assignment_problems;

import java.util.*;

interface ScoringRule {
    double calculate(double idea, double execution,
                     double presentation);
}

class InnovationScoring implements ScoringRule {
    public double calculate(double idea, double execution,
                            double presentation) {
        return idea * 0.50 +
               execution * 0.30 +
               presentation * 0.20;
    }
}

class OpenScoring implements ScoringRule {
    public double calculate(double idea, double execution,
                            double presentation) {
        return (idea + execution + presentation) / 3;
    }
}

class Student {
    String name;

    public Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;

    public Project(String name) {
        this.name = name;
    }
}

class Team {
    String name;
    List<Student> members;
    ScoringRule scoringRule;
    Project project;
    double finalScore;

    public Team(String name, List<Student> members,
                ScoringRule scoringRule) {
        this.name = name;
        this.members = members;
        this.scoringRule = scoringRule;
    }

    public boolean submit(Project project) {
        if (this.project != null)
            return false;

        this.project = project;
        return true;
    }

    public void score(double idea, double execution,
                      double presentation) {
        finalScore = scoringRule.calculate(
                idea, execution, presentation);
    }
}

class Hackathon {
    String name;
    private String state = "Open";
    private List<Team> teams = new ArrayList<>();
    private Set<Student> registeredStudents = new HashSet<>();

    public Hackathon(String name) {
        this.name = name;
    }

    public boolean registerTeam(Team team) {
        if (team.members.size() < 2 ||
            team.members.size() > 4) {
            System.out.println(
                "Registration failed: A team must have 2 to 4 members."
            );
            return false;
        }

        for (Student s : team.members) {
            if (registeredStudents.contains(s)) {
                System.out.println(
                    "Registration failed: Student already belongs to a team."
                );
                return false;
            }
        }

        teams.add(team);
        registeredStudents.addAll(team.members);

        System.out.println(
            "Team " + team.name +
            " registered (" +
            team.members.size() +
            " members)."
        );

        return true;
    }

    public void submit(Team team, Project project) {
        if (team.submit(project))
            System.out.println(
                "Project '" + project.name +
                "' submitted by " + team.name + "."
            );
    }

    public void publish() {
        state = "Published";
        System.out.println("Results published.");
    }

    public void rescore(Team team, double idea,
                        double execution,
                        double presentation) {
        if (state.equals("Published")) {
            System.out.println(
                "Rescore rejected: Results have already been published."
            );
            return;
        }

        team.score(idea, execution, presentation);
    }
}

public class CodeSprintJudgingDesk {
    public static void main(String[] args) {
        Hackathon hackathon =
                new Hackathon("Code Sprint");

        Team team = new Team(
            "ByteBusters",
            Arrays.asList(
                new Student("Asha"),
                new Student("Ravi"),
                new Student("Neha")
            ),
            new InnovationScoring()
        );

        hackathon.registerTeam(team);

        Project project = new Project("SmartAttend");

        hackathon.submit(team, project);

        team.score(8, 7, 9);

        System.out.printf("Final score: %.2f%n",
                team.finalScore);

        hackathon.publish();

        hackathon.rescore(team, 10, 7, 9);
    }
}
