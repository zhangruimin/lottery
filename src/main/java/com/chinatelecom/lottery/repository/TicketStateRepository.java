package com.chinatelecom.lottery.repository;

import com.chinatelecom.lottery.model.TicketState;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2/4/13
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TicketStateRepository extends RepositoryBase<TicketState> {
    private static final String COLLECTION = "TicketState";

    public TicketStateRepository(MongoOperations mongoOperations) {
        super(mongoOperations, COLLECTION, TicketState.class);
    }

    public TicketStateRepository() {
        this(null);
    }

    public TicketState findOne() {
        return mongoOperations.findOne(new Query(), TicketState.class, COLLECTION);
    }
}
