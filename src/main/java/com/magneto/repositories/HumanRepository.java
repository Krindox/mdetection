package com.magneto.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Singleton
public class HumanRepository {

    private AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    private DynamoDB dynamoDB = new DynamoDB(client);
    private static final Logger log = LoggerFactory.getLogger(HumanRepository.class);

    public boolean addDna(String humanDna, boolean isMutant){
        Table table = dynamoDB.getTable("human");

        try{
            Condition idCondition = new Condition().withComparisonOperator(ComparisonOperator.EQ)
                    .withAttributeValueList(new AttributeValue().withS(humanDna));
            Map<String, Condition> keyConditions = new HashMap<>();
            keyConditions.put("dna", idCondition);
            QueryRequest request = new QueryRequest("human");
            request.setSelect(Select.COUNT);
            request.setKeyConditions(keyConditions);
            QueryResult result = client.query(request);
            Integer count = result.getCount();

            if (count == 0){
                PutItemOutcome outcome = table.putItem(new Item()
                        .withPrimaryKey("dna", humanDna)
                        .with("is_mutant", isMutant));
                if(Objects.nonNull(outcome)){
                    log.info("Dna inserted successfully");
                    return true;
                }else {
                    return false;
                }
            }else{
                return true;
            }

        }catch (Exception e){
            log.error("Exception occurred during add dna Process : ", e);
            return false;
        }
    }

    public int getCountTotal(){
        try{
            ScanRequest scanRequest = new ScanRequest().withTableName("human");
            ScanResult result = client.scan(scanRequest);
            Integer count = result.getCount();
            return count;
        }catch (Exception e){
            log.error("Exception occurred searching count total dna : ", e);
            return 0;
        }
    }
    public int getCountMutant(){
        try{
            Map<String, AttributeValue> attributeValueMap = new HashMap<String, AttributeValue>();
            attributeValueMap.put(":val", new AttributeValue().withBOOL(true));

            ScanRequest scanRequest = new ScanRequest().withTableName("human")
                    .withFilterExpression("is_mutant = :val")
                    .withExpressionAttributeValues(attributeValueMap);

            ScanResult result = client.scan(scanRequest);
            Integer count = result.getCount();
            return count;
        }catch (Exception e){
            log.error("Exception occurred searching count mutant dna : ", e);
            return 0;
        }
    }


}
