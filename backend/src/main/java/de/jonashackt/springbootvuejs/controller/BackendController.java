package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.Article;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController()
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @RequestMapping(path = "/hello")
    public @ResponseBody String sayHello() {
        LOG.info("GET called on /hello resource");
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("MongoTemplate的基本使用");
            article.setAuthor("yinjihuan");
            article.setUrl("http://cxytiandi.com/blog/detail/" + i);
            article.setTags(Arrays.asList("java", "mongodb", "spring"));
            article.setVisitCount(0L);
            article.setAddTime(new Date());
            mongoTemplate.save(article);
        }
        return HELLO_TEXT;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody long addNewUser (@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User(firstName, lastName);
        userRepository.save(user);

        LOG.info(user.toString() + " successfully saved into DB");

        return user.getId();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUserById(@PathVariable("id") long id) {
        LOG.info("Reading user with id " + id + " from database.");
        return userRepository.findById(id).get();
    }

}
