package org.HardCore.services.db;

import org.HardCore.process.control.exceptions.DatabaseException;

import java.sql.*;
import java.util.Properties;

public class JDBCConnection {
    private static JDBCConnection connection = null;
    private String login = "pwelte2s";
    private String passwort = "pwelte2s";
    private String url = "jdbc:postgresql://dumbo.inf.h-brs.de/pwelte2s";
    private Connection conn = null;

    public static JDBCConnection getInstance() throws DatabaseException {
        if (connection == null) {
            connection = new JDBCConnection();
        }
        return connection;
    }

    private JDBCConnection() throws DatabaseException {
        this.initConnection();

    }

    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.openConnection();
    }

    public void openConnection() throws DatabaseException {
        try {
            Properties props = new Properties();
            props.setProperty("user", login);
            props.setProperty("password", passwort);
            this.conn = DriverManager.getConnection(this.url, props);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler bei Zugriff auf die DB! Sichere Verbindung vorhanden?");
        }
    }

    public Statement getStatement() throws DatabaseException {
        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public PreparedStatement getPreparedStatement(String sql ) throws DatabaseException {
        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

