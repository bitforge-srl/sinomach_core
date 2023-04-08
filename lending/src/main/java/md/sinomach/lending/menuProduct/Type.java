package md.sinomach.lending.menuProduct;

import jakarta.persistence.*;
import lombok.*;
import md.sinomach.lending.menuProduct.SubType;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String name;
    private String img;
    private String imgBanner;
    private String shortDescription;

    @OneToMany(mappedBy = "type")

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SubType> subTypes;

}
