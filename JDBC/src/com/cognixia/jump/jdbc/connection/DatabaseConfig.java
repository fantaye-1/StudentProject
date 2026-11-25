package com.cognixia.jump.jdbc.connection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

/**
 * Database connection settings, resolved without any credentials in source code.
 *
 * Lookup order for each value (first non-blank wins):
 * 1. Environment variables DB_URL, DB_USERNAME, DB_PASSWORD
 * 2. resources/config.properties (keys url, username, password); this file is
 *    git-ignored, copy resources/config.properties.example to create it
 * 3. Defaults: jdbc:mysql://localhost:3306/university, user "root", empty password
 */
public final class DatabaseConfig {

	public static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/university";
	public static final String DEFAULT_USERNAME = "root";
	public static final String DEFAULT_PASSWORD = "";
	public static final Path DEFAULT_PROPERTIES_FILE = Path.of("resources", "config.properties");

	private final String url;
	private final String username;
	private final String password;

	public DatabaseConfig(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/** Settings from the real environment and resources/config.properties. */
	public static DatabaseConfig fromEnvironment() {
		return resolve(System.getenv(), loadProperties(DEFAULT_PROPERTIES_FILE));
	}

	/** Resolves settings from the given environment and properties (both may be empty). */
	public static DatabaseConfig resolve(Map<String, String> env, Properties props) {
		String url = firstNonBlank(env.get("DB_URL"), props.getProperty("url"), DEFAULT_URL);
		String username = firstNonBlank(env.get("DB_USERNAME"), props.getProperty("username"), DEFAULT_USERNAME);
		String password = firstNonBlank(env.get("DB_PASSWORD"), props.getProperty("password"), DEFAULT_PASSWORD);
		return new DatabaseConfig(url, username, password);
	}

	/** Loads a properties file, returning empty properties if it does not exist or cannot be read. */
	public static Properties loadProperties(Path file) {
		Properties props = new Properties();
		if (Files.isRegularFile(file)) {
			try (InputStream in = Files.newInputStream(file)) {
				props.load(in);
			} catch (IOException e) {
				System.err.println("Could not read " + file + ": " + e.getMessage());
			}
		}
		return props;
	}

	private static String firstNonBlank(String... values) {
		for (String value : values) {
			if (value != null && !value.isBlank()) {
				return value.trim();
			}
		}
		return "";
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		// never print the password
		return "DatabaseConfig[url=" + url + ", username=" + username + "]";
	}
}
