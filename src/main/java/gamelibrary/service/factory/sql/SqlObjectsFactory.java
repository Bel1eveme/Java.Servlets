package gamelibrary.service.factory.sql;

import gamelibrary.service.jdbc.implementations.CustomJdbcConnectionPool;
import gamelibrary.service.jdbc.interfaces.JdbcConnectionPool;
import gamelibrary.service.sql.implementations.CustomEntitySqlExecutor;
import gamelibrary.service.sql.implementations.CustomSqlExecutor;
import gamelibrary.service.sql.interfaces.EntitySqlExecutor;
import gamelibrary.service.sql.interfaces.SqlExecutor;

public class SqlObjectsFactory {
    private SqlObjectsFactory(){}

    public static JdbcConnectionPool getJdbcConnectionPool(){
        return CustomJdbcConnectionPool.getInstance();
    }

    public static SqlExecutor getSqlExecutor(){
        return CustomSqlExecutor.getInstance();
    }

    public static EntitySqlExecutor getEntitySqlExecutor(){
        return CustomEntitySqlExecutor.getInstance();
    }
}
