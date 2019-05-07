package com.channelsoft.registry;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * @author sicwen
 * @date 2019/05/07
 */
public class CuratorTest {
    @Test
    public void staticFactoryTest(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.130.41.36:2181", 5000, 3000, retryPolicy);
        client.start();
    }

    @Test
    public void fluentTest(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                        .connectString("10.130.41.36:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .namespace("base")
                        .build();
        client.start();

    }

    @Test
    public void createNode() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();

        client.create().withMode(CreateMode.PERSISTENT).forPath("/base","init".getBytes());
    }

    @Test
    public void createNodeWithParent() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();

        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/q/q1","init".getBytes());
    }

    @Test
    public void deleteNodeWithChildren() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();

        client.delete().deletingChildrenIfNeeded().forPath("/q");
    }

    @Test
    public void writeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();
        client.setData().forPath("/base","init...".getBytes());
    }

    @Test
    public void readData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();
        Stat stat = new Stat();
        System.out.println(new String(client.getData().storingStatIn(stat).forPath("/base")));
        System.out.println(stat);
    }

    /**
     * Curator提供了三种Watcher(Cache)来监听结点的变化：
     * Path Cache：监视一个路径下
     *          1）孩子结点的创建
     *          2）删除
     *          3）以及结点数据的更新。
     *          产生的事件会传递给注册的PathChildrenCacheListener。
     * Node Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。
     * Tree Cache：Path Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
     */
    @Test
    public void testPathListener() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();

        PathChildrenCache pathCache = new PathChildrenCache(client,"/base",false);
        pathCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println(Thread.currentThread().getName() +"-->"+ pathChildrenCacheEvent.toString());
            }
        });
        pathCache.start();

        System.in.read();
    }

    @Test
    public void testNodeListener() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();

        NodeCache nodeCache = new NodeCache(client,"/base");
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node has changed...");
            }
        });
        nodeCache.start();

        System.in.read();
    }

    @Test
    public void testTreeListener() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("10.130.41.36:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("root")
                .build();
        client.start();

        TreeCache treeCache = new TreeCache(client,"/base");
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println(event.toString());
            }
        });
        treeCache.start();

        System.in.read();
    }
}