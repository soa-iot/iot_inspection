package cn.zg.controller;

import java.util.ArrayDeque;

public class Test {
	public static void main(String[] args) {
		 ArrayDeque<String> ad = new ArrayDeque<String>();

        ad.add("D");
        ad.offerFirst("E");
        ad.addFirst("F");
        ad.offer("A");
        ad.addLast("B");
        ad.offerLast("C");

        System.out.println("队列中的元素：" + ad.toString());
        System.out.println("队列中的第一个元素：" + ad.peekFirst());
        System.out.println("移除队列中的元素：" + ad.remove());
        System.out.println("队列中的元素：" + ad.toString());
        System.out.println("移除队列中的元素：" + ad.pollLast());
        System.out.println("队列中的元素：" + ad.toString()); 
	}
}
