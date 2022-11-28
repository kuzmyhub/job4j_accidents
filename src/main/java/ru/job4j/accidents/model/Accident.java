package ru.job4j.accidents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NamedEntityGraph(name = "Accident.detail",
        attributeNodes = {
        @NamedAttributeNode("accidentType"), @NamedAttributeNode("rules")
})
@Table(name = "accidents")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_types_id")
    private AccidentType accidentType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "accidents_rules",
            joinColumns = { @JoinColumn(name = "accidents_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "rules_id", nullable = false, updatable = false) })
    private Set<Rule> rules = new HashSet<>();
}
