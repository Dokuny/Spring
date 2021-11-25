package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {

        return Item.class.isAssignableFrom(clazz);
        // 인자로 넘어오는 클래스가 Item 클래스인지 확인
        // Item의 자식클래스여도 통과
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        // 검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName","required");
        }

//        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,"itemName","required"); 위의 검증 처리를 이렇게 해도 된다.빈칸이나 공백 처리만 가능

        if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice()>1000000){
            errors.rejectValue("price","range",new Object[]{1000,1000000},null);
        }

        if(item.getQuantity() == null || item.getQuantity()>= 9999){
            errors.rejectValue("quantity","max",new Object[]{9999},null);
        }

        // 특정 필드가 아닌 복함 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                errors.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }


    }
}
