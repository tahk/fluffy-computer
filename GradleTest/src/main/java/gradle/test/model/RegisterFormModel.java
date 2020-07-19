package gradle.test.model;

import java.text.DateFormat;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import gradle.test.messages.Messages;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFormModel {

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 20, message = "Input less than {max} letters.")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String firstName;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 20, message = "Input less than {max} letters.")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String lastName;

	private String dateOfBirth;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String bYear;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String bMonth;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String bDay;

	private String sex;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 20, message = "Input less than {max} letters.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String userId;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 20, message = "Input less than {max} letters.")
	private String userName;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(min = 8, max=20, message = "Input less than {max} letters.")
	private String password;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String passwordConf;

	public void setBYear(String bYear) {
		this.bYear = String.format("%4s", bYear).replace(" ", "0");
	}

	public void setBMonth(String bMonth) {
		this.bMonth = String.format("%2s", bMonth).replace(" ", "0");
	}

	public void setBDay(String bDay) {
		this.bDay = String.format("%2s", bDay).replace(" ", "0");
	}

	@AssertTrue(message = Messages.ERRMSG_02)
	public boolean isPwSameToPwC() {
		if (this.password == null | this.passwordConf == null) {
			return true;
		}
		return this.password.equals(this.passwordConf);
	}

	@AssertTrue(message = Messages.ERRMSG_03)
	public boolean isValidDate() {
		if (this.bYear.equals("") | this.bMonth.equals("") | this.bDay.equals("")) {
			return true;
		}
		// 日付の妥当性チェック
		String dateOfBirth = this.bYear + "/" + this.bMonth + "/" + this.bDay;
		DateFormat format = DateFormat.getDateInstance();
		format.setLenient(false);
		try {
			format.parse(dateOfBirth);
			// 日付が有効なとき値をセット
			setDateOfBirth(dateOfBirth);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
