public class SpringJDBC {
    
    @Autowired
    private DataSource   dataSource;
    private JdbcTemplate jdbcTemplate;

    @Resource(name = "certainDB")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User findOneByUsername(String username) {
        String sql = "SELECT * FROM login WHERE username= ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{username}, new UserMapper());
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM login";
        return this.jdbcTemplate.query(sql, new UserMapper());
    }

    public User findOneByActivationKey(String key) {
        String sql = "select * from login where activation_key = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{key}, new UserMapper());
    }
    
    public User findOneByResetKey(String key) {
        String sql = "select * from login where reset_key = ?";
        return this.jdbcTemplate.queryForObject(sql, new Object[]{key}, new UserMapper());
    }
      
    public void save(User u) {
        String sql = "INSERT INTO login VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, u.getUsername(), u.getPassword(), u.getEmail(),
                            u.getFirstName(), u.getActivated(), u.getActivationKey());
    }
    
    
    

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User emp = new User();
            emp.setUsername(rs.getString("username"));
            emp.setPassword(rs.getString("password"));
            emp.setEmail(rs.getString("email"));
            emp.setFirstName(rs.getString("first_name"));
            emp.setMiddleName(rs.getString("middle_name"));
            emp.setLastName(rs.getString("last_name"));
            return emp;
        }
    }
}