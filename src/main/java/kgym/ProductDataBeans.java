package kgym;

import org.springframework.context.annotation.Scope;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// 商品情報を格納するデータビーンズ
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Scope
public class ProductDataBeans{
    private String imageURL;
    private String productName;
    private int price;
    private int searchResultNum;
    private String query;
    private String itemURL;
    private String hit;
	private String rating;
	private String description;
	private String availability;
	private String code;
	private int index;
}
