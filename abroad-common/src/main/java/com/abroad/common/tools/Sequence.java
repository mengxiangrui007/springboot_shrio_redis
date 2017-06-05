package com.abroad.common.tools;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* @ClassName: Sequence
* @Description: TODO(主键生成)
* @author: mengxr
* @date 2017年4月1日 下午3:10:03
*/
public class Sequence {
    private static final long ONE_STEP = 10;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private static int count = 0;
    @SuppressWarnings("finally")
    public static Long generateId() 
    {
        LOCK.lock();
        try {
            if (lastCount == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (java.lang.InterruptedException e) {
                        }
                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }
            count = lastCount++;
        }
        finally 
        {
            LOCK.unlock();
            return Long.parseLong((lastTime+""+String.format("%03d",count))); 
        }
    }
    public static void main(String[] args)
    {
        //测试
        for(int i=0;i<100000;i++)
        {
            System.out.println(generateId());
        }        
    }
}