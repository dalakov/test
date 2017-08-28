package jpaspring;

import org.hsqldb.*;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.hibernate.jpa.HibernatePersistenceProvider;
import javax.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:persistence.properties")
public class ConfigInfrastructure {

    @Resource
    private Environment env;

    private final class HSQLProperties {
        private final String database = "server.database.0";
        private final String dbname = "server.dbname.0";
        private HsqlProperties getHsqlProperties() {
            HsqlProperties prop  = new HsqlProperties();
            prop.setProperty(database, env.getRequiredProperty(database));
            prop.setProperty(dbname, env.getRequiredProperty(dbname));
            return prop;
        }
    }

    private final class DataSourceProperties {
        private final String driverClassName = "ds.driverClassName";
        private final String url = "ds.url";
        private final String username = "ds.username";
        private final String password = "ds.password";
        private String getDriverClassName() { return env.getRequiredProperty(driverClassName); }
        private String getUrl() { return env.getRequiredProperty(url); }
        private String getUsername() { return env.getRequiredProperty(username); }
        private String getPassword() { return env.getRequiredProperty(password); }
    }

    private final class HibernateProperties {
        private final String dialect = "hibernate.dialect";
        private final String showSql = "hibernate.show_sql";
        private final String formatSql = "hibernate.format_sql";
        private final String hbm2ddl = "hibernate.hbm2ddl.auto";
        private final String useSqlComments = "hibernate.use_sql_comments";
        private Properties getHibernateProperties() {
            Properties prop = new Properties();
            prop.put(dialect, env.getRequiredProperty(dialect));
            prop.put(showSql, env.getRequiredProperty(showSql));
            prop.put(formatSql, env.getRequiredProperty(formatSql));
            prop.put(hbm2ddl, env.getRequiredProperty(hbm2ddl));
            prop.put(useSqlComments, env.getRequiredProperty(useSqlComments));
            return prop;
        }
    }

    private static final String PROP_ENTITYMANAGER_PACKAGES2SCAN = "entitymanager.packages2scan";

    private class HsqlServer extends Server {
        @PreDestroy
        private void hsqlShutdown() {
            shutdownWithCatalogs(Database.CLOSEMODE_IMMEDIATELY);
        }
    }

    @Bean (name = "hsqlServer")
    public HsqlServer hsqlServer() throws IOException, ServerAcl.AclFormatException {
        HsqlServer server = new HsqlServer();
        HSQLProperties prop = new HSQLProperties();
        server.setProperties(prop.getHsqlProperties());
        server.setLogWriter(null); // can use custom writer
        server.setErrWriter(null); // can use custom writer
        server.start();
        return server;
    }

    @Bean
    @DependsOn ("hsqlServer")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        DataSourceProperties prop = new DataSourceProperties();
        ds.setDriverClassName(prop.getDriverClassName());
        ds.setUrl(prop.getUrl());
        ds.setUsername(prop.getUsername());
        ds.setPassword(prop.getPassword());
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emf.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES2SCAN));
        HibernateProperties prop = new HibernateProperties();
        emf.setJpaProperties(prop.getHibernateProperties());
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }
}
