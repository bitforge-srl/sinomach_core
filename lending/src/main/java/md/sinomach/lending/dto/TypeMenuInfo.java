package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TypeMenuInfo {
   private String name;
   private Set<SubTypeMenuInfo> subTypes;
}
