package com.galvanize.repository;

import com.galvanize.entities.Officer;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaOfficerDao implements OfficerDao {
    @PersistenceContext
    private EntityManager entityManager;

    private final String JAP_FIND_ALL_FROM_OFFICER_TABLE = "select o from Officer o";
    private final String JAP_COUNT_ALL_FROM_OFFICER_TABLE = "select count(*) from Officer";

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery(JAP_COUNT_ALL_FROM_OFFICER_TABLE, Long.class);
        long result = query.getSingleResult();
        return result;
    }

    @Override
    public List<Officer> findAll() {
        TypedQuery<Officer> query = entityManager.createQuery(JAP_FIND_ALL_FROM_OFFICER_TABLE, Officer.class);
        List<Officer> results = query.getResultList();
        return results;
    }

    @Override
    public List<Officer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Officer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Officer> findAllById(Iterable<Integer> integers) {
        return null;
    }



    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Officer entity) {
    }

    @Override
    public void deleteAll(Iterable<? extends Officer> entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public <S extends Officer> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Officer> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Officer> findById(Integer integer) {
        return null;
    }

    public Optional<Officer> findById2(long id) {
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    public boolean existsById(long id) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Officer> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Officer> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Officer getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Officer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Officer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Officer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Officer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Officer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Officer> boolean exists(Example<S> example) {
        return false;
    }
}
