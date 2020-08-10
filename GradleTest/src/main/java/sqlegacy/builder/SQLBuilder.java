package sqlegacy.builder;

import java.util.ArrayList;
import java.util.List;

import sqlegacy.common.SQLegacyConst;
import sqlegacy.common.SQLegacyEnum.ConstructType;
import sqlegacy.util.SQLegacyUtils;


public class SQLBuilder {

	private StringBuilder sql;

	private ConstructType constructType;

	private List<String> aliasList = new ArrayList<String>();

	public SQLBuilder() {
		sql = new StringBuilder();
	}

	public SQLBuilder(ConstructType constructType, String tableName) {
		sql = new StringBuilder();
		this.constructType = constructType;
	}

	public void select() {
		sql.append(SQLegacyConst.SELECT );
	}

	public void select(String... column) {
		select();
		column();
	}

	public void space() {
		sql.append(SQLegacyConst.SPACE);
	}

	public void comma() {
		sql.append(SQLegacyConst.COMMA);
	}

	public void from() {
		sql.append(SQLegacyConst.FROM);
	}

	public void tableName(String... tableName) {
		sql.append(SQLegacyUtils.arrayConnector(tableName));
	}

	public void tableNameWithAlias(String tableName, String alias) {

	}

	public void tableNameWithAlias(String[] param) {

	}

	public void column(String... column) {
		sql.append(SQLegacyUtils.arrayConnector(column));
	}

	/**
	 * 作成したSQL文をString型で返す
	 * @return String型のSQL文
	 */
	public String toString() {
		return sql.toString();
	}

}
