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

import gradle.test.entity.table.ContentsTable;

@Repository
public class ContentsTableRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private class ContentsTableRowMapper extends BeanPropertyRowMapper<ContentsTable> {
		@Override
		public ContentsTable mapRow(ResultSet rs, int rowNum) throws SQLException {
			ContentsTable contentsTable = new ContentsTable();
			contentsTable.setId(rs.getInt(1));
			contentsTable.setColumn1(rs.getString(2));
			contentsTable.setColumn2(rs.getString(3));
			contentsTable.setColumn3(rs.getString(4));
			contentsTable.setColumn4(rs.getString(5));
			contentsTable.setColumn5(rs.getString(6));
			contentsTable.setDeletedAt(rs.getString(7));
			contentsTable.setCreatedAt(rs.getString(8));
			return contentsTable;
		}
	}

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

	/**
	 * 指定したcontentstableテーブルを取得します。
	 * @param userId
	 * @param TableId
	 * @return
	 */
	public List<ContentsTable> findAllFromContentsTableByTableId(Integer userId, Integer tableId) {
		String sql = "SELECT * FROM " + String.valueOf(userId) + "_contentstable_" + String.valueOf(tableId) + "  WHERE deleted_at IS NULL";
		System.out.println(sql);
		return jdbcTemplate.query(sql, new ContentsTableRowMapper());
	}


	/**
	 * contentstableテーブルをcreateするSQL文を作成します。
	 * @param id
	 * @param tableNum
	 * @return
	 */
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
