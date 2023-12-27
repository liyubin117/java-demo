package org.rick.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class CuratorTest {
    private final static Logger log = LoggerFactory.getLogger(CuratorTest.class);
    private final String connectString = "localhost:2181";
    CuratorFramework client;
    Integer sessionTimeoutMs = 5000;
    Integer baseSleepTimeMs = 1000;
    Integer maxRetries = 1;
    String namespace = "create";

    @Before
    public void before() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        client = CuratorFrameworkFactory
                .builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .retryPolicy(retryPolicy)
                .namespace(namespace)
                .build();
        client.start();
        log.info("客户端已开启");
    }

    @Test
    public void testCreate() throws Exception {
        client.create()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/node", "data".getBytes());
        log.info("create结束");
    }

    //自定义权限创建
    @Test
    public void testCreateAcl() throws Exception {
        Id ip = new Id("ip", "192.168.233.133");
        List<ACL> acl = Collections.singletonList(new ACL(ZooDefs.Perms.ALL, ip));
        client.create()
                .withMode(CreateMode.PERSISTENT)
                .withACL(acl)
                .forPath("/node1", "data".getBytes());
        log.info("create结束");
    }

    //递归创建节点
    @Test
    public void testCreate3() throws Exception {
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/node2/node33", "data".getBytes());
        log.info("create结束");
    }

    @Test
    public void testCreate4() throws Exception {
        //  异步方式创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        log.info(String.valueOf(curatorFramework == client));  // true
                        log.info("getResultCode(): " + curatorEvent.getResultCode());  // 0表示创建成功
                        log.info("getType(): " + curatorEvent.getType().toString());  // 获取操作类型 CREATE
                        log.info("getPath(): " + curatorEvent.getPath());   // 获取节点路径
                    }
                })
                .forPath("/node2/node38", "data".getBytes());
        log.info("create结束");
    }

    //更新节点
    @Test
    public void testSet() throws Exception {
        client.setData()
                .forPath("/node", "set".getBytes());
        log.info("设置完成");
    }

    //带版本更新
    @Test
    public void testSet2() throws Exception {
        client.setData()
                .withVersion(1)  // 带有版本号
                .forPath("/node", "12".getBytes());
        log.info("设置完成");
    }

    //带回调方法更新一个节点
    @Test
    public void testSet3() throws Exception {
        client.setData()
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        log.info(String.valueOf(curatorEvent.getResultCode()));  // 0
                        log.info(String.valueOf(curatorEvent.getType()));  // SET_DATA
                        log.info(curatorEvent.getPath());  // /node
                        log.info(curatorEvent.getStat().toString());  // 21474836489,21474836542,1620040487612,1620042328488,4,0,0,0,3,0,21474836489
                    }
                })
                .forPath("/node", "432".getBytes());
        log.info("设置完成");
    }

    @Test
    public void testDelete() throws Exception {
        client.delete()
                .forPath("/node");
        log.info("删除结束");
    }

    @Test
    public void testDelete1() throws Exception {
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath("/node2");
        log.info("删除结束");
    }

    @Test
    public void testDelete3() throws Exception {
        client.delete()
                .deletingChildrenIfNeeded()
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        log.info(String.valueOf(curatorEvent.getType()));  // DELETE
                        log.info(curatorEvent.getPath());  // /node1
                    }
                })
                .forPath("/node1");
        log.info("删除结束");
    }

    @Test
    public void testGet() throws Exception {
        byte[] data = client.getData()
                .forPath("/node2");
        log.info(new String(data));
    }

    @Test
    public void testGet2() throws Exception {
        Stat stat = new Stat();
        byte[] data = client.getData()
                .storingStatIn(stat)
                .forPath("/node2");
        log.info(new String(data));
        log.info(String.valueOf(stat.getVersion()));
    }

    @Test
    public void testGet3() throws Exception {
        client.getData()
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        log.info(new String(curatorEvent.getData()));  // 4134134
                        log.info(curatorEvent.getStat().toString());  // 21474836566,21474836566,1620042863998,1620042863998,0,0,0,0,7,0,21474836566
                        log.info(curatorEvent.getType().toString());  // GET_DATA
                    }
                })
                .forPath("/node2");
    }

    @Test
    public void testChildren() throws Exception {
        List<String> children = client.getChildren()
                .forPath("/");
        log.info(children.toString());
    }

    @Test
    public void testChildren2() throws Exception {
        client.getChildren()
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        log.info(curatorEvent.getPath()); // /
                        log.info(curatorEvent.getType().toString());  // CHILDREN
                        log.info(curatorEvent.getChildren().toString());  // [node, node2, node3]
                    }
                })
                .forPath("/");
    }

    @Test
    public void testExists() throws Exception {
        Stat stat = client.checkExists()
                .forPath("/node");
        if (stat != null)
            log.info(stat.toString());
        else
            log.info("节点不存在");
    }

    @Test
    public void testExists1() throws Exception {
        client.checkExists()
                .inBackground(new BackgroundCallback() {
                    @Override
                    public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                        log.info(curatorEvent.getType().toString());  // EXISTS
                        Stat stat = curatorEvent.getStat();
                        if (stat != null)
                            log.info(stat.toString());  // 21474836548,21474836548,1620042534164,1620042534164,0,0,0,0,0,0,21474836548
                        else
                            log.info("节点不存在");
                    }
                })
                .forPath("/node");
    }

    /**
     * curator提供了两种Watcher(Cache)来监听结点的变化
     * NodeCache : 只是监听某一个特定的节点，监听节点的新增、修改数据、删除。（子节点的新增、删除、修改均不会管）
     * PathChildrenCache : 监控一个ZNode的子节点. 当一个子节点增加、修改数据、删除时， PathCache会改变它的状态， 会包含最新的子节点， 子节点的数据和状态
     * 这个监视器可以多次使用
     */
    @Test
    public void testWatch() throws Exception {
        //  观察节点的变化
        NodeCache nodeCache = new NodeCache(client, "/node22");
        nodeCache.start();
        nodeCache.getListenable()
                .addListener(() -> {
                    ChildData currentData = nodeCache.getCurrentData();
                    if (currentData != null) {
                        log.info(currentData.getPath());
                        log.info(new String(currentData.getData()));
                    } else {
                        log.info("删除了某个节点");
                    }
                });
        Thread.sleep(60000); //睡30s
        nodeCache.close();
    }

    @Test
    public void testWatch2() throws Exception {
        //  观察节点的变化
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/node22", true);
        pathChildrenCache.start();
        pathChildrenCache.getListenable()
                .addListener((curatorFramework, pathChildrenCacheEvent) -> {
                    log.info(String.valueOf(pathChildrenCacheEvent.getType()));  // CHILD_ADDED, CHILD_REMOVED, CHILD_UPDATED
                    log.info(pathChildrenCacheEvent.getData().toString());  // ChildData{path='/node22/child', stat=21474836630,21474836630,1620044984259,1620044984259,0,0,0,0,2,0,21474836630, data=[50, 50]}
                    log.info(new String(pathChildrenCacheEvent.getData().getData()));
                    log.info(pathChildrenCacheEvent.getData().getPath());  // ChildData{path='/node22/child'
                    log.info(pathChildrenCacheEvent.getData().getStat().toString());  // 21474836630,21474836630,1620044984259,1620044984259,0,0,0,0,2,0,21474836630
                });
        Thread.sleep(60000); //睡30s
        pathChildrenCache.close();
    }

    /**
     * 事务
     */
    @Test
    public void testTransaction() throws Exception {
        client.inTransaction()
                .create().forPath("/node100", "100".getBytes())
                .and()  // 桥
                .create().forPath("/node101", "101".getBytes())
                .and()  // 桥
                .commit();  // 提交
        log.info("提交成功");
    }

    /**
     * 分布式锁
     * 排它锁，就是所有人都争抢同一个锁节点/lock，请求的时候，会在/lock内部添加一个顺序节点，当轮到自己的时候，就可以继续执行；否则就阻塞。释放锁的时候，会删除自己增加的顺序节点。（基本实现原理与分布式锁基本一致）
     */
    @Test
    public void testMutex() throws Exception {
        //  排他锁
        InterProcessLock lock = new InterProcessMutex(client, "/lock");
        log.info("等待获取锁对象");
        lock.acquire();
        for (int i = 0; i < 3; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        lock.release();
        log.info("释放锁");
    }

    /**
     * 读锁在运行的时候，写锁不允许工作，在阻塞。
     * 读锁运行的时候，允许另一个读锁也进入读数据
     * 写锁运行时，其他读写锁都不能进入
     */
    @Test
    public void testReadLock() throws Exception {
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(client, "/lock");
        InterProcessLock readLock = interProcessReadWriteLock.readLock();
        log.info("等待获取读锁对象");
        readLock.acquire();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        readLock.release();
        log.info("释放锁");
    }

    @Test
    public void testWriteLock() throws Exception {
        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(client, "/lock");
        InterProcessLock writeLock = interProcessReadWriteLock.writeLock();
        log.info("等待获取写锁对象");
        writeLock.acquire();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(3000);
            System.out.println(i);
        }
        writeLock.release();
        log.info("释放锁");
    }


    @After
    public void after() {
        client.close();
        log.info("客户端已关闭");
    }
}
