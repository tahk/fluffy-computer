package gradle.test.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import gradle.test.entity.table.TableManager;

@Repository
public class TableManagerRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private class TableManagerRowMapper extends BeanPropertyRowMapper<TableManager> {
		@Override
		public TableManager mapRow(ResultSet rs, int rowNum) throws SQLException {
			TableManager tableManager = new TableManager();
			tableManager.setId(rs.getInt(1));
			tableManager.setName(rs.getString(2));
			tableManager.setColCount(rs.getInt(3));
			tableManager.setColName1(rs.getString(4));
			tableManager.setColName2(rs.getString(5));
			tableManager.setColName3(rs.getString(6));
			tableManager.setColName4(rs.getString(7));
			tableManager.setColName5(rs.getString(8));
			tableManager.setDelFlg(rs.getInt(9));
			tableManager.setCreatedAt(rs.getString(10));
			return tableManager;
		}
	}

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

	/**
	 * tablemanagerテーブルのうち削除フラグが0のものの数を取得します。
	 * @param id
	 * @return
	 */
	public int countContentsTables(Integer id) {
//		String sql = "SELECT COUNT(*) FROM :tableName WHERE del_flg = 0";
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue("tableName", String.valueOf(id) + "_tablemanager");
//		Integer result = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
		String sql = "SELECT COUNT(*) FROM " + String.valueOf(id) + "_tablemanager WHERE del_flg = 0";
		Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.println(result);
		try {
			return (int) result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * tablemanagerテーブルのうち削除フラグが0のものを全て取得します。
	 * @param id
	 * @return
	 */
	public List<TableManager> getTableManager(Integer id) {
		String sql = "SELECT * FROM " + String.valueOf(id) + "_tablemanager WHERE del_flg = 0";
		return jdbcTemplate.query(sql, new TableManagerRowMapper());
	}

	/**
	 * テーブル名でtablemanagerテーブルを取得します(削除フラグが0のもの)。
	 * @param id
	 * @param tableName
	 * @return
	 */
	public List<TableManager> findTableManagerByTableName(Integer id, String tableName) {
		String sql = "SELECT * FROM " + String.valueOf(id) + "_tablemanager WHERE name = '" + tableName + "' AND del_flg = 0" ;
		System.out.println(sql);
		return jdbcTemplate.query(sql, new TableManagerRowMapper());
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
