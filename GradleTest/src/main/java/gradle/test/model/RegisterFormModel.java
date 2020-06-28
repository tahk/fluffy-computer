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
	@Size(max = 10, message = "Input less than {max} letters.")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String firstName;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 10, message = "Input less than {max} letters.")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String lastName;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String year;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String month;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String day;

	private String sex;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 30, message = "Input less than {max} letters.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String userId;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(max = 30, message = "Input less than {max} letters.")
	private String userName;

	@NotEmpty(message = Messages.ERRMSG_01)
	@Size(min = 8, message = "Input less than {max} letters.")
	private String password;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String passwordConf;

	@AssertTrue(message = Messages.ERRMSG_02)
	public boolean isPwSameToPwC() {
		if (this.password == null | this.passwordConf == null) {
			return true;
		}
		return this.password.equals(this.passwordConf);
	}

	@AssertTrue(message = Messages.ERRMSG_03)
	public boolean isValidDate() {
		if (this.year.equals("") | this.month.equals("") | this.day.equals("")) {
			return true;
		}
		// 日付の妥当性チェック
		String dateOfBirth = this.year + "/" + this.month + "/" + this.day;
		DateFormat format = DateFormat.getDateInstance();
		format.setLenient(false);
		try {
			format.parse(dateOfBirth);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
