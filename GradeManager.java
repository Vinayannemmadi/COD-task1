import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GradeManager extends Frame implements ActionListener {
    private TextField gradeInput;
    private TextArea gradeList;
    private Label averageLabel;
    private Label letterGradeLabel;
    private ArrayList<Double> grades;
    
    public GradeManager() {
        setLayout(new FlowLayout());

        Label gradeInputLabel = new Label("Enter Marks:");
        add(gradeInputLabel);

        gradeInput = new TextField(10);
        add(gradeInput);

        Button addButton = new Button("Add Marks");
        addButton.addActionListener(this);
        add(addButton);

        gradeList = new TextArea(10, 30);
        gradeList.setEditable(false);
        add(gradeList);

        averageLabel = new Label("Average: N/A");
        add(averageLabel);
	
	letterGradeLabel = new Label("Grade: N/A");
        add(letterGradeLabel);

        grades = new ArrayList<>();

        setTitle("Student Grade Manager");
        setSize(400, 350);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GradeManager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double grade = Double.parseDouble(gradeInput.getText());
            if (grade < 0 || grade > 100) {
                gradeList.append("Invalid grade: " + grade + "\n");
            } else {
                grades.add(grade);
                updateGradeList();
                calculateAverage();
            }
        } catch (NumberFormatException ex) {
            gradeList.append("Invalid input: " + gradeInput.getText() + "\n");
        }
        gradeInput.setText("");
    }

    private void updateGradeList() {
        gradeList.setText("");
        for (double grade : grades) {
            gradeList.append(grade + "\n");
        }
    }

    private void calculateAverage() {
        if (grades.isEmpty()) {
            averageLabel.setText("Average: N/A");
	    letterGradeLabel.setText("Letter Grade: N/A");
            return;
        }

        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }

        double average = sum / grades.size();
	String letterGrade = getLetterGrade(average);
        averageLabel.setText("Average: " + String.format("%.2f", average) + " (" + getLetterGrade(average) + ")");
	letterGradeLabel.setText("Letter Grade: " + letterGrade);

	    }

    private String getLetterGrade(double average) {
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }
}
