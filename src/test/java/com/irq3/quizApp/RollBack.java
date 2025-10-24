package com.irq3.quizApp;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
class RollBack {
    @Autowired
    DataSource dataSource;

    @Test
    public void rollbackLastChange() throws Exception {
        Liquibase liquibase = new Liquibase(
                "db",
                new ClassLoaderResourceAccessor(),
                DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                        new JdbcConnection(dataSource.getConnection())
                )
        );
        liquibase.rollback(1, "");
    }
}
