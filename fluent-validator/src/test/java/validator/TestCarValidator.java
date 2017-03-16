package validator;


import com.hui.zhang.fluentvalidator.FluentValidator;
import com.hui.zhang.fluentvalidator.Result;
import com.hui.zhang.fluentvalidator.ResultCollectors;
import org.junit.Test;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class TestCarValidator {

    @Test
    public void testCarNullProperty() {

        Car car=new Car();
        car.setCarLicensePlate("name");
        Result ret = FluentValidator.checkAll()
                .putAttribute2Context("car",car)
                .on("zhang2323", new CarLicensePlateValidator())
                .on(11, new CarIdValidator()).when(2 == 4)
                .doValidate().result(ResultCollectors.simpleCollector());
        System.out.println(ret);
    }
}
