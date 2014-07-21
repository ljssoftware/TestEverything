package com.testeverything.concurrency.test;

import com.testeverything.concurrency.chapter1.Sequence;
import com.testeverything.concurrency.chapter1.UnsafeSequence;

/**
 * test sequence
 * Created by lijinsheng on 14-7-3.
 */
public class TestSequence {
    public static void main(String[] args) {
        System.out.println("unsafeSequence:" + UnsafeSequence.getNext());
        System.out.println("safe sequence:" + Sequence.getNext());

        System.out.println("unsafeSequence:" + UnsafeSequence.getNext());
        System.out.println("safe sequence:" + Sequence.getNext());

        System.out.println("unsafeSequence:" + UnsafeSequence.getNext());
        System.out.println("safe sequence:" + Sequence.getNext());

        System.out.println("unsafeSequence:" + UnsafeSequence.getNext());
        System.out.println("safe sequence:" + Sequence.getNext());

        System.out.println("unsafeSequence:" + UnsafeSequence.getNext());
        System.out.println("safe sequence:" + Sequence.getNext());
    }
}
