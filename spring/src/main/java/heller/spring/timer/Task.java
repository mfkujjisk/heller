package heller.spring.timer;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhouzhanghe on 2016/11/24.
 */
@Component("task")
@Lazy
public class Task {
    public Task(){
        System.out.println("construct Task!!!");
    }
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "* * * * * ?")
    public void pushQuestionnaire() {

        System.out.println("定时任务1，自动执行:" + format.format(new Date()));
    }
}