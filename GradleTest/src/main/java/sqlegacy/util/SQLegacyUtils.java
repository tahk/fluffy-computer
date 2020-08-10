package sqlegacy.util;

import sqlegacy.common.SQLegacyConst;

public class SQLegacyUtils {

	public static String sanitize(String rawLetters) {

		return "";
	}

	public static String arrayConnector(String[] target) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < target.length; i++) {
			if (i != 0) {
				sb.append(SQLegacyConst.COMMA);
			}
			sb.append(SQLegacyConst.SPACE);
			sb.append(target[i]);
		}
		return sb.toString();
	}

	public static String arrayConnector(String[] target, boolean isSanitizeNeeded) {

		return "";
	}



}
