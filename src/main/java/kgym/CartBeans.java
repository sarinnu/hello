package kgym;
    
import java.util.HashMap;

import org.springframework.context.annotation.Scope;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Data
@Scope
public class CartBeans{
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private ProductDataBeans pdb;
    private HashMap<String,ProductDataBeans> item;
}    
