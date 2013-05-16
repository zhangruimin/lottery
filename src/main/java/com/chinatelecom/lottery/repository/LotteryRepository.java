package com.chinatelecom.lottery.repository;

import com.chinatelecom.lottery.model.LotteryRecord;
import com.chinatelecom.lottery.model.PrizeType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/13/13
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class LotteryRepository extends RepositoryBase<LotteryRecord> {
    private static final String COLLECTION = "LotteryRecord";

    public LotteryRepository(MongoOperations mongoOperations) {
        super(mongoOperations, COLLECTION, LotteryRecord.class);
    }

    public LotteryRepository() {
        this(null);
    }

    public LotteryRecord findByPhone(String phone) {
        Query query = new Query(Criteria.where("phoneNumber").is(phone));
        return mongoOperations.findOne(query, LotteryRecord.class, COLLECTION);
    }

    public List<LotteryRecord> findByPrizeType(PrizeType type) {
        Query query = new Query();
        if (type != null) {
            Criteria criteria = Criteria.where("prizeType").is(type);
            query.addCriteria(criteria);
        }

        query.with(new Sort(Sort.Direction.DESC, "timestamp"));
        return mongoOperations.find(query, LotteryRecord.class, COLLECTION);
    }
}
