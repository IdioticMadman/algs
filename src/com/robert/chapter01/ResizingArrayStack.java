package com.robert.chapter01;

import java.util.Iterator;

/**
 * 可变长度数组实现的栈结构
 *
 * @param <Item> 数据类类型
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] items;
    private int index = 0;

    public ResizingArrayStack() {
        this.items = (Item[]) new Object[1];
    }

    public Item pop() {
        if (isEmpty()) return null;
        final Item item = items[--index];
        //从容器中去除,防止游离状态
        items[index] = null;
        //
        if (index < items.length / 4) resize(items.length / 2);
        return item;
    }

    public void push(Item item) {
        final int length = items.length;
        //满了需要扩容
        if (isFull()) resize(length * 2);
        items[index++] = item;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    private boolean isFull() {
        return index == items.length;
    }

    public void resize(int max) {
        Item[] data = (Item[]) new Object[max];
        for (int i = 0; i < index; i++) {
            data[i] = items[i];
        }
        items = data;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator(index);
    }

    public class ReverseArrayIterator implements Iterator<Item> {

        private int mIndex;

        public ReverseArrayIterator(int index) {
            this.mIndex = index;
        }

        @Override
        public boolean hasNext() {
            return mIndex > 0;
        }

        @Override
        public Item next() {
            return items[--mIndex];
        }
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push("i：" + i);
        }
        for (String s : stack) {
            System.out.println(s);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
        for (int i = 0; i < 5; i++) {
            stack.pop();
        }
        System.out.println("isEmpty -> " + stack.isEmpty());
    }
}
