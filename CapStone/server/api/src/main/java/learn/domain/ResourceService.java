package learn.domain;

import learn.data.ResourceRepository;
import learn.domain.Results.ResourceResult;
import learn.models.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    private final ResourceRepository repository;

    public ResourceService(ResourceRepository repository) {
        this.repository = repository;
    }
    public List<Resources> findAll() {
        return repository.findAll();
    }
}
