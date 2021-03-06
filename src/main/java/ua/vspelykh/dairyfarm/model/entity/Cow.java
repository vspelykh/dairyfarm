package ua.vspelykh.dairyfarm.model.entity;

import org.hibernate.validator.constraints.Range;
import ua.vspelykh.dairyfarm.model.AbstractBaseEntity;
import ua.vspelykh.dairyfarm.model.record.Calving;
import ua.vspelykh.dairyfarm.model.record.Insemination;
import ua.vspelykh.dairyfarm.model.record.Vaccination;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cows")
@NamedEntityGraph(name = "cow.full",
        attributeNodes = {@NamedAttributeNode("calvings"),
        @NamedAttributeNode("inseminations"), @NamedAttributeNode("vaccinations")
        })
public class Cow extends AbstractBaseEntity {

    @Column(name = "number", nullable = false, unique = true)
    @Size(max = 8)
    private String number;

    @Column(name = "birth", nullable = false)
    @NotNull
    private LocalDateTime birth;

    @Column(name = "is_alive", nullable = false, columnDefinition = "bool default true")
    private boolean isAlive;

    @Column(name = "death")
    private LocalDateTime death;

    @Column(name = "section", nullable = false)
    @Range(max = 1000)
    private Integer section;

    @Column(name = "lactation")
    private Integer lactation;

    @Column(name = "is_milking", nullable = false)
    @NotNull
    private boolean isMilking;

    @Column(name = "is_inseminated", nullable = false)
    @NotNull
    private boolean isInseminated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Farm farm;

    @OneToMany(mappedBy = "cow")
    private Set<Vaccination> vaccinations;

    @OneToMany(mappedBy = "cow")
    private Set<Insemination> inseminations;

    @OneToMany(mappedBy = "cow")
    private Set<Calving> calvings;

    public Cow() {
    }
    public Cow(Integer id, String number, LocalDateTime birth, Integer section) {
        super(id);
        this.number = number;
        this.birth = birth;
        this.section = section;
        isMilking = false;
        isAlive = true;
        isInseminated = false;
        lactation = 0;
    }

    public Cow(String number, LocalDateTime birth, Integer section) {
        this.number = number;
        this.birth = birth;
        this.section = section;
        isMilking = false;
        isAlive = true;
        isInseminated = false;
        lactation = 0;
    }

    public Cow(String number, LocalDateTime birth, Integer section, Integer lactation, boolean isMilking, boolean isInseminated) {
        isAlive = true;
        this.number = number;
        this.birth = birth;
        this.section = section;
        this.lactation = lactation;
        this.isMilking = isMilking;
        this.isInseminated = isInseminated;
    }

    public Cow(Integer id, String number, LocalDateTime birth, Integer section, Integer lactation, boolean isMilking, boolean isInseminated) {
        super(id);
        isAlive = true;
        this.number = number;
        this.birth = birth;
        this.section = section;
        this.lactation = lactation;
        this.isMilking = isMilking;
        this.isInseminated = isInseminated;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public LocalDateTime getDeath() {
        return death;
    }

    public void setDeath(LocalDateTime death) {
        this.death = death;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getLactation() {
        return lactation;
    }

    public void setLactation(Integer lactation) {
        this.lactation = lactation;
    }

    public boolean isMilking() {
        return isMilking;
    }

    public void setMilking(boolean milking) {
        isMilking = milking;
    }

    public boolean isInseminated() {
        return isInseminated;
    }

    public void setInseminated(boolean inseminated) {
        isInseminated = inseminated;
    }

    @Override
    public String toString() {
        return "Cow{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", birth=" + birth +
                ", section=" + section +
                ", isMilking=" + isMilking +
                '}';
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public Set<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }


    public Set<Insemination> getInseminations() {
        return inseminations;
    }

    public void setInseminations(Set<Insemination> inseminations) {
        this.inseminations = inseminations;
    }


    public Set<Calving> getCalvings() {
        return calvings;
    }

    public void setCalvings(Set<Calving> calvings) {
        this.calvings = calvings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cow)) return false;
        if (!super.equals(o)) return false;
        Cow cow = (Cow) o;
        return Objects.equals(number, cow.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number);
    }
}
