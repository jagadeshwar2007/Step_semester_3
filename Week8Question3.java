import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract class Question {
    protected int number;
    protected int points;

    public Question(int number, int points) {
        this.number = number;
        this.points = points;
    }

    public abstract boolean isCorrect(String answer);

    public int getNumber() {
        return number;
    }

    public int getPoints() {
        return points;
    }
}

class MultipleChoiceQuestion extends Question {
    private String correctOption;

    public MultipleChoiceQuestion(int number, int points, String correctOption) {
        super(number, points);
        this.correctOption = correctOption;
    }

    public boolean isCorrect(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctValue;

    public TrueFalseQuestion(int number, int points, boolean correctValue) {
        super(number, points);
        this.correctValue = correctValue;
    }

    public boolean isCorrect(String answer) {
        return Boolean.parseBoolean(answer) == correctValue;
    }
}

class ShortAnswerQuestion extends Question {
    private String expected;

    public ShortAnswerQuestion(int number, int points, String expected) {
        super(number, points);
        this.expected = expected;
    }

    public boolean isCorrect(String answer) {
        return expected.equalsIgnoreCase(answer.trim());
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

class Examination {
    private String name;
    private List<Question> questions = new ArrayList<>();
    private Set<Student> submittedBy = new HashSet<>();

    public Examination(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Attempt start(Student student) {
        if (submittedBy.contains(student)) {
            System.out.println(student.getName() + " has already submitted " + name + ".");
            return null;
        }
        System.out.println(name + " started by " + student.getName() + ".");
        return new Attempt(student, this);
    }

    void markSubmitted(Student student) {
        submittedBy.add(student);
    }
}

class Attempt {
    private Student student;
    private Examination exam;
    private Map<Integer, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    public void answer(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(question.getNumber(), answer);
        System.out.println("Answer recorded for Question " + question.getNumber() + ".");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }
        submitted = true;
        exam.markSubmitted(student);
        System.out.println(exam.getName() + " submitted by " + student.getName() + ".");
        printResult();
    }

    private void printResult() {
        StringBuilder sb = new StringBuilder("Result: ");
        int score = 0;
        int total = 0;
        boolean first = true;
        for (Question q : exam.getQuestions()) {
            String given = answers.get(q.getNumber());
            boolean correct = given != null && q.isCorrect(given);
            int earned = correct ? q.getPoints() : 0;
            score += earned;
            total += q.getPoints();
            if (!first) {
                sb.append(", ");
            }
            first = false;
            sb.append("Question ").append(q.getNumber()).append(": ")
                    .append(correct ? "Correct" : "Incorrect").append(" (").append(earned).append(" points)");
        }
        sb.append(". Total score: ").append(score).append("/").append(total).append(".");
        System.out.println(sb);
    }
}

public class Week8Question3 {
    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        Question q1 = new MultipleChoiceQuestion(1, 5, "C");
        Question q2 = new TrueFalseQuestion(2, 5, false);
        examA.addQuestion(q1);
        examA.addQuestion(q2);

        Student student1 = new Student("Student 1");
        Attempt attempt = examA.start(student1);
        attempt.answer(q1, "C");
        attempt.answer(q2, "True");
        attempt.submit();
        attempt.answer(q1, "A");
    }
}
