package three.pay.instance;


import com.tencent.mm.opensdk.modelpay.PayReq;

import three.pay.PayUrlGeneratro;
import three.pay.bean.PayInfo;


/**
 * Created by onetouch on 2017/11/22.
 */

public class AlPayUrlGeneratro extends PayUrlGeneratro {
    public AlPayUrlGeneratro(PayInfo payInfo) {
        super(payInfo);
    }

    @Override
    public PayReq getPayReq() {
        return null;
    }
}
