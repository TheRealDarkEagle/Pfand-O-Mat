package resources;

public enum PropertyPath {

	PFAND("src/resources/property/pfand.properties"), BRANDS("src/resources/property/brands.properties"),
	VOLUMEN("src/resources/property/volumen.properties"),
	ALLOWEDTYPES("src/resources/property/allowedtypes.properties"),
	LANGUAGE("src/resources/property/language.properties"), FLAGS("src/resources/property/languageFlags.properties");

	private String path;

	PropertyPath(String path) {
		this.path = path;
	}

	public String path() {
		return this.path;
	}

}
