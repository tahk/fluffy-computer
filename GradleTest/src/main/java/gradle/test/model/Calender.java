package gradle.test.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Calender {

	private List<String> yearList = new ArrayList<String>();
	private List<String> monthList = new ArrayList<String>();
	private List<String> dayList = new ArrayList<String>();
	private int yearListVersion = 0;
	private int monthListVersion = 0;
	private int dayListVersion = 0;

	public void addYear(String year) {
		this.yearList.add(year);
	}

	public void addMonth(String month) {
		this.monthList.add(month);
	}

	public void addDay(String day) {
		this.dayList.add(day);
	}

	public void yearListVersionUp() {
		this.yearListVersion++;
	}

	public void monthListVersionUp() {
		this.monthListVersion++;
	}

	public void dayListVersionUp() {
		this.dayListVersion++;
	}



}
