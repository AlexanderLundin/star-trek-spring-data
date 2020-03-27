package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.repository.JdbcOfficerDao;
import com.galvanize.repository.JpaOfficerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@RestController
public class OfficerController {


    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcOfficerDao jdbcOfficerDao;
    JpaOfficerDao jpaOfficerDao;


    public OfficerController(JdbcTemplate jdbcTemplate, JpaOfficerDao jpaOfficerDao){
        jdbcOfficerDao = new JdbcOfficerDao(jdbcTemplate);
        this.jpaOfficerDao = jpaOfficerDao;
    }


    //CREATE


    @PostMapping("/officers")
    public Officer JDBCPostOfficer(@RequestBody Officer officer){
        return jdbcOfficerDao.save(officer);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/officers", params = {"first", "last", "rank"})
    public RedirectView officerAdd(@RequestParam String first,
                                @RequestParam String last, @RequestParam String rank, Model model) {
        Officer newOfficer = new Officer();
        newOfficer.setFirst(first);
        newOfficer.setLast(last);
        Rank doesRankExist = Rank.getIfPresent(rank);
        if (doesRankExist != null){
            newOfficer.setRank(Rank.valueOf(rank));
        }else{
            newOfficer.setRank(Rank.ENSIGN);
        }
        jdbcOfficerDao.save(newOfficer);
        model.addAttribute("officer", newOfficer);
        return new RedirectView("/officers/" + newOfficer.getId());
    }


    //READ


    @GetMapping("/officers")
    public List<Officer> JDBCGetAllOfficers() {
        List<Officer> officerList = jdbcOfficerDao.findAllOfficers();
        return officerList;
    }

    @GetMapping("/officers/{id}")
    public Optional<Officer> JDBCGetOfficerByID(@PathVariable Long id){
        Optional<Officer> officer = jdbcOfficerDao.findOfficerById(id);
        return officer;
    }


    //UPDATE


    @PatchMapping("/officers/{id}/{rank}")
    public Officer JPAPatchOfficerRank(@PathVariable Long id, @PathVariable Rank rank){
        return jpaOfficerDao.updateRankByID(id, rank);
    }


    // DELETE


    @DeleteMapping("/officers/{id}")
    public void JBDCDeleteOfficerByID( @PathVariable Long id){
        jdbcOfficerDao.delete(id);
    }

}
