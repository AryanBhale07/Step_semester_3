package main.java.oop_design_systems.class_problems;

abstract class Question {
    protected String text;

    public Question(String text) {
        this.text = text;
    }

    public abstract boolean checkAnswer(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String text, String correctAnswer) {
        super(text);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    Student student;
    private Examination examination;
    private String[] answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        this.answers = new String[examination.getQuestionCount()];
    }

    public void answer(int questionNumber, String answer) {
        if (submitted)
            return;

        answers[questionNumber - 1] = answer;
        System.out.println("Question " + questionNumber +
                " answered with '" + answer + "'.");
    }

    public void submit() {
        if (submitted)
            return;

        submitted = true;

        int correct = 0;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] != null &&
                    examination.getQuestion(i).checkAnswer(answers[i]))
                correct++;
        }

        System.out.println("Examination '" +
                examination.getTitle() +
                "' submitted successfully.");

        System.out.println("Result for '" +
                examination.getTitle() +
                "' attempt: " +
                correct + "/" + answers.length + " correct");
    }
}

class Examination {
    private String title;
    private Question[] questions;

    public Examination(String title, Question[] questions) {
        this.title = title;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public int getQuestionCount() {
        return questions.length;
    }

    public Question getQuestion(int index) {
        return questions[index];
    }

    public Attempt start(Student student) {
        System.out.println("Examination '" + title +
                "' started by " + student.getName() + ".");
        return new Attempt(student, this);
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("John");

        Question[] questions = {
            new MultipleChoiceQuestion("2 + 2 = ?", "A"),
            new MultipleChoiceQuestion("Capital of France?", "B")
        };

        Examination exam =
                new Examination("Math Quiz", questions);

        Attempt attempt = exam.start(student);

        attempt.answer(1, "A");
        attempt.answer(2, "C");

        attempt.submit();
    }
}
