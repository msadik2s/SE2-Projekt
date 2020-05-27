package org.HardCore.model.dao;

import org.HardCore.process.control.exceptions.DatabaseException;
import org.HardCore.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class AbstractDAO {

    protected Statement getStatement() {
        Statement statement = null;

        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return statement;
    }

    protected PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement = null;

        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        return statement;
    }
}

