package gradle.test.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class LoginSessionModel {

	private LoginUser loginUser;

	// ログイン判定
	public boolean isLogin() {
		if (loginUser != null) {
			return true;
		}
		return false;
	}

	public LoginUser getLoginUser() {
		// 参照渡し回避のためクローンを作成して返す
		return loginUser.clone();
	}

	public void doLogin(LoginUser loginUser) {
		this.loginUser = loginUser;
	}

	public void doLogout() {
		this.loginUser = null;
	}


}
