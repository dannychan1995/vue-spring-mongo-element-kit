package de.jonashackt.springbootvuejs.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
/**
 * 文章信息
 * @author yinjihuan
 *
 */
@Document(collection = "article_info")
@Data
public class Article {
    @Id
    private String id;
    @Field("title")
    private String title;
    @Field("url")
    private String url;
    @Field("author")
    private String author;
    @Field("tags")
    private List<String> tags;
    @Field("visit_count")
    private Long visitCount;
    @Field("add_time")
    private Date addTime;

    //省略get set方法
}
