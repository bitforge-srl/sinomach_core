package md.sinomach.lending.type;


import lombok.RequiredArgsConstructor;
import md.sinomach.lending.type.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;

    @GetMapping("all")
    public List<Type> allTypes() {
        List<Type> allTypes = typeService.getAllTypes();
        //FIXME why do not loaded products
        return allTypes;
    }

    @DeleteMapping("delete/{id}")
    public RemoveTypeResponse deleteTypeById(@PathVariable("id") Long id) {
        return typeService.deleteById(id);
    }

    @PostMapping("/add")
    public AddTypeResponse addNewType(@RequestBody Type type) {
        return typeService.addType(type);
    }

    @PostMapping("/editType/{id}")
    public EditTypeNameResponse editType(@PathVariable("id") Long id, @RequestBody Type editedType) {
        return typeService.editType(id, editedType);
    }

    @PostMapping("/updateOrderTypes")
    public UpdateTypeOrderResponse editOrderTypes(@RequestBody List<UpdateTypeOrder> updateTypeOrder){
        return typeService.updateOrderTypes(updateTypeOrder);
    }
}
