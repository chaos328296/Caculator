package com.example.caculator;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Stack<T> {
    private T[] list;
    private int top;
    private int capacity;
    public Stack(Class<T> clazz, int capacity) {
        list= (T[])Array.newInstance(clazz, capacity);
        top=-1;
        this.capacity=capacity;
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public boolean isFull(){
        return top==capacity-1;
    }


    public void push(T item){
        /*if(isFull()){
            Log.d(TAG, "push:栈已满无法放进了");
        }else{
           list[++top]=item;
        }*/
        list[++top]=item;

    }
    public T pop(){
        if(isEmpty()){
            Log.d(TAG, "pop: 栈已空无法再有弹出操作");
            return  null;
        }else{
            return list[top--];
        }
    }
    public T peek(){
        if(isEmpty()){
            Log.d(TAG, "pop: 栈已空无法再有弹出操作");
            return null;
        }else{
            return list[top];

        }
    }

}
