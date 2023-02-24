package md.sinomach.lending.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

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
