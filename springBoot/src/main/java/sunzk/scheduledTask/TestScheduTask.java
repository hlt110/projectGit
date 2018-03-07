package sunzk.scheduledTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 82681 on 2017/12/29.
 */
@Component
public class TestScheduTask {
    @Scheduled(fixedRate = 1000)
    public void test(){
        System.out.println("我正在执行。。。。" + System.currentTimeMillis());
    }
}
