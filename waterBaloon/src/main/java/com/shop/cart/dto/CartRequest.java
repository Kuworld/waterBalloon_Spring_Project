package com.shop.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long cartNum;
    private Long uNum;
    private Long iNum;
    private int quantity;
    

    // 추가적인 로직이 필요한 경우 메서드를 작성할 수 있습니다.
}
