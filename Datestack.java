package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Datestack implements Comparable<Datestack> {// date stack that contains the date and each date pointes on
															// stack that contains the info of the martyr.
	GregorianCalendar date;
	Stack stack;

	public Datestack(GregorianCalendar date) {
		super();
		this.date = date;
		this.stack = new Stack();
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public Stack getStack() {
		return stack;
	}

	public void setStack(Stack s) {
		this.stack = s;
	}

	public int getCount() {
		return stack.getSize();
	}

	@Override
	public int compareTo(Datestack o) {
		return this.date.compareTo(o.date);
	}

	@Override
	public String toString() {
		return date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR)
				+ stack;
	}

}
