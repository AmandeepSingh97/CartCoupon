package models;

import java.util.logging.Logger;

public class Criteria implements Applicable {

    final String id;
    final String name;
    final String value;
    final String attribute;
    final OperatorType operatorType;
    private static final Logger LOGGER = Logger.getLogger(Criteria.class.toString());

    public Criteria(String id, String name, String value, String attribute, OperatorType operatorType) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.attribute = attribute;
        this.operatorType = operatorType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }
    public String getAttribute() {
        return attribute;
    }

    @Override
    public boolean isApplicable(String actualValue) {
        try {
            switch (operatorType) {
                case GREATER_THAN_OR_EQUAl:
                    return greaterThanEqualCheck(actualValue, value);
                case LESS_THAN_OR_EQUAL:
                    return lessThanEqualCheck(actualValue, value);
                case GREATER_THAN:
                    return greaterThanCheck(actualValue, value);
                case LESS_THAN:
                    return lessThanCheck(actualValue, value);
                case EQUAL:
                    return equalsCheck(actualValue, value);
            }
        } catch (NullPointerException e) {
            LOGGER.warning("Attribute not present on item");
            return false;
        }
        return false;
    }

    private boolean equalsCheck(String actualValue, String value) {
        return actualValue.equals(value);
    }

    private boolean lessThanEqualCheck(String actualValue, String value) {
        double actual = Double.parseDouble(actualValue);
        double expected = Double.parseDouble(value);
        return actual <= expected;
    }

    private boolean greaterThanEqualCheck(String actualValue, String value) {
        double actual = Double.parseDouble(actualValue);
        double expected = Double.parseDouble(value);
        return actual >= expected;
    }

    private boolean lessThanCheck(String actualValue, String value) {
        double actual = Double.parseDouble(actualValue);
        double expected = Double.parseDouble(value);
        return actual < expected;
    }

    private boolean greaterThanCheck(String actualValue, String value) {
        double actual = Double.parseDouble(actualValue);
        double expected = Double.parseDouble(value);
        return actual > expected;
    }
}
