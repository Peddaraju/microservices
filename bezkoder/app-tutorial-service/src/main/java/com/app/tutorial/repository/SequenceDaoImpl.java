package com.app.tutorial.repository;

import com.app.tutorial.exception.SequenceException;
import com.app.tutorial.model.SequenceId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class SequenceDaoImpl implements SequenceDao{

    @Autowired
    protected MongoOperations mongoOperations;

    @Override
    public long getNextSequenceId(String key) throws SequenceException {
        //Create the “sequence” collection in your MongoDB first!
        //db.sequence.insert({_id: "hosting",seq: 0})


        // get sequence id
        Query query = new Query(Criteria.where("_id").is(key));

        // increase the sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        // return new increased id value
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //this is the magic happend
        SequenceId sequenceId = null;

        try {
            sequenceId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //if no id, throws SequenceException
        //optional, just a way to tell user when the sequence id is failed to generate.
        if (sequenceId == null) {
            throw new SequenceException("Unable to get sequence id for key : " + key);
        }
        return sequenceId.getSeq();
    }
}
