package com.test.java.thread;

import java.util.LinkedList;

/**
 * Created by 123 on 2016/2/2.
 */
public class WorkQueue {
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedList queue;

    public WorkQueue(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new PoolWorker[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized (queue) {
            queue.addLast(r);
            queue.notify();
// 大多数专家建议使用 notifyAll() 而不是 notify() ，而且理由很充分：
// 使用 notify() 具有难以捉摸的风险，只有在某些特定条件下使用该方法才是合适的。
// 另一方面，如果使用得当， notify() 具有比 notifyAll() 更可取的性能特征；
// 特别是， notify() 引起的环境切换要少得多，这一点在服务器应用程序中是很重要的。
        }
    }

    private class PoolWorker extends Thread {
        public void run() {
            Runnable r;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    r = (Runnable) queue.removeFirst();
                }
                //if we don't catch RuntimeException,
                //the pool could leak threads
                try {
                    r.run();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    //you might want to log something here
                }
            }
        }
    }

}
