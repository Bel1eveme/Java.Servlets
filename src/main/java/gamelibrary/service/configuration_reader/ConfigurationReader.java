package gamelibrary.service.configuration_reader;

import gamelibrary.model.jdbc.JdbcConnectionPoolInfo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigurationReader {

    private static URL urlToDbProperties = ConfigurationReader.class.getClassLoader().getResource("/properties/database.properties");

    public static JdbcConnectionPoolInfo readConnectionPoolInfo(){
        if(urlToDbProperties != null) {
            String pathToDbProperties = urlToDbProperties.getPath();
            try (InputStream input = new FileInputStream(pathToDbProperties)) {
                Properties properties = new Properties();
                properties.load(input);

                String url = properties.getProperty("url");
                String user = properties.getProperty("user");
                String password = properties.getProperty("password");
                int connectionsCount = Integer.parseInt(properties.getProperty("connectionsCount"));
                return new JdbcConnectionPoolInfo(url, user, password, connectionsCount);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        } else{
            return null;
        }
    }
}
