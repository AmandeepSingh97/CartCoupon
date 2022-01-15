package models;

import java.util.ArrayList;
import java.util.List;

public class Coupon {

    String id;
    String name;
    List<Criteria> criterias;

    public Coupon(String id) {
        this.id = id;
        initialiseCriterias();
    }

    public Coupon(String id, String name) {
        this.id = id;
        this.name = name;
        initialiseCriterias();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    private void initialiseCriterias() {
        criterias = new ArrayList<>();
    }

}
