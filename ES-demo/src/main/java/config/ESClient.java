package config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ESClient {

    public static void main(String[] args) throws IOException {

        //创建ES客户端
        //创建低级客户端
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200)).build();
        //使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        //创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

        //关闭ES客户端
        transport.close();
        restClient.close();
    }
}
