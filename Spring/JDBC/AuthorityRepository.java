package certain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import certain.domain.Authority;

@Repository
public class AuthorityRepository {

    @Autowired
    private DataSource                 dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "certainDB")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public ArrayList<Authority> getAuthorities(String username) {

        String sql = "select * from authority where username=:uname";
        ArrayList<Authority> aus = new ArrayList<Authority>();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("uname", username);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
        for (Map<String, Object> row : rows) {

            Authority a = new Authority();
            a.setName(row.get("name").toString());
            aus.add(a);
        }
        return aus;
    }
    
     public void savePatient(Patient p) {
        String sql =
                     "insert into ctn_adm_demo " + "(ctn_id, admission_date, last_name, middle_name, first_name,  age, gender, "
                         + "blood_type, height_cm, weight_kg, code_status, user_admit, hosp_code) "
                         + "values (:ctnid, :admDT, :lName, :mName, :fName, :age, :gender, :bt, :ht, :wt, :sts, :user, :hosp)";

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ctnid", p.getCtnID());
        parameters.put("admDT", p.getAdminDT());
        parameters.put("lName", p.getLastName());
        parameters.put("mName", p.getMiddleName());
        parameters.put("fName", p.getFirstName());
        parameters.put("age", p.getAge());
        parameters.put("gender", p.getGender());
        parameters.put("bt", p.getBloodType());
        parameters.put("ht", p.getHeight());
        parameters.put("wt", p.getWeight());
        parameters.put("sts", p.getCodeStatus());
        parameters.put("user", p.getUserAdmit());
        parameters.put("hosp", p.getHospCode());

        jdbcTemplate.update(sql, parameters);
    }

    public Patient getPatientByMRN(String ctnID) {
        try {
            String sql = "select * from ctn_adm_demo " + "where ctn_id = :ctnID  order by admission_date desc limit 1";
            SqlParameterSource namedParameters = new MapSqlParameterSource("ctnID", ctnID);
            Patient p = jdbcTemplate.queryForObject(sql, namedParameters, new RowMapper<Patient>() {
                public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Patient emp = new Patient();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (rs.getTimestamp("discharge_date") == null) {
                        emp.setDischargeDT(null);
                    } else {
                        emp.setDischargeDT(df.format(new Date(rs.getTimestamp("discharge_date").getTime())));
                    }
                    emp.setCtnID(rs.getString("ctn_id"));
                    emp.setEcptID(rs.getString("ctn_id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setMiddleName(rs.getString("middle_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setAdminDT(df.format(new Date(rs.getTimestamp("admission_date").getTime())));
                    emp.setHospCode(rs.getString("hosp_code"));
                    emp.setAge(rs.getString("age"));
                    emp.setGender(rs.getString("gender"));
                    emp.setBloodType(rs.getString("blood_type"));
                    emp.setHeight(rs.getString("height_cm"));
                    emp.setWeight(rs.getString("weight_kg"));
                    emp.setCodeStatus(rs.getString("code_status"));
                    emp.setRfa(rs.getString("rfa"));
                    emp.setHistory(rs.getString("history"));
                    emp.setMedication(rs.getString("medications"));
                    emp.setAllergy(rs.getString("allergies"));

                    return emp;
                }
            });
            return p;
        } catch (Exception ex) {
            Patient p = new Patient();
            p.setCtnID("none");
            return p;
        }
    }


    public ArrayList<Patient> getPatientsByHospAndDischargeDT(String hospCode) {
        String sql =
                     "select ctn_id, first_name, middle_name, last_name from ctn_adm_demo where hosp_code=:hosp "
                         + "and discharge_date ='0000-00-00 00:00:00' group by ctn_id order by admission_date desc";
        ArrayList<Patient> psl = new ArrayList<Patient>();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("hosp", hospCode);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
        for (Map<String, Object> row : rows) {
            Patient p = new Patient();
            p.setCtnID(row.get("ctn_id").toString());
            p.setFirstName(row.get("first_name").toString());
            p.setMiddleName(row.get("middle_name").toString());
            p.setLastName(row.get("last_name").toString());
            psl.add(p);
        }
        return psl;
    }

    public void updatePatientByDischargeDT(Patient p) {
        String sql = "update ctn_adm_demo set discharge_date=:disDT where ctn_id=:ctnid and admission_date=:admDT";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ctnid", p.getCtnID());
        parameters.put("admDT", p.getAdminDT());
        parameters.put("disDT", p.getDischargeDT());
        jdbcTemplate.update(sql, parameters);
    }

    public void updateHistory(VisitHistory vh) {
        String sql = "select count(*) from ctn_adm_visit_history where ctn_id = :mrn and user = :user";
        SqlParameterSource namedParameters = new MapSqlParameterSource("mrn", vh.getMrn()).addValue("user", vh.getUser());
        int flag = jdbcTemplate.queryForInt(sql, namedParameters);
        if (flag > 0) {

            String updateSQL =
                               "update ctn_adm_visit_history set admission_date = :admDT, s_time = :sessionTime "
                                   + " where ctn_id=:mrn and user=:user";
            Map<String, Object> parameters1 = new HashMap<String, Object>();
            parameters1.put("admDT", vh.getAdminDT());
            parameters1.put("sessionTime", vh.getSessionTime());
            parameters1.put("mrn", vh.getMrn());
            parameters1.put("user", vh.getUser());
            jdbcTemplate.update(updateSQL, parameters1);
        } else {

            String insertSQL =
                               "INSERT INTO ctn_adm_visit_history (user, ctn_id, admission_date, patient, s_time) "
                                   + "VALUES (:user, :mrn, :admDT, :ptName, :sessionTime)";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("user", vh.getUser());
            parameters.put("mrn", vh.getMrn());
            parameters.put("admDT", vh.getAdminDT());
            parameters.put("ptName", vh.getPtName());
            parameters.put("sessionTime", vh.getSessionTime());
            jdbcTemplate.update(insertSQL, parameters);


        }


    }

    public ArrayList<VisitHistory> getUserHistory(String user) {
        String sql = "select * from ctn_adm_visit_history where user=:user order by s_time desc limit 6";
        ArrayList<VisitHistory> hcl = new ArrayList<VisitHistory>();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user", user);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);
        for (Map<String, Object> row : rows) {
            VisitHistory cl = new VisitHistory();
            cl.setMrn(row.get("ctn_id").toString());
            cl.setPtName(row.get("patient").toString());
            hcl.add(cl);
        }
        return hcl;
    }



}
