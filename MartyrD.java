package application;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MartyrD implements Comparable<MartyrD> {// martyrd classthat contains the martyrs information that are
														// sorted depends on the martyrs name.
	private String name = "";
	private int age = 0;
	private String location = "";
	private GregorianCalendar date = new GregorianCalendar();
	private char gender = 'M';

	public MartyrD(String line) {
		String[] tkz = line.split(",");
		if (tkz.length == 5) {
			setName(tkz[0]);
			setAge(tkz[1].trim());
			setLocation(tkz[2]);
			setDate(makeDate(tkz[3].trim()));
			setGender(tkz[4].trim().charAt(0));
		} else
			System.out.println("Line error!! " + line);

	}

	public MartyrD(String name, int age, String location, GregorianCalendar date, char gender) {
		this.name = name;
		this.age = age;
		this.location = location;
		this.date = date;
		this.gender = gender;
	}

	private GregorianCalendar makeDate(String date) {
		String[] tkz = date.split("/");
		if (tkz.length == 3) {
			int year = Integer.parseInt(tkz[2].trim());
			int month = Integer.parseInt(tkz[0].trim()) - 1;
			int dayOfMonth = Integer.parseInt(tkz[1].trim());
			return new GregorianCalendar(year, month, dayOfMonth);
		}
		System.out.println("Date error!!! " + date);
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAge(String age) {
		if (age != null && age.trim().length() > 0)
			setAge(Integer.parseInt(age));
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		if (date != null)
			this.date = date;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		if (gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F')
			this.gender = gender;
	}

	@Override
	public int compareTo(MartyrD o) {
		return name.compareTo(o.getName());
	}

	@Override
	public String toString() {
		return name + ", " + age + ", " + location + ", " + date.get(Calendar.DAY_OF_MONTH) + "/"
				+ (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR) + ", " + gender;
	}

}
