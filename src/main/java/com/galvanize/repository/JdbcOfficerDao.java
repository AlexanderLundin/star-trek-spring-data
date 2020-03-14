package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcOfficerDao {

    private JdbcTemplate jdbcTemplate;
    private final String COUNT_OFFICERS = "select count(*) from officers";
    private final String FETCH_ALL_OFFICERS = "select id, officer_rank, first_name, last_name from officers";
    private final String FETCH_OFFICER_BY_ID = "select * from officers where id = ?";
    private final Officer officer = new Officer();

    public JdbcOfficerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countOfficers() {
        return jdbcTemplate.queryForObject(COUNT_OFFICERS, Integer.class);
    }

    public List<Officer> findAllOfficers() {
        return jdbcTemplate.query(FETCH_ALL_OFFICERS, new OfficerMapper());
    }

    public boolean officerExistsById(long id) {
        try{
            Optional<Object> officer = Optional.ofNullable(jdbcTemplate.queryForObject(FETCH_OFFICER_BY_ID, new Object[] { id }, new OfficerMapper()));
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }

    }

    public Optional<Object> findOfficerById(long id) {
        officer.setId(id);
        Object [] array = new Object[]{id};
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(FETCH_OFFICER_BY_ID, array, new OfficerMapper()));

        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }

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