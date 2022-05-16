package doc;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import entity.User;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class Query {

    public static void main(String[] args) throws IOException {

        //创建ES客户端
        //创建低级客户端
        RestClient restClient = RestClient.builder(new HttpHost("localhost",9200)).build();
        //使用Jackson映射器创建传输层
        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
        //创建API客户端
        ElasticsearchClient client = new ElasticsearchClient(transport);

//        // 1、全量查询
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user").query(q -> q.matchAll(m -> m)),User.class);
//        HitsMetadata<User> hits = searchResponse.hits();
//        //循环打印数据输出
//        for (Hit<User> hit : hits.hits()){
//            System.out.println("user = " + hit.source().toString());
//        }
//        //打印总数据条数
//        System.out.println("searchResponse.hits().total().value() = " + searchResponse.hits().total().value());


//        // 2、term查询，查询条件为关键字
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user").query(q -> q
//                .term(t -> t.field("age").value(FieldValue.of(24)))),User.class);
//        HitsMetadata<User> hits = searchResponse.hits();
//        //循环打印数据输出
//        for (Hit<User> hit : hits.hits()){
//            System.out.println("user = " + hit.source().toString());
//        }
//        //打印总数据条数
//        System.out.println("searchResponse.hits().total().value() = " + searchResponse.hits().total().value());


//        // 3、分页查询
//        // Tips:（当前页码-1）* 每页显示的条数
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user").query(q -> q.matchAll(m -> m)).from(0).size(2),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 4、查询结果排序
//        SearchResponse<User> searchResponse = client.search(
//                e -> e.index("user")
//                        .query(q -> q.matchAll(m -> m))
//                        .sort(o -> o.field(f -> f.field("age").order(SortOrder.Asc))),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 5、过滤字段
//        SearchResponse<User> searchResponse = client.search(
//                e -> e.index("user")
//                        .query(q -> q.matchAll(m -> m))
//                        .sort(o -> o.field(f -> f.field("age").order(SortOrder.Asc)))
//                        .source(r -> r.filter(f -> f.includes("name","sex").excludes(""))),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 6、组合查询
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user")
//                .query(q -> q.bool(b -> b
//                        .must(m -> m.match(u -> u.field("age").query(FieldValue.of(18))))
//                        .mustNot(m -> m.match(u -> u.field("sex").query(FieldValue.of("女"))))
////                        .should(m -> m.match(u -> u.field("sex").query(FieldValue.of("男"))))
////                        .should(m -> m.match(u -> u.field("age").query(FieldValue.of("24"))))
//                )),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 7、范围查询
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user").query(q -> q
//                .range(r -> r.field("age").gte(JsonData.of(20)).lte(JsonData.of(25)))
//                ),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 8、模糊查询
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user").query(q -> q
//                .fuzzy(f -> f.field("name").value(FieldValue.of("小新")).fuzziness("1"))
//                ),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 9、高亮查询
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user").query(q -> q
//                .term(t -> t.field("name").value(FieldValue.of("小新"))))
//                .highlight(h -> h.fields("name", f -> f.preTags("<font color='red'>").postTags("</font>"))),User.class);
//        //打印输出
//        searchResponse.hits().hits().forEach(h -> System.out.println(h.source().toString()));


//        // 10、聚合查询-最大值
//        SearchResponse<User> searchResponse = client.search(e -> e.index("user")
//                        .aggregations("maxAge",a -> a.max(m -> m.field("age"))),User.class);
//        //打印输出
//        searchResponse.aggregations().entrySet().forEach(f -> System.out.println(f.getKey() + ":" + f.getValue().max().value()));


        // 11、聚合查询-分组查询
        SearchResponse<User> searchResponse = client.search(e -> e.index("user")
                .aggregations("ageGroup",a -> a.terms(t -> t.field("age"))),User.class);
        //打印输出
        searchResponse.aggregations().get("ageGroup").lterms().buckets().array().forEach(f -> System.out.println(f.key() + ":" + f.docCount()));


        //关闭客户端
        transport.close();
        restClient.close();

    }
}
