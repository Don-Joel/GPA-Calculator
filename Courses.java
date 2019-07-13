/**
 * @author Joel Tavarez
 * @workedwith mm6md (Michael Masner)
 * 
 */

import java.util.ArrayList;

public class Courses {

		//fields
		private int credit = 0;
		private String name = "";
		private String grade = "";
		private String status = "";


		//the constructor for all kinds of possible inputs

		public Courses(int credit, String name, String grade, String status) {
		this.credit = credit;
		this.name = name;
		this.grade = grade;
		this.status = status;
		}

		//getters for all four fields
		public int getCredit() {
		return credit;
		}

		public String getName() {
		return name;
		}

		public String getGrade() {
		return grade;
		}

		public String getStatus() {
		return status;
		}
		

}

