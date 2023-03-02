package md.sinomach.lending.dao;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "type")

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SubType> subTypes;

}
