package gradle.test.model;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private Integer id;

	private String firstName;

	private String lastName;

	private String age;

	private String sex;

	private String userId;

	private String userName;

	private Date reg_date;

	private Timestamp lst_upd;

}
