package gradle.test.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Integer id;

	private String firstName;

	private String lastName;

	private String bYear;

	private String bMonth;

	private String bDay;

	private String sex;

	private String userId;

	private String userName;

	private Date reg_date;

	private Timestamp lst_upd;

	private Integer version;

}
