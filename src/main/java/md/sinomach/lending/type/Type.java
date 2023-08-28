package md.sinomach.lending.type;

import jakarta.persistence.*;
import lombok.*;
import md.sinomach.lending.subtypes.SubType;

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
    @Column(name = "\"order\"" )
    private Long order;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SubType> subTypes;

}
