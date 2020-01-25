package com.wjl.list.linkelist;

public class MyLinkedList<E> {

    //首尾节点和大小
    private Node frist;
    private Node last;
    private int size;

    /**
     * 添加方法我们要知道在第一次添加的时候
     * 是没有首和尾的
     * @param e
     */
    public void add(E e){
        Node<E> node = new Node<E>();
        //第一次添加的情况
        if (size==0) {
            fristAddNode(e, node);
        }else {
            /**
             * 其余情况下的添加
             * 实际上就是在尾部添加一个节点，
             * 我们只需要将尾部的下一个节点指向新的节点
             * 将新的节点为上一个指向尾部节点，
             * 将尾部节点替换成新节点即可
             */
            addNewNode(e, node);
        }

    }

    /**
     * 获取某个节点上的值
     * 实际上就是从某一点开始一直调用他的next即可
     * @param index
     * @return
     */
    public E get(int index){

        return getNode(index).item;

    }

    /**
     * 删除掉某一节点的值
     * 实际上就是将这个节点的引用置为空
     * 将他的上一个节点的下一个节点引用为他的下一个节点
     * 将他的下一个节点的上一个节点引用为他的上一个节点
     * 并且我们要将这个节点的所有引用置为空
     * 防止资源泄漏
     * @param index
     * @return
     */
    public E remove(int index){
        return removeNode(index);
    }

    private E removeNode(int index) {
        Node<E> needRemove = getNode(index);
        Node<E> prev = needRemove.prev;
        Node<E> next = needRemove.next;
        //这里我们要注意如果这是首节点或者尾节点的情况
        if (prev == null) {
            removeFristNode();
            if (next != null) {
                frist = next;
                next.prev = null;
            }
        }
        if (next == null){
            removeLastNode();
            if (prev != null) {
                last = prev;
                prev.next = null;
            }
        }
        size--;
        return needRemove.item;
    }

    /**
     * 指定下标添加
     * 我们需要获取该下标的原有值
     * 并且获取该下标的上一个值
     * 只需要将该下标的上一个改成添加节点
     * 将该节点的上一个节点的下一个节点指向添加节点即可
     * @param index
     * @param e
     */
    public void add(int index,E e){
        Node<E> needAddNode = new Node<E>();
        needAddNode.item = e;
        Node<E> node = getNode(index);
        Node<E> prev = node.prev;
        //这里我们开始替换下标
        //当然我们也要判断是否是添加的首节点
        if (prev == null){
            addFristNode(needAddNode);
            size++;
            return;
        }
        prev.next = needAddNode;
        needAddNode.prev = prev;
        needAddNode.next = node;
        node.prev = needAddNode;
        size++;
    }

    private void addFristNode(Node<E> node) {
        frist.prev = node;
        node.next = frist;
        frist = node;
    }

    private void removeLastNode() {
        this.last = null;
    }

    private void removeFristNode() {
        this.frist = null;
    }

    private Node<E> getNode(int index) {
        //这里是下标越界检查
        rangeCheckForAdd(index);
        //这里使用二分查找
        if (index < (size >> 1)){
            Node<E> node = frist;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }else {
            Node<E> node = last;
            for (int i = size - 1; i > index ; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    private void rangeCheckForAdd(int index) {

    }

    private void addNewNode(E e, Node<E> node) {
        node.item = e;
        this.last.next = node;
        node.prev = last;
        last = node;
        size++;
    }

    private void fristAddNode(E e,Node<E> node) {
        node.next = null;
        node.prev = null;
        node.item = e;
        this.frist = node;
        this.last = node;
        size++;
    }


    //节点内部类
    private class Node<E>{
        E item;
        Node<E> prev;
        Node<E> next;

        /**
         * 这里是JDK的原生写法
         * @param prev
         * @param element
         * @param next
         */
        /*Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }*/
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Node<E> node = getNode(i);
            sb.append(node.item.toString()).append(" ");
        }
        
        return "MyLinkedList{" +
                "frist=" + frist.toString() +
                ", last=" + last.toString()+
                ",item="+ sb.toString() +
                ", size=" + size +
                '}';
    }
}
