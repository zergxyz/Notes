Use Java configuration to define a JNDI datasource: 
``` java 
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@PropertySource("classpath:props.properties")
public class DataSourceConfigProd {

    @Autowired
    private Environment env;

    @Bean
    public AbstractPlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setDataSource(dataSource());
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));

        bean.setJpaProperties(props);
        return bean.getObject();
    }

    @Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("jdbc/yourJdbcGoesHere");
        return dataSource;
    }
}
```
