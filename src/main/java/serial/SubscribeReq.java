package serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Ardien
 * @Date 9/15/2020 9:19 AM
 * @Email ardien@126.com
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubscribeReq implements Serializable {
    private static final long serialVersionUID = 24L;
    private int subReqId;
    private String username;
    private String productName;
    private String phoneNumber;
    private String address;
}
