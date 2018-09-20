package com.gystar.master.Config.netApi;

import com.gystar.master.bean.BeginrechargeBean;
import com.gystar.master.bean.ByconditionBean;
import com.gystar.master.bean.CityListBean;
import com.gystar.master.bean.HistoryRecBean;
import com.gystar.master.bean.HistoryRemarkBean;
import com.gystar.master.bean.HomeDataBean;
import com.gystar.master.bean.MessageBean;
import com.gystar.master.bean.MyClientPerson;
import com.gystar.master.bean.PersonalBean;
import com.gystar.master.bean.TopBean;
import com.gystar.master.bean.UserBean;
import com.gystar.master.bean.WorkOrderBean;
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

    @GET(AppConfig.CUSTOMERLISTSHOW)
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
     * 接口地址	/mobileterminal/login/loginVerfy
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * phone	String	Y	手机号
     * smscode	String	Y	验证码
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * state	int	Y	是在新用户（0.不是；1.是）
     * ID	int	Y	用户id
     */

    @GET(AppConfig.LOGINVERFY)
    Observable<UserBean> getLoginVerfy(@Query("phone") String phone,
                                       @Query("smscode") String smscode);


    /**
     * 接口地址	/mobileterminal/home/queryInformation
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	int	Y	用户id
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * data	list	Y	我的消息列表
     * id	int	Y	消息id
     * content	String	Y	消息内容
     * create_time	date	Y	创建时间
     */

    @GET(AppConfig.QUERYINFORMATION)
    Observable<MessageBean> getQueryInformation(@Query("user_id") String user_id);


    /**
     * 工单列表显示
     * 接口地址	/mobileterminal/workOrder/workOrderShow
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	long	Y	用户id
     * currPage	String	Y	页数（默认为1）
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * data	list	Y	工单数据
     * id	int	Y	工单id
     * name	String	Y	姓名
     */

    @GET(AppConfig.WORKORDERSHOW)
    Observable<WorkOrderBean> getWorkOrderShow(@Query("user_id") String user_id, @Query("currPage") int currPage);


    /**
     * 查看历史备注信息
     * 接口地址	/mobileterminal/workOrder/historyRemark
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * work_id	long	Y	工单id
     * currPage	String	Y	页数（默认为1）
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * data	list	Y	历史备注数据
     * id	int	Y	历史备注id
     * create_time	String	Y	创建时间
     * remark	String	Y	备注
     */

    @GET(AppConfig.HISTORYREMARK)
    Observable<HistoryRemarkBean> getHistoryremark(@Query("work_id") String work_id,
                                                   @Query("currPage") int currPage);

    /**
     * 接口地址	/mobileterminal/recharge/historyRec
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * phone	String	Y	用户手机号
     * currPage	String	Y	页数（默认为1）
     */
    @GET(AppConfig.HISTORYREC)
    Observable<HistoryRecBean> getHistoryrec(@Query("phone") String phone,
                                             @Query("currPage") int currPage);


    /**
     * 根据条件查询充值信息
     * 接口地址	/mobileterminal/recharge/byCondition
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * keyword	String	Y	用户手机号
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * data	Map	Y	用户个人信息
     * id	long	Y	用户id
     * phone	String	Y	用户手机号
     * nike_name	String 	Y	用户昵称
     */
    @GET(AppConfig.BYCONDITION)
    Observable<ByconditionBean> getBycondition(@Query("keyword") String phone);


    /**
     * 最高价购
     * 接口地址	/mobileterminal/home/topPriceBuy
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	long	Y	用户id
     * customer_id	long	Y	客户信息id
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     */
    @GET(AppConfig.TOPPRICEBUY)
    Observable<DataBeanCallBack> getTopPriceBuy(@Query("user_id") String user_id, @Query("customer_id") String customer_id);


    /**
     * 客户个人信息查询
     * 接口地址	/mobileterminal/home/personMessageShow
     * 请求方式	POST
     * 返回格式	JSON
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	String	Y	用户id
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     * data	list	Y	返回集合
     * call_customer_num	int	Y	总呼出次数
     */
    @GET(AppConfig.PERSONMESSAGESHOW)
    Observable<PersonalBean> getPersonMessageShow(@Query("user_id") String user_id);


    /**
     * 联系客户
     * 接口地址	/mobileterminal/home/callCustomer
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * user_id	long	Y	用户id
     * customer_id	long	Y	客户id
     * call_duration	long	Y	通话时长
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     */
    @GET(AppConfig.CALLCUSTOMER)
    Observable<DataBeanCallBack> getCallCustomer(@Query("user_id") String user_id, @Query("customer_id") String customer_id,
                                                 @Query("call_duration") String call_duration);


    /**
     * 增加最新备注信息
     * 接口地址	/mobileterminal/workOrder/addWorkOrderRemark
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * work_id	long	Y	工单id
     * remark	String	Y	备注
     */
    @GET(AppConfig.ADDWORKORDERREMARK)
    Observable<DataBeanCallBack> getAddWorkorderRemark(@Query("work_id") String work_id, @Query("remark") String remark);


    /**
     * 接口地址	/mobileterminal/home/priceBuyBatch
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * customerIds	String	Y	批量客户id (例：1,2,3)以，相隔
     * user_id	String	Y	用户id
     * 返回说明：
     * 参数	类型	是否必须	说明
     * code	int	Y	返回码(0.操作成功)
     * msg	String	Y	返回信息
     */
    @GET(AppConfig.PRICEBUYBATCH)
    Observable<DataBeanCallBack> getPricebuybatch(@Query("customerIds") String customerIds, @Query("user_id") String user_id);


    /**
     * 接口地址	/mobileterminal/login/addUserMessage
     * 请求方式	GET
     * 返回格式	JSONP
     * 请求说明：
     * 参数	类型	是否必须	说明
     * phone	String	Y	手机号
     * cityCode	String	Y	城市id
     * nickName	String	Y	昵称
     * workYears	String	Y	工作年限
     */
    @GET(AppConfig.ADDUSERMESSAGE)
    Observable<DataBeanCallBack> getAddusermessage(@Query("phone") String phone, @Query("cityCode") String cityCode
            , @Query("nickName") String nickName, @Query("workYears") String workYears);


}
