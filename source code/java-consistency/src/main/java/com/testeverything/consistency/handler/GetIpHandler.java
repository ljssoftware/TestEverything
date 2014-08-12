package com.testeverything.consistency.handler;

import com.testeverything.consistency.hash.HashAlgorithm;
import com.testeverything.consistency.node.CacheNode;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * 连接工厂的相关内容
 * Created by lijinsheng on 14-8-12.
 */
public class GetIpHandler {
    private List<String> addressList;
    private LinkedList<CacheNode> linkedList;
    private HashAlgorithm hashAlgorithm;
    private int virtualNumber; // 虚拟节点的复制个数

    public void setAddressList(List<String> addressList) {
        if (CollectionUtils.isEmpty(addressList)) {
            throw new IllegalArgumentException("传入的地址列表不能为空");
        }
        this.addressList = addressList;
    }

    public void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    public void setVirtualNumber(int virtualNumber) {
        if (virtualNumber <= 0) {
            throw new IllegalArgumentException("插入的virtualNumber小于等于0");
        }
        this.virtualNumber = virtualNumber;
    }

    public void afterPropertiesSet() {
        linkedList = new LinkedList<CacheNode>();
        for (String address : addressList) {

            //创建虚拟节点
            for (int i = 0; i < virtualNumber; i++) {
                CacheNode cacheNode = new CacheNode();
                cacheNode.setHashCode(hashAlgorithm.getHash(address + "#" + i));
                cacheNode.setIp(address);
                linkedList.add(cacheNode);
            }
        }
        Collections.sort(linkedList, new CacheNode.CacheNodeComparator());
    }

    public String getAddress(String memKey) {
        long keyHashCode = hashAlgorithm.getHash(memKey);
        if (keyHashCode > linkedList.getLast().getHashCode() || keyHashCode <= linkedList.getFirst().getHashCode()) {
            return linkedList.getFirst().getIp();
        }

        Iterator<CacheNode> iterator = linkedList.iterator();
        CacheNode previousNode = iterator.next();
        while (iterator.hasNext()) {
            CacheNode cacheNode = iterator.next();
            if (previousNode.getHashCode() < keyHashCode && cacheNode.getHashCode() >= keyHashCode) {
                return cacheNode.getIp();
            }
            previousNode = cacheNode;
        }
        return null;
    }
}
