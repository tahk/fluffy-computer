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

	public void addYear(String year) {
		this.yearList.add(year);
	}

	public void addMonth(String month) {
		this.monthList.add(month);
	}

	public void addDay(String day) {
		this.dayList.add(day);
	}

}
