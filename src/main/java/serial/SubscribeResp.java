package serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Ardien
 * @Date 9/15/2020 9:23 AM
 * @Email ardien@126.com
 * @Version 1.0
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubscribeResp implements Serializable {

    private static final long serialVersionUID = 23L;
    private int subResp;
    private int respCode;
    private String desc;
}
