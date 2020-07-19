package gradle.test.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				calender.yearListVersionUp();
				break;
			case MONTH:
				calender.addMonth(String.format("%2d", i));
				calender.monthListVersionUp();
				break;
			case DAY:
				calender.addDay(String.format("%2d", i));
				calender.dayListVersionUp();
				break;
			}
		}
	}

	public int calenderStatus() {
		int i = 0;
		if (calender.getYearList().size() != 0) {
			i += 4;
		}
		if (calender.getMonthList().size() != 0) {
			i += 2;
		}
		if (calender.getDayList().size() != 0) {
			i += 1;
		}
		return i;
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

	public String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}

	public String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}

}
