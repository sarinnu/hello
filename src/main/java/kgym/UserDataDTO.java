package kgym;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// ユーザデータをDB操作用にマッピングしたもの
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Scope
public class UserDataDTO {
    private int userID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private int deleteFlg;

}
