package com.example.caculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;

public class MainActivity extends AppCompatActivity {
    Button[] buttons=new Button[10];
    TextView[] textView=new TextView[3];
    private String string="";
    Button clear,back,point;
    Button multiply,divide,add,minus,qiuyu,equal;
    Button left,right;
    Transformation tran=new Transformation();
    InfixtoValue infixtoValue=new InfixtoValue();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView[0]=findViewById(R.id.text_0);//结果
        textView[1]=findViewById(R.id.text_1);//中缀表达式
        textView[2]=findViewById(R.id.text_2);//后缀表达式
        InitButtion_Num();
        InitButton();

    }
    //把管理数字的button给初始化一下
    public void  InitButtion_Num(){
        buttons[0]=findViewById(R.id.bt_0);
        buttons[1]=findViewById(R.id.bt_1);
        buttons[2]=findViewById(R.id.bt_2);
        buttons[3]=findViewById(R.id.bt_3);
        buttons[4]=findViewById(R.id.bt_4);
        buttons[5]=findViewById(R.id.bt_5);
        buttons[6]=findViewById(R.id.bt_6);
        buttons[7]=findViewById(R.id.bt_7);
        buttons[8]=findViewById(R.id.bt_8);
        buttons[9]=findViewById(R.id.bt_9);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               string=textView[0].getText().toString()+'0';//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+1;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+2;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+3;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+4;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+5;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+6;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+7;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+8;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
        buttons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+9;//这个0直接以字符的形式存了把，就是把原来的东西先提取出来
                textView[0].setText(string);
            }
        });
    }
    public void  InitButton(){
        //先把特殊案件来一波
        //清零的按键
        clear=findViewById(R.id.tv_init);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView[0].setText("");
            }
        });
        back=findViewById(R.id.bt_delete);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length=textView[0].getText().toString().length();
                int i=0;
                String s="";
                s+=textView[0].getText().toString().substring(i,length-1);
                textView[0].setText(s);
            }
        });
        multiply=findViewById(R.id.bt_multiply);
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+'*';
                textView[0].setText(string);
            }
        });
        divide=findViewById(R.id.bt_divide);
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+'/';
                textView[0].setText(string);
            }
        });
        add=findViewById(R.id.bt_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+"+";
                textView[0].setText(string);
            }
        });
        minus=findViewById(R.id.bt_minus);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+"-";
                textView[0].setText(string);
            }
        });
        /*qiuyu=findViewById(R.id.qiuyu);
        qiuyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+'%';
                textView[0].setText(string);
            }
        });*/
        equal=findViewById(R.id.bt_equal);
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    string = textView[0].getText().toString();
                    Log.d("MainActivity", "onClick: 这是进去的字符串" + string);
                    textView[1].setText(string);//显示的是中缀表达式
                    tran.InfixtoSuffix(string);
                    tran.SuffixtoValue();
                    String s = "";
                    s += "=" + tran.s4.pop();
                    String s1 = "";
                    tran.InfixtoSuffix(string);
                    while (!tran.s2.isEmpty()) {
                        s1 += tran.s2.remove() + " ";
                   /* double ex=Double.parseDouble(tran.s2.getFront());
                    if(Math.abs(ex-(int)ex)>1e-5){
                        s+=tran.s2.remove();
                    }else {
                        double ex1=Double.parseDouble(tran.s2.remove());
                        s+=String.valueOf((int)ex1);
                    }*/
                    }
                    s1 += s;
                    textView[2].setText(s1);//显示的后缀表达式
                    infixtoValue.toValue(string);//先要把它算出来之后才能进行下一步
                    double result = infixtoValue.opd.pop();
                    if (Math.abs(result - (int) result) > 1e-5) {
                        string += "" + result;//等会中缀表达式后缀表达式会用到
                        textView[0].setText(String.valueOf(result));//显示结果
                    } else {
                        textView[0].setText(String.valueOf((int) result));
                    }
                }catch (ArithmeticException e){
                    Toast.makeText(MainActivity.this,"除数不能为0",Toast.LENGTH_SHORT).show();
                }




            }
        });
        left=findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+"(";
                textView[0].setText(string);

            }
        });
        right=findViewById(R.id.right);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+")";
                textView[0].setText(string);
            }
        });
        point=findViewById(R.id.bt_point);
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string=textView[0].getText().toString()+".";
                textView[0].setText(string);
            }
        });




    }

}
