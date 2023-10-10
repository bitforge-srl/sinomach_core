package md.sinomach.lending.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import md.sinomach.lending.subtypes.SubType;

import java.util.List;

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
    private String shortSpecification;
    private String content;
    private String additionalDescription;
    private String img;
    private Long imgId;

    @ManyToOne
    @JsonIgnore
    private SubType subType;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Feature> features;

}
