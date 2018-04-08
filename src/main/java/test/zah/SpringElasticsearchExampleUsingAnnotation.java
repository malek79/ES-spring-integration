package test.zah;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import groovy.util.NodeBuilder;

@Configuration("mainBean")
@EnableElasticsearchRepositories(basePackages = "test.zah")
public class SpringElasticsearchExampleUsingAnnotation implements CommandLineRunner {
	@Autowired
	private DatasetRepository repository;
//	@Autowired
//	private ElasticsearchTemplate template;
	
	 @Autowired
	    private RestClient client;
	
//	@Bean
//	public ElasticsearchTemplate elasticsearchTemplate() {
//		return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
//	}
	 @Bean
     public RestClient esClient() throws Exception {
     	RestClient restClient = RestClient.builder(
     	        new HttpHost("localhost", 9200, "http")).build();
     	
     	return restClient ;
     }

	public static void main(String[] args) throws URISyntaxException, Exception {
		SpringApplication.run(SpringElasticsearchExampleUsingAnnotation.class, "--debug").close();

	
	}

	public void addEmployees() throws IOException {
		
		//template.putMapping(Employee.class);
//		IndexQuery indexQuery = new IndexQuery();
//		indexQuery.setId(joe.getId());
//		indexQuery.setObject(joe);
		//template.index(indexQuery);
		//template.refresh(Employee.class, true);
		
		//		repository.save(johnS);
//		repository.save(johnP);
//		repository.save(sam);
	}

	@Override
	public void run(String... arg0) throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		try {
			ctx.register(SpringElasticsearchExampleUsingAnnotation.class);
			ctx.refresh();
			System.out.println("Load context");
			SpringElasticsearchExampleUsingAnnotation s = (SpringElasticsearchExampleUsingAnnotation) ctx
					.getBean("mainBean");
			Map<String, String> params = Collections.emptyMap();
			HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory consumerFactory =
			        new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024);
			

			Response response = client.performRequest("GET", "/posts/_search", params, null, consumerFactory); 
			RequestLine requestLine = response.getRequestLine(); 
			HttpHost host = response.getHost(); 
			int statusCode = response.getStatusLine().getStatusCode(); 
			Header[] headers = response.getHeaders(); 
			String responseBody = EntityUtils.toString(response.getEntity());
	System.out.println(responseBody);
//			System.out.println("Add employees");
//			s.addEmployees();
			System.out.println("Find all employees");
			s.findAllDatasets();
//			System.out.println("Find employee by name 'Joe'");
//			s.findEmployee("Joe");
//			System.out.println("Find employee by name 'John'");
//			s.findEmployee("John");
//			System.out.println("Find employees by age");
//			s.findEmployeesByAge(32);
		} finally {
			ctx.close();
		}
	}

	public void findAllDatasets() {
		repository.findAll().forEach(System.out::println);
	}
//
//	public void findEmployee(String name) {
//		List<Employee> empList = repository.findEmployeesByName(name);
//		System.out.println("Employee list: " + empList);
//	}
//
//	public void findEmployeesByAge(int age) {
//		List<Employee> empList = repository.findEmployeesByAge(age);
//		System.out.println("Employee list: " + empList);
//	}
	
//	private static NodeClient getNodeClient() {
//		return (NodeClient) nodeBuilder().clusterName(UUID.randomUUID().toString()).local(true).node()
//				.client();
//	}
}
