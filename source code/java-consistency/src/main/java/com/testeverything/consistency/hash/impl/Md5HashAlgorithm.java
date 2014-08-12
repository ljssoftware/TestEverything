package com.testeverything.consistency.hash.impl;

import com.testeverything.consistency.hash.HashAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * M5的Hash 算法
 * Created by lijinsheng on 14-8-12.
 */
public class Md5HashAlgorithm implements HashAlgorithm {
    public ThreadLocal<MessageDigest> local = new ThreadLocal<MessageDigest>() {
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("MD5 Algorithm is not found");
            }
        }

        ;
    };

    @Override
    public long getHash(String memKey) {
        MessageDigest md5 = local.get();

        md5.reset();
        md5.update(memKey.getBytes());
        byte[] bKey = md5.digest();
        long res = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16)
                | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF);
        return res;
    }
}
