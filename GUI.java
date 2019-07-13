/**
 * @author Joel Tavarez 
 * 
 */



//overall assumptions

//only courses that has been taken in the past can have gpa
//calculation of target gpa is for any existing "current" or "future" courses
//default value for gpa is an empty string, ""
//grades and name of course are optional; however, grades for are mandatory for previously taken classes


import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	//displays 
	JTable classes;
	DefaultTableModel model;
	ArrayList<Courses> courseLog;

	//COURSES
	//fields to display the info entered
	JLabel calculatedGrade;
	JLabel requiredGrade;

	//the buttons to carry out the functions and their instructions
	JButton calculate;
	JButton calculate_2; //calculate the future gpa
	JButton remover; // removes one course
	JButton reset; // removes all courses
	JButton addCourse;

	// instructions for buttons
	JLabel Name;
	JLabel grade;
	JLabel credits;
	JLabel state;
	JLabel targetGrade;
	JLabel warn;

	//entering the information
	JTextArea courseName; //optional
	JTextArea creditHours;
	JComboBox<String> letterGrade; //optional
	JComboBox<String> courseType;
	JTextArea enterTarg;

	//SUMMARY
	double calcGrade = 0.0;
	double reqGrade = 0;
	DecimalFormat df = new  DecimalFormat("0.##");


	public static void main(String[] args) {
		new GUI();
	}

	//the grand constructor
	public GUI() {
		//sizing of the window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.setSize(width, height);


		//the list of classes are here with everything such as cred hours and grade


		model= new DefaultTableModel();
		model.addColumn("Course Name");
		model.addColumn("GPA");
		model.addColumn("Credit Hours");
		model.addColumn("Status");

		classes = new JTable(model);


		String[] type = {"previously taken", "current", "future"};
		String[] gradeChoice = {"","A", "A-", "B+", "B", "B-", "C+", "C", "C-","D+", "D", "D-", "F"};

		//courses		


		calculate = new JButton("Calculate GPA");
		calculate_2 = new JButton("Calculate Required GPA");
		remover = new JButton("Remove Course");
		reset = new JButton("Reset");


		addCourse = new JButton("Add Course");

		Name = new JLabel("Enter Course Name: ");
		grade = new JLabel("Enter Letter Grade (Leave Empty for Future or Current Classes): ");
		credits = new JLabel("Enter Credit Hours: ");
		state = new JLabel("Enter Class Status: ");
		targetGrade = new JLabel("Enter Target GPA: ");
		warn = new JLabel(""); 

		//entries for courses
		courseName = new JTextArea("");
		letterGrade = new JComboBox<String>(gradeChoice);
		creditHours = new JTextArea("");
		enterTarg = new JTextArea("");
		courseType = new JComboBox<String>(type);
		courseLog = new ArrayList<Courses>();

		//summary

		requiredGrade = new JLabel("Required Grade: " + reqGrade);
		requiredGrade.setFont(new Font("Georgia",Font.BOLD, 28));
		calculatedGrade = new JLabel("Current GPA: 0.0");
		calculatedGrade.setFont(new Font("Georgia", Font.BOLD, 28));

		//setting the size
		calculatedGrade.setSize(width/3, 50);


		addCourse.setSize(width/6, 20);
		calculate.setSize(width/6, 20);
		calculate_2.setSize(width/6, 20);
		remover.setSize(width/6, 20);
		reset.setSize(width/6, 20);


		Name.setSize(width/3, 20);
		grade.setSize(width/3, 20);
		credits.setSize(width/3, 20);
		targetGrade.setSize(width/3, 20);


		courseName.setSize(width/3, 20);
		letterGrade.setSize(width/3, 20);
		creditHours.setSize(width/3, 20);
		courseType.setSize(width/3, 20);
		state.setSize(width/3, 20);

		enterTarg.setSize(width/3, 20);

		requiredGrade.setSize(width/3, 50);

		classes.setSize(33*width/50, 550);
		warn.setSize(800, 30);
		warn.setFont(new Font("Georgia", Font.ITALIC, 24));

		//locations of the items


		classes.setLocation(width/6, 300);


		Name.setLocation(width/25, 25);
		credits.setLocation(width/25, 75);
		grade.setLocation(width/25, 125);  
		state.setLocation(width/25, 175);
		targetGrade.setLocation(width/25, 225);

		courseName.setLocation(width/25, 50);
		letterGrade.setLocation(width/25, 150);   
		creditHours.setLocation(width/25, 100);
		courseType.setLocation(width/25, 200);
		enterTarg.setLocation(width/25, 250);

		addCourse.setLocation(width/2, 45);
		calculate.setLocation(width/2, 95);
		calculate_2.setLocation(width/2, 145);
		remover.setLocation(width/2, 195);
		reset.setLocation(width/2, 245);


		calculatedGrade.setLocation(1000, 100);
		requiredGrade.setLocation(1000, 150);
		warn.setLocation(500, 270);


		//adding things onto the page
		this.add(classes);

		this.add(courseName);
		this.add(creditHours);

		this.add(letterGrade);
		this.add(enterTarg);
		this.add(requiredGrade);
		this.add(calculate);
		this.add(remover);
		this.add(reset);
		this.add(addCourse);
		this.add(courseType);
		this.add(state);

		this.add(calculatedGrade);
		this.add(calculate_2);

		this.add(Name);
		this.add(grade);
		this.add(credits);
		this.add(targetGrade);
		this.add(classes);
		this.add(warn);


		//final adds to show everything
		this.add(new JLabel());
		this.setLocationRelativeTo(null);
		setVisible(true);

		/**
		 * adds any course to the course catalog
		 **/
		ActionListener addCourseAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent addNewCourse) {
				// Making sure credit hours are present
				if(creditHours.getText().equals("") || creditHours.getText().equals("")){
					warn.setText("Please input Credit Hour.");
				}
				// previously taken courses must have a grade.
				else if((courseType.getSelectedItem() + "").equals("previously taken") && (letterGrade.getSelectedItem() + "").equals("")) {
					warn.setText("Taken courses must have Letter Grades.");
				}
				// Future or current courses should not have GPA.
				else if ((!(courseType.getSelectedItem() + "").equals("previously taken") && !(letterGrade.getSelectedItem() + "").equals(""))) {
					warn.setText("Future or current courses should not have letter grades.");
				}
				// Adding future or current courses.
				else if(!(courseType.getSelectedItem() + "").equals("previously taken") && (letterGrade.getSelectedItem() + "").equals("")) {
					int credHours = Integer.parseInt(creditHours.getText());
					String nameOfCourse = courseName.getText();
					String letterGrd = letterGrade.getSelectedItem() + "";
					String courseStatus = courseType.getSelectedItem() + "";
					courseLog.add(new Courses(credHours, nameOfCourse, letterGrd, courseStatus));
					model.addRow(new String [] {creditHours.getText(), nameOfCourse, letterGrd, courseStatus});
				}
				else {
					int credHours = Integer.parseInt(creditHours.getText());
					String nameOfCourse = courseName.getText();
					String letterGrd = letterGrade.getSelectedItem() + "";
					String courseStatus = courseType.getSelectedItem() + "";

					courseLog.add(new Courses(credHours, nameOfCourse, letterGrd, courseStatus));
					model.addRow(new String [] {creditHours.getText(), nameOfCourse, letterGrd, courseStatus});

				}
			}
		};

		
		addCourse.addActionListener(addCourseAction);
		/**
		 * Clears the entire course log
		 */
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent removingAll) {
				//avoid bad entries
				if(courseLog.size() == 0) {
					warn.setText("Catalog is Empty");
				}

				else {
					//reset grades
					requiredGrade.setText("Required GPA: 0.0");
					calculatedGrade.setText("Current GPA: 0.0");

					// clear all
					for(int i = courseLog.size() - 1; i >= 0; i--) {
						model.removeRow(i);
					}
					courseLog.clear();	

				}

			}
		});
		/*
		 * calculates the current GPA
		 * warns when status of the course is not previously taken
		 * also warns when the course is not empty 
		 */
		calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent calculate) {

				//ensure there are things to calculate with
				if(courseLog.size() == 0) {
					warn.setText("Please enter a previously taken course to calculate.");
				}

				else { 

					calculatedGrade.setText("Current GPA: " + calculateCurrGpa());
				}

			}
		});
		/*
		 * removes a single course from the course log
		 */
		ActionListener removeCourse = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent removeOneCourse) {
				// If there are no courses in the catalog.
				if(courseLog.size() == 0) {
					warn.setText("Catalog is empty");
				}
				else {
					//reset grades
					calcGrade = 0.0;
					reqGrade = 0.0;

					//remove
					model.removeRow(courseLog.size() - 1);
					courseLog.remove(courseLog.size() - 1);
				}
			}
		};
		remover.addActionListener(removeCourse);
		
		/*
		 * calculates the required GPA
		 * warns when no values, negative values, and/or values greater than 4 are given for the target value
		 */
		calculate_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent calculateFuture) {
				double gradeGoal = Double.parseDouble(enterTarg.getText());
				
				int cumilativeCredit = cumilativeCredit();
				int currentCredit = 0;
				int futureCredit = 0;
				double currGpa = Double.parseDouble(calculateCurrGpa());
				
				if(enterTarg.getText().equals("")||(Double.parseDouble(enterTarg.getText())) < 0.0 || Double.parseDouble(enterTarg.getText()) > 4.0) {
					warn.setText("Please input a valid target grade.");
				}
				else {
					for(Courses course: courseLog) {
						if(course.getStatus().equals("previously taken")) {
							currentCredit = currentCredit + course.getCredit();
						}
						else {
							futureCredit = futureCredit + course.getCredit();
						}
					}
	
					double requiredGPA = ((gradeGoal * cumilativeCredit) - (currGpa * currentCredit)) / futureCredit;
					reqGrade = requiredGPA;
					
					if(requiredGPA > 4.0) {
						warn.setText("Try adding more credit hours and recalculating.");
					}
					if(requiredGPA < 2.0) {
						if(requiredGPA < 0.0) {
							requiredGPA = 0.0;
						}
						warn.setText("Your required GPA is " + df.format(reqGrade)+ ". " + "You are able to take fewer credit hours, if you wish.");
					}
					 requiredGrade.setText("Required Grade: " + df.format(reqGrade));
				}
		}
		});

	}
	/**
	 * 
	 * @return cumilitive credit
	 */
	public int cumilativeCredit() {
		int cumilativeCredit = 0;
		for (Courses courses: courseLog) {
			cumilativeCredit = cumilativeCredit + courses.getCredit();
		}
		return cumilativeCredit;
	}
	
	/**
	 * http://www.sourcecodeera.com/blogs/Samath/Simple-GPA-calculator-in-Java.aspx ---> GPA algorithm 
	 * @return current GPA
	 */
	public String calculateCurrGpa() {
		int creditHourSum = 0;
		double weightedGpaSum = 0.0;
		
		for(Courses course : courseLog) {
			if(course.getStatus().equals("previously taken")) {
				if(course.getGrade().equals("A")) {
					weightedGpaSum += (4.0 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("A-")) {
					weightedGpaSum += (3.67 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("B+")) {
					weightedGpaSum += (3.3 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("B")) {
					weightedGpaSum += (3.0 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("B-")) {
					weightedGpaSum += (2.67 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("C+")) {
					weightedGpaSum += (2.33 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("C")) {
					weightedGpaSum += (2.0 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("C-")) {
					weightedGpaSum += (1.67 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("D+")) {
					weightedGpaSum += (1.33 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("D")) {
					weightedGpaSum += (1.0 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("D-")) {
					weightedGpaSum += (0.67 * course.getCredit());
					creditHourSum += course.getCredit();
				}
				else if( course.getGrade().equals("F")) {
					weightedGpaSum += (0.0 * course.getCredit());
					creditHourSum += course.getCredit();
				}
			}
		}
		calcGrade = weightedGpaSum/creditHourSum;

		return df.format(calcGrade);
	}
}
