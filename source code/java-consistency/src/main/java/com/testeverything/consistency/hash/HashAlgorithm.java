package com.testeverything.consistency.hash;

/**
 * Hash 算法接口
 * Created by lijinsheng on 14-8-12.
 */
public interface HashAlgorithm {
    /**
     * 获取hash 引子的 hash值
     *
     * @param hashPrimers hash 引子
     * @return 计算出来的hash值
     */
    long getHash(String hashPrimers);
}
