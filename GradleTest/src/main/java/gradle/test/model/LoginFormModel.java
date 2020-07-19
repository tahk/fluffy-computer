package gradle.test.model;

import javax.validation.constraints.NotEmpty;

import gradle.test.messages.Messages;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFormModel {

	@NotEmpty(message = Messages.ERRMSG_01)
	private String userId;

	@NotEmpty(message = Messages.ERRMSG_01)
	private String password;

}
