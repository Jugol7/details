package zlp.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zlp
 * @date 2019-11-06 14:34
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DatasourceApp {
    public static void main(String[] args) {
SpringApplication.run(DatasourceApp.class, args);
	}
}
