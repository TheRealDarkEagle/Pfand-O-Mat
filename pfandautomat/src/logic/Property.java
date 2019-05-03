package logic;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import resources.PropertyPath;

public class Property {

	private static Properties loadProperty(String path) {
		try (FileInputStream input = new FileInputStream(path)) {
			Properties prop = new Properties();
			prop.load(input);
			return prop;

		} catch (Exception e) {
			System.out.println("[Property.loadProperty] FEHLER! Dateipfad ist ungültig!");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static Properties getBrand() {
		return loadProperty(PropertyPath.BRANDS.path());
	}

	public static Properties getVolumen() {
		return loadProperty(PropertyPath.VOLUMEN.path());
	}

	public static Properties getPfand() {
		return loadProperty(PropertyPath.PFAND.path());
	}

	public static Properties allowedTypes() {
		return loadProperty(PropertyPath.ALLOWEDTYPES.path());
	}

	public static boolean isHandle() {
		return new Random().nextBoolean();
	}

	public static Properties getLanguage() {
		return loadProperty(PropertyPath.LANGUAGE.path());
	}

	public static Properties getFlags() {
		return loadProperty(PropertyPath.FLAGS.path());
	}
}
