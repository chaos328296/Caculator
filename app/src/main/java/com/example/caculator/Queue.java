package com.example.caculator;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Queue <T>{
    private T[] list;
    private int front;//队头下标
    private int rear;//队尾下标
    private int count;//队列的元素
    private int capacity;//队列的容量
    public Queue(Class<T> clazz, int capacity) {
        list= (T[]) Array.newInstance(clazz, capacity);
        rear=0;
        front=0;
        count=0;
        this.capacity=capacity;
    }
    //入队操作
    public void insert(T item){
        if(count==capacity){
            Log.d(TAG, "insert: 队列已满无法插入了");
            return;//这个是结束的意思么
        }
        count++;
        list[rear]=item;
        rear=(rear+1)%capacity;
    }
    //出队操作
    public T remove(){
        if(count==0){
            Log.d(TAG, "remove:队列已经空了无法出队了");
            return null;
        }else{
            int temp=front;
            count--;
            front=(front+1)%capacity;
            return list[temp];

        }

    }
    //置空队列的操作
    public void clear(){
        front=0;
        rear=0;
        count=0;
    }
    //判断队列是否为空的问题
    public  boolean isEmpty(){
        return count==0;
    }
    public T getFront(){//返回此时队首元素
        return list[front];
    }


}
