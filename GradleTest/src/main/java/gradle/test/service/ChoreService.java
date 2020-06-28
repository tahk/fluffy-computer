package gradle.test.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradle.test.model.Calender;
import gradle.test.model.CalenderEnum.CalenderIndex;
import gradle.test.model.SexEnum.SexIndex;

@Service
public class ChoreService {

	@Autowired
	private Calender calender;

	public List<String> getCalender(CalenderIndex type) {
		switch(type) {
		case YEAR:
			return calender.getYearList();
		case MONTH:
			return calender.getMonthList();
		case DAY:
			return calender.getDayList();
		default:
			return new ArrayList<String>();
		}
	}

	public void setCalender(CalenderIndex type, int from, int to) {
		for (int i = from; i <= to; i++) {
			switch(type) {
			case YEAR:
				calender.addYear(String.format("%4d", i));
				break;
			case MONTH:
				calender.addMonth(String.format("%2d", i));
				break;
			case DAY:
				calender.addDay(String.format("%2d", i));
				break;
			}
		}
	}

	public LinkedHashMap<String, String> getSexList() {
		LinkedHashMap<String, String> sexList = new LinkedHashMap<String, String>();
		for (SexIndex sex: SexIndex.values()) {
			sexList.put(sex.getValue(), sex.name());
		}
		return sexList;
	}

	public String sexValueConverter(String sex) {
		if (sex.length() == 1) {
			for (SexIndex si: SexIndex.values()) {
				if (si.getValue().equals(sex)) {
					return si.name();
				}
			}
		} else {
			for (SexIndex si: SexIndex.values()) {
				if (si.name().equals(sex)) {
					return si.getValue();
				}
			}
		}
		return "no selected";
	}

}
