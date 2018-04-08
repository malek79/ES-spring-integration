package test.customer;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RestApp implements CommandLineRunner {
	
	  @Configuration
	    public class AppConfig {
	        @Bean
	        public RestClient esClient() throws Exception {
	        	RestClient restClient = RestClient.builder(
	        	        new HttpHost("localhost", 9200, "http")).build();
	        	
	        	return restClient ;
	        }
	    }
	  
	   @Autowired
	    private RestClient client;

	public static void main(String[] args) throws IOException{
//		 AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//		 
//		 context.scan("fr.pilato.tests");
//	        context.refresh();
//	        RestApp p = context.getBean(RestApp.class);
//	        p.run();
		SpringApplication.run(RestApp.class, "--debug").close();
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		Map<String, String> params = Collections.emptyMap();
		HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory consumerFactory =
		        new HttpAsyncResponseConsumerFactory.HeapBufferedResponseConsumerFactory(30 * 1024 * 1024);
		

        client.performRequest("GET", "/posts/_search", params, null, consumerFactory); 
    		
	}

}
