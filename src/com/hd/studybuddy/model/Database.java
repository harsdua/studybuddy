
package com.hd.studybuddy.model;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * A class represeting the database of the application
 */

public class Database {
	static String URL = "jdbc:sqlite:studybuddy.db";
	private static Database singleton;

	protected Database() {
		createTables();
	}

	public static Database getSingleton() {
		if (singleton == null) {
			singleton = new Database();
		}
		return singleton;
	}

	private static final String CREATE_USERS_TABLE_SQL =
			"CREATE TABLE IF NOT EXISTS users (" +
					"username VARCHAR(50) PRIMARY KEY," +
					"password VARCHAR(50) NOT NULL" +
					")";


	private static final String CREATE_DECKS_TABLE_SQL =
			"CREATE TABLE IF NOT EXISTS decks ("
					+ "id INTEGER PRIMARY KEY,"
					+ "username VARCHAR(50) NOT NULL,"
					+ "deckName VARCHAR(50) NOT NULL,"
					+ "deck BLOB NOT NULL,"
					+ "UNIQUE(username, deckName)"
					+ ")";

	public void createTables() {
		try (Connection conn = DriverManager.getConnection(URL);
			 Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(CREATE_USERS_TABLE_SQL);
			stmt.executeUpdate(CREATE_DECKS_TABLE_SQL);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error creating tables: " + e.getMessage());
		}
	}

	public void addUser(String name, String passwd) {
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.setString(2, passwd);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error adding user: " + e.getMessage());
		}
	}


	public void deleteUser(String name) {
		String sql = "DELETE FROM users WHERE username = ?";

		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error deleting user: " + e.getMessage());
		}
	}

	public String[] getUser(String key) {
		String sql = "SELECT * FROM users WHERE username = ?";
		String[] user = new String[2];

		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, key);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				user[0] = rs.getString("username");
				user[1] = rs.getString("password");
			}
		} catch (SQLException e) {
			System.err.println("Error getting user: " + e.getMessage());
		}
		return user;
	}

	public void modifyPassword(String user, String pass) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pass);
			pstmt.setString(2, user);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Error modifying password: " + e.getMessage());
		}
	}

	public boolean addDeck(String username, Deck deckToSave) {
		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO decks(username, deckName, deck) VALUES (?, ?, ?)")) {
			pstmt.setString(1, username);
			pstmt.setString(2, deckToSave.getTitle());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(deckToSave);
			oos.flush();
			pstmt.setBytes(3, baos.toByteArray());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeDeck(String username, String deckName) {
		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM decks WHERE username = ? AND deckName = ?")) {
			pstmt.setString(1, username);
			pstmt.setString(2, deckName);
			int rowsDeleted = pstmt.executeUpdate();
			if (rowsDeleted == 0) {
				System.out.println("No deck found with username " + username + " and deck name " + deckName);
			} else {
				System.out.println("Deck with username " + username + " and deck name " + deckName + " has been removed from the database.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public ArrayList<Deck> retreiveAllDecksFromUser(String username) {
		ArrayList<Deck> decks = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement("SELECT deckName, deck FROM decks WHERE username = ?")) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				byte[] deckBytes = rs.getBytes("deck");
				ByteArrayInputStream bais = new ByteArrayInputStream(deckBytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				Deck deck = (Deck) ois.readObject();
				decks.add(deck);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decks;
	}

	public Deck retrieveDeckFromUser(String username, String deckName) {
		Deck deck = null;
		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement("SELECT deck FROM decks WHERE username = ? AND deckName = ?")) {
			pstmt.setString(1, username);
			pstmt.setString(2, deckName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				byte[] deckBytes = rs.getBytes("deck");
				ByteArrayInputStream bais = new ByteArrayInputStream(deckBytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				deck = (Deck) ois.readObject();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deck;
	}

	public void updateExistingDeck(String username, Deck deck) {
		try (Connection conn = DriverManager.getConnection(URL);
			 PreparedStatement pstmt = conn.prepareStatement("UPDATE decks SET deck = ? WHERE username = ? AND deckName = ?")) {

			//TODO: Make function that serializes and call it instead of the code following. Change also addDeck accordingly.
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(deck);
			byte[] deckBytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(deckBytes);


			pstmt.setBinaryStream(1, bais, deckBytes.length);
			pstmt.setString(2, username);
			pstmt.setString(3, deck.getTitle());
			int rowsUpdated = pstmt.executeUpdate();
			if (rowsUpdated == 0) {
				System.out.println("No deck found with username " + username + " and deck name " + deck.getTitle());
			} else {
				System.out.println("Deck with username " + username + " and deck name " + deck.getTitle() + " has been updated in the database.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


