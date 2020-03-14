package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcOfficerDao {

    private JdbcTemplate jdbcTemplate;
    private final String COUNT_OFFICERS = "select count(*) from officers";
    private final String FETCH_ALL_OFFICERS = "select id, officer_rank, first_name, last_name from officers";

    public JdbcOfficerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countOfficers() {
        return jdbcTemplate.queryForObject(COUNT_OFFICERS, Integer.class);
    }

    public List<Officer> findAllOfficers() {
        return jdbcTemplate.query(FETCH_ALL_OFFICERS, new OfficerMapper());
    }
}

class OfficerMapper implements RowMapper {

    @Override
    public Officer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Officer officer = new Officer();
        officer.setId((long) rs.getInt("id"));
        officer.setRank(Rank.valueOf(rs.getString("officer_rank")));
        officer.setFirst(rs.getString("first_name"));
        officer.setLast(rs.getString("last_name"));
        return officer;
    }

}