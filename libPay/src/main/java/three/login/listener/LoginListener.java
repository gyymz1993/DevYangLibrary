package three.login.listener;


import three.login.bean.BaseToken;
import three.login.result.LoginResult;

/**
 * Created by onetouch on 2017/11/21.
 */

public abstract class LoginListener {

    public abstract void loginSuccess(LoginResult result);

    public void beforeFetchUserInfo(BaseToken token) {
    }

    public abstract void loginFailure(Exception e);

    public abstract void loginCancel();
}
