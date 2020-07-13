package gradle.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gradle.test.model.LoginSessionModel;
import gradle.test.service.table.TableServiceImpl;

@Controller
@RequestMapping("/wordcards")
public class WordcardsController {

	@Autowired
	private LoginSessionModel loginSession;

	@Autowired
	private TableServiceImpl tableService;

	@GetMapping("/")
	public String showTop(Model model) {
		int countOfTables = tableService.countContentsTables(loginSession.getLoginUser().getId());

		return "wordcards_top";
	}

}
