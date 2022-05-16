package doc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class Insert {

    public static void main(String[] args) throws IOException {

        //创建ES客户端
        //创建低级客户端
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200)).build();
        //使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        //创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

        //向User对象中添加数据
        User user = new User();
        user.setName("小新不吃蔬菜");
        user.setSex("男");
        user.setAge(18);

        //向索引中添加数据
        CreateResponse createResponse = client.create(e -> e.index("user").id("1001").document(user));
        System.out.println("createResponse.result() = " + createResponse.result());

        transport.close();
        restClient.close();

    }
}
