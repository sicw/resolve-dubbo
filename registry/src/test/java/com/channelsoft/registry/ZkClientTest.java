package com.channelsoft.registry;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.junit.Test;

import java.util.List;

public class ZkClientTest {
    @Test
    public void testZkClientCreateEphemeral(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            zkClient.createEphemeral("/ephemeral","this is data");
            Thread.sleep(50000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }
    @Test
    public void testZkClientCreateEphemeralSequential(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            for (int i = 0; i < 3; i++) {
                zkClient.createEphemeralSequential("/ephemeralSeq","this is data");
            }
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }
    @Test
    public void testZkClientCreatePersistent(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            zkClient.createPersistent("/persistent","this is data");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }
    @Test
    public void testZkClientCreatePersistentSequential(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            for (int i = 0; i < 3; i++) {
                zkClient.createPersistentSequential("/persistentSeq","this is data");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }

    @Test
    public void testSubscribeChildChanges(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            zkClient.subscribeChildChanges("/persistent", new IZkChildListener() {
                @Override
                public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                    System.out.println("parentPath: " + parentPath);
                    System.out.println("currentChilds: " + currentChilds);
                }
            });
            Thread.sleep(30000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }

    @Test
    public void testSubscribeDataChanges(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            zkClient.setZkSerializer(new BytesPushThroughSerializer());
            zkClient.subscribeDataChanges("/persistent/path1", new IZkDataListener() {

                @Override
                public void handleDataChange(String dataPath, Object data) throws Exception {
                    System.out.println("data change");
                    System.out.println("dataPath: " + dataPath);
                    System.out.println("data: " + new String((byte[])data));
                }

                @Override
                public void handleDataDeleted(String dataPath) throws Exception {
                    System.out.println("data delete");
                    System.out.println("dataPath: " + dataPath);
                }
            });
            Thread.sleep(300000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }

    @Test
    public void testWatche(){
        String zookeeperAddress = "localhost:2181";
        ZkClient zkClient = null;
        try {
            zkClient = new ZkClient(new ZkConnection(zookeeperAddress), 3000);
            System.out.println(zkClient.watchForChilds("/persistent"));
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (zkClient != null) {
                zkClient.close();
                zkClient = null;
            }
        }
    }
}