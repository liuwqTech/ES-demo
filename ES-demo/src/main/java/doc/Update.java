package doc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Update {

    public static void main(String[] args) throws IOException {

        //创建ES客户端
        //创建低级客户端
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200)).build();
        //使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        //创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

        //使用map集合封装需要修改的内容
        Map<String, Object> map = new HashMap<>();
        map.put("name","小新");

        //更新数据
        UpdateResponse<User> updateResponse = client.update(e -> e.index("user").id("1001").doc(map),User.class);
        System.out.println("updateResponse.result() = " + updateResponse.result());

        transport.close();
        restClient.close();

    }
}
