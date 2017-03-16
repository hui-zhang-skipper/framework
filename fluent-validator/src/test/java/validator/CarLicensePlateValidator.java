package validator;

import com.hui.zhang.fluentvalidator.Validator;
import com.hui.zhang.fluentvalidator.ValidatorContext;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class CarLicensePlateValidator implements Validator<String> {
    public boolean validate(ValidatorContext context, String s) {
        Car car=context.getAttribute("car",Car.class);
        car.setCarLicensePlate("woded");
        if (s.startsWith("zhang")) {
            context.addErrorMsg("this is error");
            return false;
        }
        return true;
    }

    public boolean accept(ValidatorContext context, String s) {
        return true;
    }
}
