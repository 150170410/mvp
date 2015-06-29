package com.example.admin.mvp.presenter;

import android.os.Handler;

import com.example.admin.mvp.biz.IUserBiz;
import com.example.admin.mvp.biz.IUserLoginView;
import com.example.admin.mvp.biz.OnLoginListener;
import com.example.admin.mvp.biz.UserBiz;
import com.example.admin.mvp.model.User;

/**
 * Created by admin on 2015/6/28.
 */
public class UserLoginPresenter
{
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login()
    {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener()
        {
            @Override
            public void loginSuccess(final User user)
            {
                //��Ҫ��UI�߳�ִ��
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed()
            {
                //��Ҫ��UI�߳�ִ��
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }



}
