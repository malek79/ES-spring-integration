//package test.customer;
//
//import java.net.InetAddress;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "test.customer")
//public class EsConfig {
//
//    @Value("${elasticsearch.host}")
//    private String EsHost;
//
//    @Value("${elasticsearch.port}")
//    private int EsPort;
//
//    @Value("${elasticsearch.clustername}")
//    private String EsClusterName;
//
//    @Bean
//    public RestClient client() throws Exception {
////        Settings esSettings = Settings.builder()
////        		.put("cluster.name", EsClusterName)
////                .put("shield.transport.ssl", true)
////                .build();
////        return TransportClient
////                .settings(esSettings)
////                .build()
////                .addTransportAddress(
////                new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
//    return RestClient.builder(
//            new HttpHost("localhost", 9200, "http")).build();
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(client());
//    }
//
////    @Bean
////    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
////        return new ElasticsearchTemplate(new NodeBuilder().local(true).node().client());
////    }
//
//}
