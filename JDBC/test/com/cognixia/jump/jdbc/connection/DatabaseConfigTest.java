package com.cognixia.jump.jdbc.connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class DatabaseConfigTest {

	private static Properties props(String url, String username, String password) {
		Properties p = new Properties();
		p.setProperty("url", url);
		p.setProperty("username", username);
		p.setProperty("password", password);
		return p;
	}

	@Test
	void usesDefaultsWhenNothingIsConfigured() {
		DatabaseConfig config = DatabaseConfig.resolve(Map.of(), new Properties());

		assertEquals(DatabaseConfig.DEFAULT_URL, config.getUrl());
		assertEquals("root", config.getUsername());
		assertEquals("", config.getPassword());
	}

	@Test
	void usesPropertiesFileValues() {
		DatabaseConfig config = DatabaseConfig.resolve(Map.of(),
				props("jdbc:mysql://db:3306/school", "app", "secret"));

		assertEquals("jdbc:mysql://db:3306/school", config.getUrl());
		assertEquals("app", config.getUsername());
		assertEquals("secret", config.getPassword());
	}

	@Test
	void environmentVariablesOverrideProperties() {
		Map<String, String> env = Map.of(
				"DB_URL", "jdbc:mysql://prod:3306/university",
				"DB_USERNAME", "envUser",
				"DB_PASSWORD", "envPass");

		DatabaseConfig config = DatabaseConfig.resolve(env, props("jdbc:mysql://db/x", "app", "secret"));

		assertEquals("jdbc:mysql://prod:3306/university", config.getUrl());
		assertEquals("envUser", config.getUsername());
		assertEquals("envPass", config.getPassword());
	}

	@Test
	void blankEnvironmentValuesFallBackToProperties() {
		DatabaseConfig config = DatabaseConfig.resolve(Map.of("DB_USERNAME", "  "),
				props("jdbc:mysql://db/x", "app", "secret"));

		assertEquals("app", config.getUsername());
	}

	@Test
	void toStringNeverContainsThePassword() {
		DatabaseConfig config = new DatabaseConfig("jdbc:mysql://db/x", "app", "s3cr3t");

		assertFalse(config.toString().contains("s3cr3t"));
		assertTrue(config.toString().contains("app"));
	}

	@Test
	void missingPropertiesFileGivesEmptyProperties(@TempDir Path dir) {
		assertTrue(DatabaseConfig.loadProperties(dir.resolve("nope.properties")).isEmpty());
	}

	@Test
	void loadsPropertiesFile(@TempDir Path dir) throws IOException {
		Path file = dir.resolve("config.properties");
		Files.writeString(file, "url=jdbc:mysql://h/u\nusername=bob\npassword=pw\n");

		Properties loaded = DatabaseConfig.loadProperties(file);

		assertEquals("bob", loaded.getProperty("username"));
		assertEquals("pw", loaded.getProperty("password"));
	}
}
