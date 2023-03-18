package md.sinomach.lending.menuProduct;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @GetMapping("all")
    public Set<Type> allTypes(){
        Set<Type> allTypes = typeService.getAllTypes();
        //FIXME why do not loaded products
        return allTypes;
    }
}
