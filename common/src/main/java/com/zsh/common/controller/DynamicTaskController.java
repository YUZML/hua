package com.zsh.common.controller;

import com.zsh.common.thread.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

@RestController
@Component
public class DynamicTaskController {


    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    /**
     *
     * ThreadPoolTaskScheduler：线程池任务调度类，能够开启线程池进行任务调度。
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，
     * 在这个方法需要添加两个参数，Runnable（线程接口类） 和CronTrigger（定时任务触发器）
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     */
    private ScheduledFuture<?> future;

    /**
     * 启动任务
     **/
    @RequestMapping("/startTask")
    public String startCron() {
        future = threadPoolTaskScheduler.schedule(new SchedulingRunnable(), new CronTrigger("0/5 * * * * *"));


        System.out.println("DynamicTaskController.startCron()");
//        try{
//            //get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
//            //future的get()设定超时时间
//            Object o = future.get(3900, TimeUnit.MILLISECONDS);
//            System.out.println("===>>>  "+o);
//            Object o1 = future.get();
//            System.out.println("===>>>  "+o1);
//        }
//        catch ( Exception e){
//            System.out.println(e);
//        }
        return "startTask";
    }

    /**
     * 启此任务
     **/
    @RequestMapping("/stopTask")
    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTaskController.stopCron()");
        return "stopTask";
    }

    /**
     * 变更任务间隔，再次启动
     **/
    @RequestMapping("/changeCron")
    public String changeCron() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new SchedulingRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTaskController.changeCron()");
        return "changeCron";
    }

}
