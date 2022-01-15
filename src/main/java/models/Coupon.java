package models;

import java.util.ArrayList;
import java.util.List;

public class Coupon {

    final String id;
    final String name;
    final List<Criteria> criterias;

    public Coupon(String id, String name) {
        this.id = id;
        this.name = name;
        criterias = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Criteria> getCriteriaList() {
        return criterias;
    }

    public void addCriterias(List<Criteria> criteriaList) {
        this.criterias.addAll(criteriaList);
    }

    public Coupon addCriteria(Criteria criteria) {
        this.criterias.add(criteria);
        return this;
    }

    public boolean apply(Item item) {
        for (Criteria criteria: criterias) {
            if(!criteria.isApplicable(item.getAttributeValue(criteria.getAttribute()))){
                return false;
            }
        }
        return true;
    }

}
