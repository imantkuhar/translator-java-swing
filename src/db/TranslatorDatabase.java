package db;

import model.Word;

import java.sql.*;

/**
 * Created by imant
 */
public class TranslatorDatabase {

    private Connection connection = null;
    private static TranslatorDatabase instance;

    public static TranslatorDatabase getInstance() {
        if (instance == null)
            instance = new TranslatorDatabase();
        return instance;
    }

    private TranslatorDatabase() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void createConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:/home/roma/Рабочий " + "стол/database.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writeToDB(Connection connection, String languageFrom, String languageTo, String inputWord, Word word) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO  'words' ('langFrom', 'langTo', 'word', 'translatedWord') VALUES ('" + languageFrom + "', '" + languageTo + "', '" + inputWord + "', '" + word + "')");
        } catch (SQLException e) {
            System.out.println("SQLException");
        }
    }
}

