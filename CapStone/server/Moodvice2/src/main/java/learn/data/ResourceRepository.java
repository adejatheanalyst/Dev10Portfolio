package learn.data;

import learn.models.Resources;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ResourceRepository {
    List<Resources> findAll();
}
