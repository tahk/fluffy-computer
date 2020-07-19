package gradle.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContentsTableRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean createTableManagerTable(Integer id, int tableNum) {
		try {
			jdbcTemplate.execute(buildSqlForCreateContentsTableTable(id, tableNum));
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 指定したcontentstableテーブルの内容を全削除する
	 * @param id
	 * @param tableNum
	 * @return
	 */
	public int deleteFromContentsTableTable(Integer id, int tableNum) {
		String sql = "DELETE FROM " + String.valueOf(id) + "_contentstable_" + String.valueOf(tableNum);
		return jdbcTemplate.update(sql);
	}

	/**
	 * contentstableテーブルを初期化
	 * @param id
	 * @return
	 */
	public int initializeContentsTableTable(Integer id, int tableNum) {
		String sql = "INSERT INTO " + String.valueOf(id) + "_contentstable_" + String.valueOf(tableNum) + " VALUES(0, '', '', '', '', '', null, current_timestamp)";
		int affectedRows = 0;
		try {
			affectedRows += jdbcTemplate.update(sql);
			System.out.println("インサートしたよ");
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	private static String buildSqlForCreateContentsTableTable(Integer id, int tableNum) {
		StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE " + String.valueOf(id) + "_contentstable_" + String.valueOf(tableNum) + " (");
			sb.append("id Integer AUTO_INCREMENT PRIMARY KEY");
			sb.append(", column1 varchar(20)");
			sb.append(", column2 varchar(20)");
			sb.append(", column3 varchar(20)");
			sb.append(", column4 varchar(20)");
			sb.append(", column5 varchar(20)");
			sb.append(", deleted_at timestamp");
			sb.append(", created_at timestamp");
			sb.append(")");
		return sb.toString();
	}

}
