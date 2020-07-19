package gradle.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gradle.test.dto.UserDto;
import gradle.test.entity.user.User;
import gradle.test.model.CalenderEnum.CalenderIndex;
import gradle.test.model.LoginFormModel;
import gradle.test.model.LoginSessionModel;
import gradle.test.model.LoginUser;
import gradle.test.model.RegisterFormModel;
import gradle.test.service.ChoreService;
import gradle.test.service.UserServiceImpl;
import gradle.test.service.table.TableServiceImpl;

@Controller
public class TopController {

	@Autowired
	private ChoreService choreService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private TableServiceImpl tableService;

	@Autowired
	private LoginSessionModel loginSession;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/")
	public String showTop() {
		return "top";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		if (model.getAttribute("registerFormModel") == null) {
			model.addAttribute("registerFormModel", new RegisterFormModel());
		}
		if (choreService.calenderStatus() == 0) {
			choreService.setCalender(CalenderIndex.YEAR, 1900, 2020);
			choreService.setCalender(CalenderIndex.MONTH, 1, 12);
			choreService.setCalender(CalenderIndex.DAY, 1, 31);
		}
		model.addAttribute("yearList", choreService.getCalender(CalenderIndex.YEAR));
		model.addAttribute("monthList", choreService.getCalender(CalenderIndex.MONTH));
		model.addAttribute("dayList", choreService.getCalender(CalenderIndex.DAY));
		model.addAttribute("sexList", choreService.getSexList());
		return "register";
	}

	@PostMapping("/confirm")
	public String confirmRegister(@Valid @ModelAttribute RegisterFormModel registerFormModel, BindingResult br,
			Model model, HttpSession session) {
		if (br.hasErrors()) {
			model.addAttribute("yearList", choreService.getCalender(CalenderIndex.YEAR));
			model.addAttribute("monthList", choreService.getCalender(CalenderIndex.MONTH));
			model.addAttribute("dayList", choreService.getCalender(CalenderIndex.DAY));
			model.addAttribute("sexList", choreService.getSexList());
			return "register";
		}
		// パスワードをハッシュ化
		registerFormModel.setPassword(passwordEncoder.encode(registerFormModel.getPassword()));
		// ここでセッションにセット
		session.setAttribute("registerFormModel", registerFormModel);
		// 表示用に性別の値を変換
		model.addAttribute("sex", choreService.sexValueConverter(registerFormModel.getSex()));
		return "register_conf";
	}

	@PostMapping("/register")
	public String executeRegister(Model model, HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("registerFormModel") == null) {
			return "register";
		}
		if (request.getParameter("confToken") == null) {
			return "register";
		}
		RegisterFormModel registerFormModel = (RegisterFormModel) session.getAttribute("registerFormModel");
//		UserDto user = modelMapper.map(registerFormModel, UserDto.class);
		UserDto user = new UserDto();
		user.setFirstName(registerFormModel.getFirstName());
		user.setLastName(registerFormModel.getLastName());
		user.setDateOfBirth(registerFormModel.getDateOfBirth());
		user.setSex(registerFormModel.getSex());
		user.setUserId(registerFormModel.getUserId());
		user.setUserName(registerFormModel.getUserName());
		user.setPassword(registerFormModel.getPassword());
		user.setRegDate(choreService.getDate());
		user.setLstUpd(choreService.getTime());
		user.setVersion(0);
		userService.createUser(user);

		// 登録したユーザのidを取得
		List<User> fetched = userService.findUserByUserId(user.getUserId());
		Integer id = fetched.get(0).getId();
		// tablemanagerテーブルを作成
		tableService.createTableManagerTable(id);
		// tablemanagerテーブルを初期化
		tableService.initializeTableManagerTable(id);
		// 1つ目のcontentstableテーブルを作成
		tableService.createContentsTable(id, 1);
		// 作成したcontentstableテーブルを初期化
		tableService.initializeContentsTable(id, 1);
		return "register_complete";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		if (model.getAttribute("loginFormModel") == null) {
			model.addAttribute("loginFormModel", new LoginFormModel());
		}
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginFormModel loginFormModel, BindingResult br, Model model, HttpSession session) {
		if (br.hasErrors()) {
			return "login";
		}
		// ログイン処理
		User foundUser = userService.findUserByUserId(loginFormModel.getUserId()).get(0);
		if (passwordEncoder.matches(loginFormModel.getPassword(), passwordEncoder.encode(loginFormModel.getPassword()))) {
			LoginUser loginUser = new LoginUser();
			loginUser.setId(foundUser.getId());
			loginUser.setUserId(foundUser.getUserId());
			loginUser.setUserName(foundUser.getUserName());
			loginSession.doLogin(loginUser);
			model.addAttribute("loginUser", loginSession.getLoginUser());
		} else {
			return "login";
		}
		return "menu";
	}


	@GetMapping("/memorandum")
	public String showMemorandum() {
		return "memorandum";
	}

}
