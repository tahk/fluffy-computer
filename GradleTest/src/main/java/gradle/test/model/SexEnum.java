package gradle.test.model;

public class SexEnum {

	public enum SexIndex {
		male("m"),
		female("f"),
		other("o");

		private String value;

		private SexIndex (String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

}
