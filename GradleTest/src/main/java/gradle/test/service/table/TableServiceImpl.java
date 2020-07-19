package gradle.test.service.table;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gradle.test.entity.table.TableManager;
import gradle.test.repository.ContentsTableRepository;
import gradle.test.repository.TableManagerRepository;

@Service
public class TableServiceImpl implements TableService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TableManagerRepository tableManagerRepository;

	@Autowired
	private ContentsTableRepository contentsTableRepository;

	public void createTableManagerTable(Integer id) {
		// booleanが返ってきているが未対応(ロジックの切り分け方を見直した方がいい)
		tableManagerRepository.createTableManagerTable(id);
	}

	@Transactional
	public void initializeTableManagerTable(Integer id) {
		try {
			int affectedRows = tableManagerRepository.initializeTableManagerTable(id);
			if (affectedRows != 5) {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			System.out.println("failed to initialize tablemanager");
			e.printStackTrace();
		}
	}

	/**
	 * ユーザが持つ削除されていないコンテンツテーブルの数を取得する
	 * @param id
	 * @return
	 */
	public int countContentsTables(Integer id) {
		return tableManagerRepository.countContentsTables(id);
	}

	/**
	 * contentstableテーブルを作成する
	 * @param id
	 * @param tableNum
	 */
	public void createContentsTable(Integer id, int tableNum) {
		contentsTableRepository.createTableManagerTable(id, tableNum);
	}

	/**
	 * contentstableテーブルを初期化する
	 * @param id
	 * @param tableNum
	 */
	@Transactional
	public void initializeContentsTable(Integer id, int tableNum) {
		try {
			contentsTableRepository.deleteFromContentsTableTable(id, tableNum);
			System.out.println("デリートしたよ");
			if (contentsTableRepository.initializeContentsTableTable(id, tableNum) != 1) {
				throw new RuntimeException();
			}
			System.out.println("サービスで初期化完了");
		} catch (RuntimeException e) {
			System.out.println("failed to initialize contentstable");
			e.printStackTrace();
		}
	}

	public List<TableManager> selectAllFromTableManager(Integer id) {
		return tableManagerRepository.getTableManager(id);
	}

}
