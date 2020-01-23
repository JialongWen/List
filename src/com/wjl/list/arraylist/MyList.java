package com.wjl.list.arraylist;

public interface MyList<E> {
    //添加方法无返回值
    void add(E e);
    //根据下标获取到一个值
    E get(int index);
    //删除掉指定下标的值并且返回这个值
    E remove(int index);
    //在指定下标添加一个值
    void add(int index,E e);
    //清空所有的值
    void clear();
    //获取列表的大小
    int getSize();
}
