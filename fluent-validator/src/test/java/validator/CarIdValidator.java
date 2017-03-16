package validator;

import com.hui.zhang.fluentvalidator.Validator;
import com.hui.zhang.fluentvalidator.ValidatorContext;

/**
 * @author zhanghui32
 * @date 2017/3/15
 */
public class CarIdValidator implements Validator<Integer> {
    public boolean validate(ValidatorContext context, Integer integer) {
        return false;
    }

    public boolean accept(ValidatorContext context, Integer integer) {
        return true;
    }
}
