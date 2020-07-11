package gradle.test.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser implements Cloneable {

	private String userId;
	private String userName;

	@Override
	public LoginUser clone() {
		try {
			LoginUser clone = (LoginUser) super.clone();
			clone.setUserId(this.userId);
			clone.setUserName(this.userName);
			return clone;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}


}
