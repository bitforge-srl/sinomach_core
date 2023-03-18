package md.sinomach.lending.menuProduct;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/type_image")
@RequiredArgsConstructor
public class TypeImageController {
    private final TypeService typeService;

    @GetMapping("")
    private Set<TypeImageResponse> getTypeImage() {

        Set<Type> allTypes = typeService.getAllTypes();

        return allTypes.stream()
                .map(type -> TypeImageResponse.builder()
                        .type(type.getName())
                        .imageSource(type.getImg())
                        .build()).collect(Collectors.toSet());
    }

}
