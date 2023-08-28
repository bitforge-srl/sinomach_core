package md.sinomach.lending.menuProduct;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TypeMenuInfo {
   private Long id;
   private String name;
   private Set<SubTypeMenuInfo> subTypes;
   private Long order;
}
