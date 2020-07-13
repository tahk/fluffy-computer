package gradle.test.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TableManagerRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * tablemanagerテーブルを生成
	 * @param id
	 * @return
	 */
	public boolean createTableManagerTable(Integer id) {
		try {
			jdbcTemplate.execute(buildSqlForCreateTableManagerTable(id));
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * tablemanagerテーブルを初期化
	 * @param id
	 * @return
	 */
	public int initializeTableManagerTable(Integer id) {
		String sqlForActive = "INSERT INTO " + String.valueOf(id) + "_tablemanager VALUES(0, 'テーブル1', 2, 'カラム1', 'カラム2', null, null, null, 0, current_timestamp)";
		String sqlForInactive = "INSERT INTO " + String.valueOf(id) + "_tablemanager VALUES(0, null, 0, null, null, null, null, null, 1, null)";
		int affectedRows = 0;
		try {
			affectedRows += jdbcTemplate.update(sqlForActive);
			for (int i = 0; i < 4; i++) {
				affectedRows += jdbcTemplate.update(sqlForInactive);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	public int countContentsTables(Integer id) {
		String sql = "SELECT COUNT(*) FROM :tableName WHERE del_flg = 0";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("tableName", String.valueOf(id) + "_tablemanager");
		Integer result = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
		try {
			return (int) result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	private static String buildSqlForCreateTableManagerTable(Integer id) {
		StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE " + String.valueOf(id) + "_tablemanager (");
			sb.append("id Integer AUTO_INCREMENT PRIMARY KEY");
			sb.append(", name varchar(20)");
			sb.append(", col_count Integer");
			sb.append(", col_name1 varchar(20)");
			sb.append(", col_name2 varchar(20)");
			sb.append(", col_name3 varchar(20)");
			sb.append(", col_name4 varchar(20)");
			sb.append(", col_name5 varchar(20)");
			sb.append(", del_flg Integer");
			sb.append(", created_at timestamp");
			sb.append(")");
		return sb.toString();
	}


}
