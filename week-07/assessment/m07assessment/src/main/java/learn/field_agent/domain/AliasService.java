package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AliasService {
    private final AliasRepository repository;

    public AliasService(AliasRepository repository) {
        this.repository = repository;
    }

    public List<Alias> findAll() {
        return repository.findAll();
    }

    public Alias findById(int aliasId) {
        return repository.findById(aliasId);

    }

    public Result<Alias> add(Alias alias) {
        Result<Alias> result = validate(alias);
        if (!result.isSuccess()) {
            return result;
        }
        if (alias.getAliasId() != 0) {
            result.addMessage("AliasId cannot be set for this operation", ResultType.INVALID);
            return result;
        }
        alias = repository.add(alias);
        result.setPayload(alias);
        return result;
    }

    public Result<Alias> update(Alias alias) {
        Result<Alias> result = validate(alias);

        if (alias.getAliasId() <= 0) {
            result.addMessage("Alias Id is required for update.", ResultType.INVALID);
            return result;
        }
        if (alias.getAgent() == null) {
            result.addMessage("Agent was not found", ResultType.NOT_FOUND);
            return result;
        }
        if (result.isSuccess()) {
            boolean update = repository.update(alias);
            if (update) {
                result.setPayload(alias);
            } else {
                result.addMessage("Could not find Alias to update", ResultType.NOT_FOUND);
            }
        }
        return result;
    }

    public Result<Void> deleteById(int aliasId) {
        Result<Void> result = new Result<>();
        if (aliasId == 0) {
            result.addMessage("Alias id is required for Deletion", ResultType.INVALID);
        }
        if (result.isSuccess()) {
            boolean delete = repository.deleteById(aliasId);
            if (!delete) {
                result.addMessage("Could not find Alias to delete", ResultType.NOT_FOUND);
            }
        }
        return result;
    }


    private Result<Alias> validate(Alias alias) {
        Result<Alias> result = new Result<>();
        if (Validations.isNullOrBlank(alias.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }
        for (Alias a : findAll()) {
            if (alias.getName().matches(a.getName()))
                if (Validations.isNullOrBlank(alias.getPersona()) || Validations.isNullOrBlank(a.getPersona())) {
                    result.addMessage("Duplicate name must have persona.", ResultType.INVALID);
                    break;
                }
        }
        if (result.getMessages().isEmpty()) {
            result.isSuccess();
        } else {
            System.out.println(result.getMessages());
        }
        return result;
    }


}
