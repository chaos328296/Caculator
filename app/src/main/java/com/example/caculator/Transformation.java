package com.example.caculator;

import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.BlockingDeque;

import static android.content.ContentValues.TAG;

public class Transformation {
    private Stack<String> s;//运算符栈
    private Stack<Character> s3;//操作数栈
    public Queue<String> s2;//输出的队列
    public Stack<Double> s4;//用来算后缀表达式的
    public Transformation(){
        s=new Stack<String>(String.class,50);
        s3=new Stack<>(Character.class,50);
        s2=new Queue<>(String.class,50);
        s4=new Stack<>(Double.class,50);
    }
    public void  InfixtoSuffix(String s1){//中缀转后缀
        s1+="#";
        s.push("#");//用来做字符串输入完毕的操作
        char data[]=s1.toCharArray();
        boolean flag = false;//遇到小数的标志变量
        boolean flag1=true;//"-"号在哪里含义不一样
        boolean flag3=false;
        int n = 0;
        int a = 0;
        for(int i=0;i<data.length;i++) {
            if (data[i] > '0' && data[i] < '9' || data[i] == '.' || data[i] == '-' && flag1) {
                flag1 = false;//进来过一次了此时“-”号为符号了
                if (data[i] != '.') {
                    s3.push(data[i]);
                    n++;
                    Log.d(TAG, "InfixtoSuffix:这是放进操作数栈的要进行处理的数 " + data[i]);
                }
                if (data[i] == '.') {
                    flag = true;
                }
                if (flag && data[i] != '.') {
                    a++;
                }
                if (data[i] == '-') {
                    flag3 = true;
                }

            } else {
                while (!s3.isEmpty()) {//当运算数的栈不为空时


                    double b = 0;
                    String s5 = "";
                    if (flag3) {
                        n--;
                    }
                    for (int j = 0 - a; j < n - a; j++) {
                        char ch1 = s3.pop();
                        b += (ch1 - '0') * Math.pow(10, j);
                    }
                    if (!s3.isEmpty()) {
                        s3.pop();//形式上把这个负号给它pop出来
                        s5 += '-';
                        s5 += String.valueOf(b);
                    } else {
                        s5 += String.valueOf(b);
                    }

                    s2.insert((String.valueOf(s5)));//将这个数强转为double型
                }

                n = 0;
                a = 0;
                flag = false;
                if (data[i] == ')') {//如果遇到右括号就无敌了还管啥，右括号时不进栈的把
                    while (!s.isEmpty() && s.peek().charAt(0) != '(')
                        s2.insert(s.pop());
                    s.pop();//把左括号消灭掉

                }
                if (data[i] == '#') {
                    while (!s.isEmpty() && s.peek().charAt(0) != '#')
                        s2.insert(s.pop());
                    s.pop();//把左#消灭掉
                }
                if (!s.isEmpty()) {
                    while (compare(s.peek().charAt(0), data[i])) {//访问栈顶元素和即将入栈的进行比较
                        s2.insert(s.pop());//把这个pop出来的元素给它放进队列里
                    }
                }
                if (data[i] != ')' && data[i] != '#') {
                    if (data[i] == '(') {
                        flag1 = true;
                    }
                    if (!flag1 || data[i] == '(') {
                        s.push(String.valueOf(data[i]));//如果不是负号的话就将它压入栈

                    }

                }
            }
        }


    }
    public boolean compare(char a1,char a2){//如果前一个比较数比后一个大的话那么pop出来
        if(a1=='#'){
            return  false;
        }
        else if(a1=='('||a2=='('){
            return false;
        }else if(a2=='+'||a2=='-'){
            return true;
        }else if(a1=='*'||a1=='/'){
            return true;
        }else{//如果左边的操作符号是#就是false
            return false;
        }


    }


    public  void SuffixtoValue(){//后缀表达式求值
        Log.d(TAG, "SuffixtoValue:我调用了表达式求值没有啊");


        while(!s2.isEmpty()) {
            if (s2.getFront().charAt(0) < '0' || s2.getFront().charAt(0) > '9') {
                if (!s4.isEmpty()){
                    double a=s4.pop();
                    double b=s4.pop();
                    double c=Caculation(b,s2.remove().charAt(0),a);
                    //Caculation(b,s2.remove().charAt(0),a);
                    s4.push(c);
                    Log.d(TAG, "SuffixtoValue:我压进去运算数的栈的数为"+c);


                }
            }else {
                s4.push(Double.parseDouble(s2.remove()));//无论是负数还是小数还是正常数都是这样
            }
        }


    }
    //运算符选择的函数
    public double Caculation(double b,char op,double a)throws ArithmeticException{
        switch (op){
            case '+':
                return a+b;
            case '-':
                return b-a;
            case '*':
                return a*b;
            case '/':
                return b/a;
                /*if(a!=0){
                    return b/a;
                }else{
                    throw new ArithmeticException();
                }*/
            default:
                return 0;

        }
    }


}
