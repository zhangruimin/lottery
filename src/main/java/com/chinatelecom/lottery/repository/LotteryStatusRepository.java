package com.chinatelecom.lottery.repository;

import com.chinatelecom.lottery.model.LotteryStatus;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/13/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class LotteryStatusRepository extends RepositoryBase<LotteryStatus> {
    private static final String COLLECTION = "LotteryStatus";

    public LotteryStatusRepository(MongoOperations mongoOperations) {
        super(mongoOperations, COLLECTION, LotteryStatus.class);
    }

    public LotteryStatusRepository() {
        this(null);
    }

    public LotteryStatus findOne() {
        return mongoOperations.findOne(new Query(), LotteryStatus.class, COLLECTION);
    }
}
