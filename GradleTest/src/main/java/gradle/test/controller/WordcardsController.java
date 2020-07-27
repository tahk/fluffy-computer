package gradle.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import gradle.test.entity.table.TableManager;
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
		model.addAttribute("tableManagerList", tableService.selectAllFromTableManager(loginSession.getLoginUser().getId()));
		model.addAttribute("countOfTables", countOfTables);
		return "wordcards_top";
	}

	@GetMapping("/add")
	public String addWordcards(Model model) {
		// ユーザのTableManagerに削除フラグが1のものがあるか調べ、ある場合はフラグ0にしてテーブル作成
		// なければエラー

		return "wordcards_top";
	}

	@GetMapping("/show/{tableName}")
	public String showWordcards(@PathVariable("tableName") String tableName, Model model) {
		Integer userId = loginSession.getLoginUser().getId();
		// 受け取ったテーブル名がユーザのTableManagerにあるか調べ、見つかったものを表示
		// 受け取ったテーブル名がユーザのTableManagerにあるか調べる
		List<TableManager> foundTable = tableService.findFromTableManagerByName(userId, tableName);
		if (foundTable.size() == 0) {
			return "error/404";
		}
		model.addAttribute("tableInfo", foundTable.get(0));
		// テーブルの中身を取得する
		model.addAttribute("contents", tableService.findAllFromContentsTableByTableId(userId, foundTable.get(0).getId()));
		return "wordcards_contents";
	}

}
