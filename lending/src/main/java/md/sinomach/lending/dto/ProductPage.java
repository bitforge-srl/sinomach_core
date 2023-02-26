package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.dao.Feature;
import md.sinomach.lending.dao.SubType;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ProductPage {

    private Long id;

    private String name;
    private String fullDescription;
    private String shortDescription;
    private String shortSpecification;
    private String fullSpecification;
    private String videoUrl;

    private List<Feature> features;

    private SubType subType;

    private Set<ProductInfo> productsBySubType;


}
