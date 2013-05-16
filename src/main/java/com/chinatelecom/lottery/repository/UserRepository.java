package com.chinatelecom.lottery.repository;

import com.chinatelecom.lottery.model.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/13/13
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserRepository extends RepositoryBase<User> {
    private static Map<String, String> cache = new ConcurrentHashMap<String, String>();
    private static final String COLLECTION = "Users";

    public UserRepository(MongoOperations mongoOperations) {
    	 super(mongoOperations, COLLECTION, User.class);
    }

    public UserRepository() {
        this(null);
    }

    public User findUser(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mongoOperations.findOne(query, User.class, COLLECTION);
    }

    public User findUser(String userName, String password) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        query.addCriteria(Criteria.where("password").is(password));
        return mongoOperations.findOne(query, User.class, COLLECTION);
    }

    public void remove(String id) {
        User user = mongoOperations.findById(id, User.class, COLLECTION);
        mongoOperations.remove(user, COLLECTION);
    }
}