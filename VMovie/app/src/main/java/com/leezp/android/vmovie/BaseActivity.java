package com.leezp.android.vmovie;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 项目开发流程
 *      1.梳理需求
 *      2.确认需求
 *      3.为需求做知识储备
 *
 *      4.技能储备
 *      5.技术选型
 *
 *      6.创建基础工程
 *          - 新项目，添加好所需的各种依赖
 *          - 封装常用的工具类
 *          - 创建自己的基类
 *
 *      7.使用静态数据填充页面
 *
 *      8.网络请求，填充真实数据
 *
 *
 *
 *
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

}
