package md.sinomach.lending.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import md.sinomach.lending.product.Feature;
import md.sinomach.lending.subtypes.SubType;

import java.util.List;

@Data
@Builder
public class AddProductRequest {
    private Long id;

    private String name;
    private String fullDescription;
    private String shortSpecification;
    private String content;
    private String additionalDescription;
    private String img;
    private Long imgId;

    private SubType subType;

    private List<Feature> features;}
