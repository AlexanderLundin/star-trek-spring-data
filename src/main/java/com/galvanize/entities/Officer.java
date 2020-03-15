package com.galvanize.entities;

import javax.persistence.*;

@Entity
@Table(name = "officers", schema = "star_trek")
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rank officer_rank;
    @Column(nullable = false, name = "first_name")
    private String first;
    @Column(nullable = false, name = "last_name")
    private String last;

    public Officer(){}

    public Officer(Long id, Rank officer_rank, String first, String last) {
        this.id = id;
        this.officer_rank = officer_rank;
        this.first = first;
        this.last = last;
    }

    public Officer(Rank officer_rank, String first, String last) {
        this.officer_rank = officer_rank;
        this.first = first;
        this.last = last;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rank getRank() {
        return officer_rank;
    }

    public void setRank(Rank rank) {
        this.officer_rank = rank;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public String toString(){
        String string = this.id + this.first + this.last + this.officer_rank;
        return string;
    }
}
