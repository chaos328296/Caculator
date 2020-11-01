package com.example.caculator;

import android.util.Log;

import java.util.concurrent.BlockingDeque;

import static android.content.ContentValues.TAG;

public class InfixtoValue {//中缀表达式求值
    Stack<String> opt;//操作符栈
    Stack<Double> opd;//操作数栈
    Stack<Character> T;//临时栈
    public InfixtoValue(){
        opt=new Stack<>(String.class,50);
        opd=new Stack<>(Double.class,50);
        T=new Stack<>(Character.class,50);
    }

    public  void toValue(String s1) {//直接是中缀表达式 如果有空格要怎么办

        s1 += "#";
        opt.push("#");//用来做字符串输入完毕的操作
        Log.d(TAG, "toValue:我push了一个#进运算符栈");
        boolean flag1 = true;//"-"号在哪里含义不一样
        boolean flag3=false;
        boolean flag = false;//遇到小数的标志变量
        char data[] = s1.toCharArray();
        int n = 0;
        int a = 0;
        for (int i = 0; i < data.length; i++) {


            if (data[i] >= '0' && data[i] <= '9' || data[i] == '.' || data[i] == '-' && flag1) {
                flag1 = false;//进来过一次了此时“-”号为符号了
                if(data[i]!='.'){
                    T.push(data[i]);
                    Log.d(TAG, "toValue: 这是放进临时栈的第"+i+"个需要处理的数:"+data[i]);
                    n++;
                }
                Log.d(TAG, "toValue:n的值为"+n);
                if (data[i] == '.') {
                    flag = true;
                }
                if (flag&&data[i]!='.') {
                    a++;
                    //flag=false;//如果再没有遇到点的话就变成false
                }
                if(data[i]=='-'){
                    flag3=true;
                    Log.d(TAG, "toValue:这是我是负号的标志");
                }

            }else {

                while (!T.isEmpty()) {//当运算数的栈不为空时
                    double b = 0;
                    String s = "";
                    if (flag3) {
                        n--;
                        flag3=false;
                        Log.d(TAG, "toValue:为什么进去了");
                    }
                    Log.d(TAG, "toValue:n的值为"+n);
                    for (int j = 0 - a; j < n-a; j++) {
                        Log.d(TAG, "toValue:进来了么");
                        char ch1 = T.pop();
                        Log.d(TAG, "toValue:这是第" + "j个pop出来的：" + ch1);
                        b += (ch1 - '0') * Math.pow(10, j);
                    }
                    if (!T.isEmpty()) {
                        T.pop();//形式上把这个负号给它pop出来
                        s += '-';
                        s += String.valueOf(b);
                    } else {
                        s += String.valueOf(b);
                    }

                    opd.push(Double.parseDouble(s));//将这个数强转为double型
                    Log.d(TAG, "toValue:从临时栈里处理完后此时放进操作数栈的数为 " + s);
                }


                n=0;a=0;
                flag=false;
                if (data[i] == ')') {//如果遇到右括号就无敌了还管啥，右括号时不进栈的把
                    while (!opt.isEmpty() && opt.peek().charAt(0) !='(') {
                        double a1 = opd.pop();
                        double a2 = opd.pop();
                        double c=Caculation(a2, opt.pop().charAt(0), a1);
                        opd.push(c);//把运算结果打回进栈
                        Log.d(TAG, "toValue:你都等于（怎么会进来呐");
                        Log.d(TAG, "toValue:这是遇到右括号时放进操作数栈的运算数（经过计算的了）" +c);
                    }
                    opt.pop();//把左括号消灭掉

                }
                if (data[i] == '#') {//如果遇到右括号就无敌了还管啥，右括号时不进栈的把
                    while (!opt.isEmpty() && opt.peek() != String.valueOf("#")) {
                        double a1 = opd.pop();
                        double a2 = opd.pop();
                        double c=Caculation(a2, opt.pop().charAt(0), a1);
                        opd.push(c);//把运算结果打回进栈
                        Log.d(TAG, "toValue:这是遇到右#时放进操作数栈的运算数（经过计算的了）" +c);
                    }
                    opt.pop();//把左#消灭掉

                }
                //以上两个if是不能合并的

                if (!opt.isEmpty()) {//当运算符的栈不为空时
                    while (compare(opt.peek().charAt(0), data[i])) {//访问栈顶元素和即将入栈的进行比较
                        double a1 = opd.pop();
                        double a2 = opd.pop();
                        Log.d(TAG, "toValue:a1是"+a1);
                        Log.d(TAG, "toValue:a2是"+a2);
                        Log.d(TAG, "toValue:符号栈中栈顶的符号是"+opt.peek().charAt(0));
                        double c=Caculation(a2, opt.pop().charAt(0), a1);
                        opd.push(c);
                        Log.d(TAG, "toValue:经过计算的了（除#和）的其它情况）" + c);
                    }
                }//应该是比完运算符的优先级才将一个新的压入栈中
                if (data[i] != ')' && data[i] != '#') {
                   if(data[i]=='('){
                       flag1=true;
                   }
                   if (!flag1||data[i]=='(') {
                        opt.push(String.valueOf(data[i]));//如果不是负号的话就将它压入栈中
                        Log.d(TAG, "toValue:将操作符压入了操作符栈为" + data[i]);
                   }

                }
            }





        }
    }
    public double Caculation(double b,char op,double a){
        switch (op){
            case '+':
                return a+b;
            case '-':
                return b-a;
            case '*':
                return a*b;
            case '/':
                try {
                    return b/a;
                }catch (ArithmeticException e){
                    return 0;
                    // Toast.makeText(Transformation.class,"你不可以除零",Toast.LENGTH_SHORT).show();
                }
            default:
                return 0;

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
}
