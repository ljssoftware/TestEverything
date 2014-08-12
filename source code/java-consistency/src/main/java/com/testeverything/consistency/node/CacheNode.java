package com.testeverything.consistency.node;

import java.util.Comparator;

/**
 * 缓存节点的相关数据
 * Created by lijinsheng on 14-8-12.
 */
public class CacheNode {
    private long hashCode;
    private String ip;

    public long getHashCode() {
        return hashCode;
    }

    public void setHashCode(long hashCode) {
        this.hashCode = hashCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "CacheNode{" +
                "hashCode=" + hashCode +
                ", ip='" + ip + '\'' +
                '}';
    }

    /**
     * 缓存节点的比较器
     * Created by lijinsheng on 14-8-12.
     */
    public static class CacheNodeComparator implements Comparator<CacheNode> {
        @Override
        public int compare(CacheNode cacheNode1, CacheNode cacheNode2) {
            if (cacheNode1.getHashCode() == cacheNode2.getHashCode()) {
                return 0;
            }

            if (cacheNode1.getHashCode() < cacheNode2.getHashCode()) {
                return -1;
            }
            return 1;
        }
    }
}
