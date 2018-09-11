package com.gystar.master.Config.netApi;

import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.bean.CityListBean;
import com.gystar.master.bean.HomeDataBean;
import com.gystar.master.bean.MyClientPerson;
import com.gystar.master.bean.TopBean;
import com.gystar.master.bean.UserBean;
import com.utils.gyymz.http.callback.DataBeanCallBack;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by guoh on 2018/7/25.
 * 功能描述：
 * 需要的参数：
 */
public interface ModuleApi {

    /**
     * 接口地址	/mobileterminal/recharge/beginRecharge
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	String	Y	用户id
     * recid	String	Y	充值包id
     * recharge_phone	String	Y	充值电话
     * recharge_name	String	Y	充值昵称
     * state	String	Y	充值状态（0.自冲；1.代充）
     *
     * @return
     */

    @GET(AppConfig.BEGINRECHARGE)
    Observable<BeginrechargeBean> getBeginrecharge(@Query("user_id") String user_id,
                                                   @Query("recid") String recid,
                                                   @Query("recharge_phone") String recharge_phone,
                                                   @Query("recharge_name") String recharge_name,
                                                   @Query("state") String state
    );

    /**
     * 接口地址	/mobileterminal/bid/onTermsCustomerList
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	long	Y	用户id
     * state	int	Y	查询状态（0.全部，1.进行中，2.未开始）
     * currPage	String	Y	页数（默认为1）
     */

    @GET(AppConfig.ONTERMSCUSTOMERLIST)
    Observable<MyClientPerson> getOntermsCustomerList(@Query("user_id") String user_id,
                                                      @Query("state") String state,
                                                      @Query("currPage") int currPage
    );

    /**
     * HOMESHOW
     */

    @GET(AppConfig.HOMESHOW)
    Observable<HomeDataBean> getHomeshow(@Query("user_id") String user_id);


    /**
     * 充值包列表查询
     * 接口地址	/mobileterminal/recharge/rechargeShow
     */
    @GET(AppConfig.RECHARGESHOW)
    Observable<TopBean> getRechargeShow();


    /**
     * 接口地址	/mobileterminal/login/gainArea
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * data	list	Y	区域集合
     * id	int	Y	城市id
     * name	String	Y	城市名称
     */
    @GET(AppConfig.GAINAREA)
    Observable<CityListBean> gainArea();


    /**
     * 短信发送验证码
     * 接口地址	/mobileterminal/login/loginSMS
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * phone	String	Y	手机号
     *
     * @return
     */
    @GET(AppConfig.LOGINSMS)
    Observable<DataBeanCallBack> getLoginSMS(@Query("phone") String phone);

    /**
     * 信验证登陆
     接口地址	/mobileterminal/login/loginVerfy
     请求方式	GET
     返回格式	JSONP
     请求说明：
     参数	类型	是否必须	说明
     phone	String	Y	手机号
     smscode	String	Y	验证码
     返回说明：
     参数	类型	是否必须	说明
     code	int	Y	返回码(0.操作成功)
     msg	String	Y	返回信息
     state	int	Y	是在新用户（0.不是；1.是）
     ID	int	Y	用户id
     */

    @GET(AppConfig.LOGINVERFY)
    Observable<UserBean> getLoginVerfy(@Query("phone") String phone,
                                       @Query("smscode") String smscode);
}
