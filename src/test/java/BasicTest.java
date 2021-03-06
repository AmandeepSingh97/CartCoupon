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

        final Attribute groceryCategory = createAttribute("Category", "grocery");
        Item milk = new Item("1", "200").addAttribute(groceryCategory);

        Criteria dairyProduct = createCriteria("isDairyProd", "dairy", "Category" , OperatorType.EQUAL);
        Criteria priceCriteria = createCriteria("priceCriteria", "100", "Price" , OperatorType.GREATER_THAN_OR_EQUAl);

        Coupon dairyProdCoupon = new Coupon("1", "CategoryCoupon")
                .addCriteria(dairyProduct)
                .addCriteria(priceCriteria);

        Assert.assertFalse(dairyProdCoupon.apply(milk));
    }

    @Test
    public void shouldNotApplyIfAttributeAbsent() {

        final Attribute dairyCategory = createAttribute("Category", "dairy");
        Item milk = new Item("1", "200")
                .addAttribute(dairyCategory);

        Criteria dairyProduct = createCriteria("isDairyProd", "dairy", "Category" , OperatorType.EQUAL);
        Criteria stateName = createCriteria("StateCriteria", "UP", "State", OperatorType.EQUAL);

        Coupon dairyProdCoupon = new Coupon("1", "CategoryCoupon")
                .addCriteria(dairyProduct)
                .addCriteria(stateName);

        Assert.assertFalse(dairyProdCoupon.apply(milk));
    }

    @Test
    public void shouldApplyMultipleCoupons() {

        final Attribute dairyCategory = createAttribute("Category", "dairy");
        final Attribute state = createAttribute("State", "UP");
        final Attribute size = createAttribute("Size", "large");
        Item milk = new Item("1", "200")
                .addAttribute(dairyCategory)
                .addAttribute(state)
                .addAttribute(size);

        Criteria dairyProduct = createCriteria("isDairyProd", "dairy", "Category" , OperatorType.EQUAL);
        Criteria stateName = createCriteria("StateCriteria", "UP", "State", OperatorType.EQUAL);

        Coupon dairyProdCoupon = new Coupon("1", "CategoryCoupon")
                .addCriteria(dairyProduct)
                .addCriteria(stateName);

        Criteria sizeCrietria = createCriteria("isLarge", "large", "Size", OperatorType.EQUAL);
        Coupon UPStateLargePacketCoupon = new Coupon("1", "CategoryCoupon")
                .addCriteria(stateName)
                .addCriteria(sizeCrietria);

        Assert.assertTrue(dairyProdCoupon.apply(milk));
        Assert.assertTrue(UPStateLargePacketCoupon.apply(milk));
    }

    private Attribute createAttribute(String name, String value) {
          return AttributeRepository.createAttribute(name, value);
    }

    private Criteria createCriteria(String name, String value, String attribute, OperatorType operatorType) {
        return CriteriaRepository.createCriteria(name, value, attribute, operatorType);
    }
}
