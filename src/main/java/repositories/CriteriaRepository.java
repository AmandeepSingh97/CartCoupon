package repositories;

import models.Attribute;
import models.Criteria;
import models.OperatorType;

import java.util.UUID;

public class CriteriaRepository {

    public static Criteria createCriteria(String name, String value, String attribute, OperatorType operatorType) {
        final Criteria criteria = new Criteria(UUID.randomUUID().toString(), name, value, attribute, operatorType);
        return criteria;
    }
}
