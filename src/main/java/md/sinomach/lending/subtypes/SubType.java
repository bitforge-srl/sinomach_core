package md.sinomach.lending.subtypes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import md.sinomach.lending.product.Product;
import md.sinomach.lending.type.Type;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Type type;

    private String name;

    @OneToMany(mappedBy = "subType")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Product> products;
}
