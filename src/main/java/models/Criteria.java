package models;

public class Criteria implements Applicable {

    String id;
    String name;
    String value;
    String attribute;
    OperatorType operatorType;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean isApplicable(String actualValue) {
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
