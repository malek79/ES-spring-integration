package test.zah;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface DatasetRepository extends ElasticsearchRepository<Dataset,String> {
    List<Dataset> findDatasetByAuthor(String author);  
    List<Dataset> findEmployeesByName(String name);
}
