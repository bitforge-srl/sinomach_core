package md.sinomach.lending.subtypes;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.product.Product;
import md.sinomach.lending.subtypes.dto.AddSubTypeResponse;
import md.sinomach.lending.subtypes.dto.DeleteSubTypeResponse;
import md.sinomach.lending.subtypes.dto.EditSubTypeRequest;
import md.sinomach.lending.subtypes.dto.EditSudTypeResponse;
import md.sinomach.lending.type.Type;
import md.sinomach.lending.type.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubTypeService {
    private final SubTypeRepository subTypeRepository;
    private final TypeRepository typeRepository;

    public SubType save(SubType subTypeDao) {
        return subTypeRepository.save(subTypeDao);
    }

    public AddSubTypeResponse addSubType(Type type, String name) {

        Boolean exist = subTypeRepository.existsByNameAndTypeId(name, type.getId());

        if (exist) {
            return AddSubTypeResponse.failed(AddSubTypeResponse.Error.alreadyExist);
        }
        List<Product> products = new ArrayList<>();

        SubType subType = new SubType(null, type, name, products);
        subTypeRepository.save(subType);

        return AddSubTypeResponse.success();
    }

    public DeleteSubTypeResponse deleteSubType(Long id) {
        Boolean exist = subTypeRepository.existsById(id);
        if (!exist) {
            return DeleteSubTypeResponse.failed(DeleteSubTypeResponse.Error.non_existent_subtype);
        }
        try {
            subTypeRepository.deleteById(id);
            return DeleteSubTypeResponse.success();
        } catch (Exception e) {
            return DeleteSubTypeResponse.failed(DeleteSubTypeResponse.Error.failed);
        }
    }

    public EditSudTypeResponse editSubType(EditSubTypeRequest editSubTypeRequest) {
        try {
            SubType subTypeById = subTypeRepository.getReferenceById(editSubTypeRequest.subtypeId);
            subTypeById.setName(editSubTypeRequest.editedNameSubType);
            Type type = typeRepository.getReferenceById(editSubTypeRequest.typeId);
            subTypeById.setType(type);
            subTypeRepository.save(subTypeById);
        } catch (Exception e) {
            return EditSudTypeResponse.failed(EditSudTypeResponse.Error.failed);
        }
        return EditSudTypeResponse.success();
    }
}
