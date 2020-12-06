package com.zsh.common.thread;

import org.assertj.core.util.DateUtil;

import java.util.Date;

public class SchedulingRunnable implements Runnable {

    @Override
    public void run() {
        Date date = DateUtil.now();
        System.out.println("现在执行函数的时间为："+date);
    }

}
