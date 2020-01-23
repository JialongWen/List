package com.wjl.list.arraylist;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E>{
    //定义一些我们需要的参数和常量
    //默认数组长度位10
    private static final int DEFAULT_CAPACITY = 10;
    //存储ArrayList元素的数组,但是这里我们不做初始化
    //当我们添加第一个参数的时候我们会将他初始化为DEFAULT_CAPACITY大小,这是JDK中的做法
    //我在这里将在构造参数值将它初始化
    private E[] element;
    //List实际存储数据的数量
    private int size;

    //这个构造函数允许使用者自定义数组的大小
    public MyArrayList(int initialCapacity){
        if (initialCapacity > 0){
            element = (E[]) new Object[initialCapacity];
        }else if (initialCapacity == 0){
            element = (E[]) new Object[DEFAULT_CAPACITY];
        }else {
            //这里其实只剩下负数的情况，我们可以和源码一样抛出越界的异常，也可以做其他的处理
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    //这个构造函数是创建默认大小的构造器
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(E e) {
        //添加方法就是将用户传进来的值放入数组中
        //这里我们需要用到扩容的操作了
            expansion();
        element[size++] = e;
    }

    /**
      *
      *扩容操作
      * 创建一个新的数组将原有的数组数据放入到新的数组
      * 将element的指针指向新数组
      * 扩容一点五倍
      * @data 2018/3/17 10:45
      * @by wjl
    */
    private void expansion() {
        if (element.length == size) {
            int newElements = element.length + (element.length >> 1);
            element = Arrays.copyOf(element, newElements);
        }
        /**
         * E[] newElements = (E[])new Object[newCatacity];
         *         for (int i = 0; i < size; i++) {
         *             newElements[i] = elements[i];
         *         }
         *         elements = newElements;
         *         又或者可以这样
         *       System.arraycopy(elements,0,newElements,0,element.length);
         *       elements = newElements;
         */
    }

    /**
     * 直接获取到数组指定下标的值
     * @data 2018/3/17 10:53
     * @by wjl
     */
    @Override
    public E get(int index) {
        rangeCheckForAdd(index);
        return getElement(index);
    }

    //为什么会把这个简单的操作独立出来
    //主要是在内部需要获取是使用
    private E getElement(int index) {
        return element[index];
    }

    /**
     * 删除这里我们要注意如果我们删除了某一个位置的值
     * 那么我们要将这个值之后的所有值往前挪动一位
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        rangeCheckForAdd(index);
        E e = get(index);
        //实际上我们只需要将之后的值往前挪动一位即可同时将size--
        //注意要将数组的最后一位置为null以防止内存泄漏或者脏数据的情况
        for (int i = index; i < size ; i++) {
            element[index] = element[index+1];
        }
        element[size--] = null;
       return e;
    }

    /**
     * 这里我们需要先将index开始的所有的值都往后挪动一位
     * 之后再将我们要添加的值添加到index位置上
     * @data 2018/3/17 11:13
     * @by wjl
     */
    @Override
    public void add(int index, E e) {
        //下标越界检查
        rangeCheckForAdd(index);
        //扩容检查
            expansion();

        for (int i = size; i >= index; i--) {
            element[i+1] = element[i];
        }
        element[index] = e;
        size++;
    }

    /**
     * 将size置为0
     * 将element置为null
     * 等待gc回收之前的数组
     */
    @Override
    public void clear() {
        this.size = 0;
        this.element = null;
    }

    @Override
    public int getSize() {
        return size;
    }

    //这一段是直接copy的源码的校验是否越界
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i= 0; i<size;i++) {
            sb.append(element[i].toString()).append(" ");
        }
        return "MyArrayList{ size = "+ this.size + " element = "+ sb.toString() + "}";
    }
}
