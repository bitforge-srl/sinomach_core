package md.sinomach.lending.subtypes;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.subtypes.dto.*;
import md.sinomach.lending.type.Type;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/subtype")
@RequiredArgsConstructor
public class SubTypeController {
    private final SubTypeService subTypeService;

    @PostMapping("/add")
    public AddSubTypeResponse addSubType(@RequestBody AddSubTypeRequest addSubTypeRequest) {
        Type type = new Type();
        type.setId(addSubTypeRequest.getTypeId());
        return subTypeService.addSubType(type, addSubTypeRequest.getName());
    }

    @DeleteMapping("/delete/{id}")
    public DeleteSubTypeResponse deleteSubType(@PathVariable("id") Long id) {
        return subTypeService.deleteSubType(id);
    }

    @PostMapping("/edit")
    public EditSudTypeResponse editSubType(@RequestBody EditSubTypeRequest editSubTypeRequest) {
        return subTypeService.editSubType(editSubTypeRequest);
    }

}
