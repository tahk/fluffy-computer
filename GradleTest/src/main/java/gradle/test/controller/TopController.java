package gradle.test.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gradle.test.model.CalenderEnum.CalenderIndex;
import gradle.test.model.LoginFormModel;
import gradle.test.model.RegisterFormModel;
import gradle.test.model.User;
import gradle.test.service.ChoreService;

@Controller
public class TopController {

	@Autowired
	private ChoreService choreService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/")
	public String showTop() {
		return "top";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		if (model.getAttribute("registerFormModel") == null) {
			model.addAttribute("registerFormModel", new RegisterFormModel());
		}
		choreService.setCalender(CalenderIndex.YEAR, 1900, 2020);
		choreService.setCalender(CalenderIndex.MONTH, 1, 12);
		choreService.setCalender(CalenderIndex.DAY, 1, 31);
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
			return "register";
		}
		session.setAttribute("registerFormModel", registerFormModel);
		registerFormModel.setSex(choreService.sexValueConverter(registerFormModel.getSex()));
		return "register";
	}

	@PostMapping("/register")
	public String executeRegister(Model model, HttpSession session) {
		if (session.getAttribute("registerFormModel") == null) {
			return "register";
		}
		RegisterFormModel registerFormModel = (RegisterFormModel) session.getAttribute("registerFormModel");
		User user = modelMapper.map(registerFormModel, User.class);
		
		return "register_complete";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		if (model.getAttribute("loginFormModel") == null) {
			model.addAttribute("loginFormModel", new LoginFormModel());
		}
		return "login";
	}

	@GetMapping("/memorandum")
	public String showMemorandum() {
		return "memorandum";
	}

}
