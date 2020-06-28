package gradle.test.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "b_year")
	private String bYear;

	@Column(name = "b_monthr")
	private String bMonth;

	@Column(name = "b_day")
	private String bDay;

	@Column(name = "sex")
	private String sex;

	@Column(name = "userId")
	private String userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "reg_date")
	private Date reg_date;

	@Column(name = "lst_upd")
	private Timestamp lst_upd;

	@Version
	@Column(name = "version")
	private Integer version;

}
