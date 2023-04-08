package md.sinomach.lending.productPage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import md.sinomach.lending.menuProduct.SubType;

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

    @ManyToOne
    @JsonIgnore
    private SubType subType;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Feature> features;

}
