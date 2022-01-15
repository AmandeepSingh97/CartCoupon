import models.Attribute;
import models.Coupon;
import models.Criteria;
import models.Item;
import models.OperatorType;
import org.junit.Assert;
import org.junit.Test;
import repositories.AttributeRepository;
import repositories.CriteriaRepository;

public class BasicTest {

    @Test
    public void shouldApplyCoupon() {

        final Attribute dairyCategory = createAttribute("Category", "dairy");
        Item milk = new Item("1", "200").addAttribute(dairyCategory);

        Criteria dairyProduct = createCriteria("isDairyProd", "dairy", "Category" , OperatorType.EQUAL);
        Criteria priceCriteria = createCriteria("priceCriteria", "100", "Price" , OperatorType.GREATER_THAN_OR_EQUAl);

        Coupon dairyProdCoupon = new Coupon("1", "CategoryCoupon")
                .addCriteria(dairyProduct)
                .addCriteria(priceCriteria);

        Assert.assertTrue(dairyProdCoupon.apply(milk));
    }

    @Test
    public void shouldNotApplyCoupon() {

        final Attribute dairyCategory = createAttribute("Category", "grocery");
        Item milk = new Item("1", "200").addAttribute(dairyCategory);

        Criteria dairyProduct = createCriteria("isDairyProd", "dairy", "Category" , OperatorType.EQUAL);
        Criteria priceCriteria = createCriteria("priceCriteria", "100", "Price" , OperatorType.GREATER_THAN_OR_EQUAl);

        Coupon dairyProdCoupon = new Coupon("1", "CategoryCoupon")
                .addCriteria(dairyProduct)
                .addCriteria(priceCriteria);

        Assert.assertFalse(dairyProdCoupon.apply(milk));
    }

    private Attribute createAttribute(String name, String value) {
          return AttributeRepository.createAttribute(name, value);
    }

    private Criteria createCriteria(String name, String value, String attribute, OperatorType operatorType) {
        return CriteriaRepository.createCriteria(name, value, attribute, operatorType);
    }
}
