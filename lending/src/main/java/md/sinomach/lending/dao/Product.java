package md.sinomach.lending.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String fullDescription;
    private String shortDescription;
    private String shortSpecification;
    private String fullSpecification;
    private String videoUrl;

    @ManyToOne
    @JsonIgnore
    private SubType subType;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Feature> features;

   }
