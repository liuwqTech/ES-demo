package doc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Insert_Batch {

    public static void main(String[] args) throws IOException {

        //创建ES客户端
        //创建低级客户端
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200)).build();
        //使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        //创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

        //批量添加数据
        List<BulkOperation> list = new ArrayList<>();
        list.add(new BulkOperation.Builder().create(
                d -> d.document(new User("小新","男",18)).id("1001").index("user")).build());
        list.add(new BulkOperation.Builder().create(
                d -> d.document(new User("小宇","男",24)).id("1002").index("user")).build());
        list.add(new BulkOperation.Builder().create(
                d -> d.document(new User("小房","男",25)).id("1003").index("user")).build());
        list.add(new BulkOperation.Builder().create(
                d -> d.document(new User("小胡","女",18)).id("1004").index("user")).build());
        list.add(new BulkOperation.Builder().create(
                d -> d.document(new User("小刘","男",24)).id("1005").index("user")).build());

        //向索引中添加数据
        BulkResponse bulkResponse = client.bulk(e -> e.index("user").operations(list));
        System.out.println("bulkResponse.items() = " + bulkResponse.items());

        transport.close();
        restClient.close();

    }
}
