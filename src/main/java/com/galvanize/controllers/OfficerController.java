package com.galvanize.controllers;

import com.galvanize.entities.Officer;
import com.galvanize.entities.Rank;
import com.galvanize.services.OfficerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


@RestController
public class OfficerController {

    OfficerService officerService;


    public OfficerController(OfficerService officerService){
        this.officerService = officerService;
    }


    //CREATE


    @PostMapping("/officers")
    public Officer postOfficer(@RequestBody Officer officer){
        return officerService.save(officer);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/officers", params = {"first", "last", "rank"})
    public RedirectView postOfficerWithPathVariables(@RequestParam String first,
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
        officerService.save(newOfficer);
        model.addAttribute("officer", newOfficer);
        return new RedirectView("/officers/" + newOfficer.getId());
    }


    //READ


    @GetMapping("/officers")
    public List<Officer> getAllOfficers() {
        List<Officer> officerList = officerService.findAll();
        return officerList;
    }

    @GetMapping("/officers/{id}")
    public Officer getOfficerById(@PathVariable Long id){
        Officer officer = officerService.findById(id);
        return officer;
    }


    //UPDATE


    @PatchMapping("/officers/{id}")
    public Officer updateOfficerById(@PathVariable Long id, @RequestBody Officer officer){
        return officerService.updateById(id, officer);
    }


    // DELETE


    @DeleteMapping("/officers/{id}")
    public void JBDCDeleteOfficerByID( @PathVariable Long id){
        officerService.deleteById(id);
    }

}
