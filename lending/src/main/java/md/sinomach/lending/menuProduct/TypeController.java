package md.sinomach.lending.menuProduct;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @GetMapping("all")
    public Set<Type> allTypes() {
        Set<Type> allTypes = typeService.getAllTypes();
        //FIXME why do not loaded products
        return allTypes;
    }

    @DeleteMapping("delete/{id}")
    public TypeDeleteResponse deleteTypeById(@PathVariable("id") Long id) {
        return typeService.deleteById(id);
    }
}
