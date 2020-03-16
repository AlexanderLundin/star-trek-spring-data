package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcOfficerDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertOfficer;
    private final Officer officer = new Officer();
    // DB query strings
    private final String COUNT_OFFICERS = "select count(*) from officers";
    private final String FETCH_ALL_OFFICERS = "select id, officer_rank, first_name, last_name from officers";
    private final String FETCH_OFFICER_BY_ID = "select * from officers where id = ?";
    private final String DELETE_OFFICER_BY_ID = "delete from officers where id = ?";


    public JdbcOfficerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }


    //CREATE
    public Officer save(Officer officer) {
        Map<String, Object> fields = new HashMap<>();
        fields.put("officer_rank", officer.getRank());
        fields.put("first_name", officer.getFirst());
        fields.put("last_name", officer.getLast());

        long id = insertOfficer.executeAndReturnKey(fields).longValue();
        officer.setId(id);

        return officer;
    }

    //READ

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


    //UPDATE

    //DELETE


    public void delete(long id) {
        jdbcTemplate.update(DELETE_OFFICER_BY_ID, id);
    }

}

//class to map db query returns to officer objects
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