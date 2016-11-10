package com.drawable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.util.Calendar;

public class Calculator_Activity extends Activity {
    public Double div(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button btn_Point;
    Button btn_Clear;
    Button btn_JJ1;
    Button btn_percent;
    Button btn_DEL;
    Button btn_Div;
    Button btn_Mul;
    Button btn_Sub;
    Button btn_Add;
    Button btn_equal;
    EditText et_Input1;
    private final String Tag = "myTag";

    private Calendar calendar; //获取当前时期（到秒）
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator); // 启动activity_main界面

         /*
           年月日——秒
         */
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        setTitle("计算器      " + "          " + year + "-" + month + "-" + day + " " + hour + ":" + minute);

        btn_0 = (Button) findViewById(R.id.bt_0);
        btn_1 = (Button) findViewById(R.id.bt_1);
        btn_2 = (Button) findViewById(R.id.bt_2);
        btn_3 = (Button) findViewById(R.id.bt_3);
        btn_4 = (Button) findViewById(R.id.bt_4);
        btn_5 = (Button) findViewById(R.id.bt_5);
        btn_6 = (Button) findViewById(R.id.bt_6);
        btn_7 = (Button) findViewById(R.id.bt_7);
        btn_8 = (Button) findViewById(R.id.bt_8);
        btn_9 = (Button) findViewById(R.id.bt_9);
        btn_Point = (Button) findViewById(R.id.bt_point);
        btn_Clear = (Button) findViewById(R.id.bt_clear);
        btn_JJ1 = (Button) findViewById(R.id.bt_jj1);
        btn_percent = (Button) findViewById(R.id.bt_percent);
        btn_DEL = (Button) findViewById(R.id.bt_del);
        btn_Div = (Button) findViewById(R.id.bt_div);
        btn_Mul = (Button) findViewById(R.id.bt_mul);
        btn_Sub = (Button) findViewById(R.id.bt_sub);
        btn_Add = (Button) findViewById(R.id.bt_add);
        btn_equal = (Button) findViewById(R.id.bt_Equal);
        et_Input1 = (EditText) findViewById(R.id.et_input);

        //实例化一个事件处理对象
        MyOnClickListener myOnClickListener = new MyOnClickListener();

        btn_0.setOnClickListener(myOnClickListener);
        btn_1.setOnClickListener(myOnClickListener);
        btn_2.setOnClickListener(myOnClickListener);
        btn_3.setOnClickListener(myOnClickListener);
        btn_4.setOnClickListener(myOnClickListener);
        btn_5.setOnClickListener(myOnClickListener);
        btn_6.setOnClickListener(myOnClickListener);
        btn_7.setOnClickListener(myOnClickListener);
        btn_8.setOnClickListener(myOnClickListener);
        btn_9.setOnClickListener(myOnClickListener);
        btn_Point.setOnClickListener(myOnClickListener);
        btn_Clear.setOnClickListener(myOnClickListener);
        btn_JJ1.setOnClickListener(myOnClickListener);
        btn_percent.setOnClickListener(myOnClickListener);
        btn_DEL.setOnClickListener(myOnClickListener);
        btn_Div.setOnClickListener(myOnClickListener);
        btn_Mul.setOnClickListener(myOnClickListener);
        btn_Sub.setOnClickListener(myOnClickListener);
        btn_Add.setOnClickListener(myOnClickListener);
        btn_equal.setOnClickListener(myOnClickListener);
    }

    // 点击处理类
    private class MyOnClickListener implements View.OnClickListener {
        private String strOp1 = "0";//第一个操作数
        private String strOp2 = "";//第二个操作数
        private String operation = "";//当前使用的 运算符
        private String strResult = et_Input1.getText().toString(); //当前显示的结果
        private int lastInputType = 0;// 最后一次输入的数字或运算符，0=数字， 1=运算符
        private int equalFlag = 0;

        //private String secOp1 = "";
        private String secOp2 = "";


        /*
          处理数字输入
         */
        private void numInput(int num) {
            Log.i(Tag, "当前点击控件为数字" + num);

            if (equalFlag == 1) {
                strResult = "0";
                strOp1 = strOp2 = secOp2 = "";
                operation = "";
                et_Input1.setText(strResult);
                equalFlag = 0;
            }
            if (strResult.equals("0")) { //如果输入框只有一个0，直接替换
                strResult = "" + num;
            } else if (lastInputType == 0) { //否则如果输入框有其他数字，直接追加
                strResult = strResult + num;
                //strOp1 = strResult;  //设在这里会在 numInput()中循环
            } else if (lastInputType == 1) {  //如果上次输入的是字符，则清除当前显示的内容，如1+2 ，2为当前输入
                strResult = "" + num;
            }
            et_Input1.setText(strResult);  //更新显示内容
            lastInputType = 0; // 设置上次输入的内容为数字
        }

        /*
          点 号输入
         */
        private void dotInput() {
            if (lastInputType==1) {
                if (strResult.indexOf(".") == -1) { //如果当前数字已经有 点号 ，则不能再加 点号
                    strResult = strResult + ".";
                    strOp1=strResult;
                    operation = "";
                    strOp2 = "";
                    secOp2 = "";
                }
            }else{
                if (strResult.indexOf(".") == -1) { //如果当前数字已经有 点号 ，则不能再加 点号
                    strResult = strResult + ".";
                }
            }
            et_Input1.setText(strResult);
            equalFlag = 0;
            lastInputType = 0;
        }

        /*
          处理+/-
         */
        private void jjInput() {
            double m = Double.valueOf(strResult);
            if (m >= 0) {
                if (m % 1.0 == 0) {
                    strResult = String.valueOf((long) (-m));
                    if(equalFlag==1){
                        strOp1=strResult;
                        operation = "";
                        strOp2 = "";
                        secOp2 = "";
                    }
                } else {
                    strResult = "-" + m;
                    if(equalFlag==1){
                        strOp1=strResult;
                        operation = "";
                        strOp2 = "";
                        secOp2 = "";
                    }
                }
            } else {
                if (m % 1.0 == 0) {
                    strResult = String.valueOf((long) (-m));
                    if(equalFlag==1){
                        strOp1=strResult;
                        operation = "";
                        strOp2 = "";
                        secOp2 = "";
                    }
                } else {
                    strResult = String.valueOf(-m);
                    if(equalFlag==1){
                        strOp1=strResult;
                        operation = "";
                        strOp2 = "";
                        secOp2 = "";
                    }
                }
            }
            /*if(equalFlag==1){
                strResult=String.valueOf(Double.parseDouble(strResult)*(-1));
                strOp1=strResult;
            }else {
                strResult=String.valueOf(Double.parseDouble(strResult)*(-1));
            }*/

            lastInputType = 0;
            // strResult = String.valueOf(Double.parseDouble(strResult)/100);
            et_Input1.setText(strResult);
        }

        /*
          处理％
         */
        private void Percent() {
            if(equalFlag ==0){
                //strResult = String.valueOf(Double.parseDouble(strResult) / 100);
                if(lastInputType==0 && operation.isEmpty()){
                    strResult = String.valueOf(div((Double.parseDouble(strResult)),100.00,10));
                    strOp1 = strResult;
                    lastInputType = 0;
                }
                if (lastInputType == 1 && !strOp1.isEmpty() && equalFlag == 0) { //处理 1+=这种情况，这个=是当前输入
                    double op1 = Double.parseDouble(strOp1);
                    secOp2 = strOp1;
                    //double op2 = Double.parseDouble(strOp1);
                    if (operation.equals("×")) {
                        strResult = String.valueOf((op1 * op1)/100);
                    } else if (operation.equals("÷")) {
                        strResult = String.valueOf((op1 / op1)/100);
                    } else if (operation.equals("+")) {
                        strResult = String.valueOf((op1 + op1)/100);
                    } else if (operation.equals("-")) {
                        strResult = String.valueOf((op1 - op1)/100);
                    }
                    et_Input1.setText(strResult);
                    strOp1 = strResult;
                }if (!strOp1.isEmpty() && lastInputType == 0) { //处理正常的1+2=这种情况， = 是当前输入
                    strOp2 = strResult;
                    secOp2 = strOp2;
                    double op1 = Double.parseDouble(strOp1);
                    double op2 = Double.parseDouble(strOp2);
                    if (operation.equals("×")) {
                        strResult = String.valueOf(div((op1 * op2),100.00,10));
                        strOp1 = strResult;
                    } else if (operation.equals("÷")) {
                        if (op2 == 0) {
                            strResult = "0.0";
                            strOp1 = "0";
                        } else {
                            strResult = String.valueOf(div((op1 / op2),100.00,10));
                            strOp1 = strResult;
                        }
                    } else if (operation.equals("-")) {
                        strResult = String.valueOf(div((op1 - op2),100.00,10));
                        strOp1 = strResult;
                    } else if (operation.equals("+")) {
                        strResult = String.valueOf(div((op1 + op2),100.00,10));
                        strOp1 = strResult;
                    }
                }
                //equalFlag = 1;
                lastInputType = 0;
                operation="";
            }else {
                strResult = String.valueOf(div((Double.parseDouble(strResult)),100.00,10));
                //strResult = String.valueOf(Double.parseDouble(strResult) / 100);
                strOp1 = strResult;
                //operation = "";
                strOp2 = "";
                secOp2 = "";
            }
            et_Input1.setText(strResult);
        }

        /*
          处理清除 clear
         */
        private void clearInput() {
            strResult = "0";
            lastInputType = 0;
            operation = "";
            equalFlag = 0;
            strOp1 = strOp2 = secOp2 = "";
            et_Input1.setText(strResult);
        }

        private void delInput() {
            strResult = strResult.substring(0, strResult.length() - 1);
            if (strResult.isEmpty()) {
                strResult = "0";
            }
            if(equalFlag==1){
                strOp1 = strResult;
                strOp2 = "";
                lastInputType = 1;
                equalFlag=1;
            }
            et_Input1.setText(strResult);
        }

        /*
          处理运算符 opt 输入的运算符
         */
        private void operationInput(String opt) {
            if (lastInputType == 1) {  //处理连续输入运算符，将当前使用的运算符替换
                operation = opt;
                et_Input1.setText(operation);
                lastInputType = 1;
                equalFlag = 0;
                return;
            }
            if (operation.isEmpty()) {
                operation = opt; //更新运算符
                et_Input1.setText(operation);
                strOp1 = strResult; //第一个操作数
                equalFlag = 0;
            } else if (!strOp1.isEmpty() && equalFlag == 0) { //例如 1+2+ ，最后的 + 是当前输入
                strOp2 = strResult; //第二个操作数
                double op1 = Double.parseDouble(strOp1);
                double op2 = Double.parseDouble(strOp2);

                if (operation.equals("×")) {
                    /*
                       if((op1*op2)%1.0==0){
                        strResult=String.valueOf((long)(op1*op2));
                    }
                        解决 小数点为零时 取整数
                     */
                    if ((op1 * op2) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 * op2));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op1));
                        BigDecimal b2 = new BigDecimal(Double.toString(op2));
                        double m = b1.multiply(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    //et_Input1.setText(strResult);
                    strOp1 = strResult;
                    //strOp2 = "";
                    operation = opt;
                } else if (operation.equals("÷")) {
                    if (op2 == 0) {
                        strResult = "∞";
                        //et_Input1.setText(strResult);
                        strOp1 = "0";
                    } else if ((op1 / op2) % 1.0 == 0) {
                        strResult = div(op1, op2, 10).toString();
                        strResult = String.valueOf((long) (op1 / op2));
                        strOp1 = strResult;
                    } else {
                        //BigDecimal a=new BigDecimal(op1);
                        //BigDecimal b=new BigDecimal(op2);
                        strResult = div(op1, op2, 10).toString();
                        strOp1 = strResult;
                    }
                    //et_Input1.setText(strResult);
                    //strOp2 = "";
                    operation = opt;
                } else if (operation.equals("+")) {
                    if ((op1 + op2) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 + op2));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op1));
                        BigDecimal b2 = new BigDecimal(Double.toString(op2));
                        double m = b1.add(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    // et_Input1.setText(strResult);
                    strOp1 = strResult;
                    //strOp2 = "";
                    operation = opt;
                } else if (operation.equals("-")) {
                    if ((op1 - op2) % 1.0 == 0) {
                        //strResult = strResult.Math.abs((op1-op2) - 1) < 0.000000001;
                        strResult = String.valueOf((long) (op1 - op2));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op1));
                        BigDecimal b2 = new BigDecimal(Double.toString(op2));
                        double m = b1.subtract(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    //et_Input1.setText(strResult);
                    strOp1 = strResult;
                    //strOp2 = "";
                    operation = opt;
                }
                et_Input1.setText(strResult);
                strOp2 = "";
            }
            lastInputType = 1;
        }


        /*
          处理 等号
         */
        private void equalInput() {
            if (operation.isEmpty()) //当前还没有 运算符，则直接返回
            {
                return;
            }
            if (lastInputType == 1 && !strOp1.isEmpty() && equalFlag == 0) { //处理 1+=这种情况，这个=是当前输入
                double op1 = Double.parseDouble(strOp1);
                secOp2 = strOp1;
                //double op2 = Double.parseDouble(strOp1);
                if (operation.equals("×")) {
                    if ((op1 * 1) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 * op1));
                    } else {
                        strResult = String.valueOf(op1 * op1);
                    }
                    //secOp2 = strOp1;
                    et_Input1.setText(strResult);
                    //strResult = "";
                } else if (operation.equals("÷")) {
                    if ((op1 / 1) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 / op1));
                    } else {
                        strResult = String.valueOf(op1 / op1);
                    }
                    //secOp2 = strOp1;
                    et_Input1.setText(strResult);
                    //strResult = "";
                } else if (operation.equals("+")) {
                    if ((op1 + 1) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 + op1));
                    } else {
                        strResult = String.valueOf(op1 + op1);
                    }
                    //secOp2 = strOp1;
                    et_Input1.setText(strResult);
                    //strResult = "";
                } else if (operation.equals("-")) {
                    if ((op1 - 1) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 - op1));
                    } else {
                        strResult = String.valueOf(op1 - op1);
                    }
                    //secOp2 = "-"+strOp1;
                    et_Input1.setText(strResult);
                    //strResult = "";
                }
                strOp1 = strResult;
            } else if (!strOp1.isEmpty() && lastInputType == 0) { //处理正常的1+2=这种情况， = 是当前输入
                strOp2 = strResult;
                secOp2 = strOp2;
                double op1 = Double.parseDouble(strOp1);
                double op2 = Double.parseDouble(strOp2);
                if (operation.equals("×")) {
                    if ((op1 * op2) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 * op2));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op1));
                        BigDecimal b2 = new BigDecimal(Double.toString(op2));
                        double m = b1.multiply(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    strOp1 = strResult;
                } else if (operation.equals("÷")) {
                    if (op2 == 0) {
                        strResult = "∞";
                        strOp1 = "0";
                    } else if ((op1 / op2) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 / op2));
                        //et_Input1.setText(strResult);
                        strOp1 = strResult;
                    } else {
                        strResult = div(op1, op2, 10).toString();
                        strOp1 = strResult;
                    }
                } else if (operation.equals("-")) {
                    if ((op1 - op2) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 - op2));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op1));
                        BigDecimal b2 = new BigDecimal(Double.toString(op2));
                        //double m = b1.subtract(b2).doubleValue();
                        double m = b1.subtract(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    strOp1 = strResult;
                } else if (operation.equals("+")) {
                    if ((op1 + op2) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op1 + op2));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op1));
                        BigDecimal b2 = new BigDecimal(Double.toString(op2));
                        double m = b1.add(b2).doubleValue();
                        strResult = String.valueOf(m);
                        //strResult = String.valueOf(op1+op2);
                    }
                    strOp1 = strResult;
                }
                // et_Input1.setText(strResult);
                //strOp2 ="";
                //operation ="";
            }

            if (equalFlag == 1){
                double op3 = Double.parseDouble(strOp1);
                double op4 = Double.parseDouble(secOp2);

                if (operation.equals("×")) {
                    if ((op3 * op4) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op3 * op4));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op3));
                        BigDecimal b2 = new BigDecimal(Double.toString(op4));
                        double m = b1.multiply(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    strOp1 = strResult;
                } else if (operation.equals("÷")) {
                    if (op4 == 0) {
                        strResult = "∞";
                        strOp1 = "0";
                    } else if ((op3 / op4) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op3 / op4));
                        //et_Input1.setText(strResult);
                        strOp1 = strResult;
                    } else {
                        strResult = div(op3, op4, 10).toString();
                        strOp1 = strResult;
                    }
                } else if (operation.equals("-")) {
                    if ((op3 - op4) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op3 - op4));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op3));
                        BigDecimal b2 = new BigDecimal(Double.toString(op4));
                        //double m = b1.subtract(b2).doubleValue();
                        double m = b1.subtract(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    strOp1 = strResult;
                } else if (operation.equals("+")) {
                    if ((op3 + op4) % 1.0 == 0) {
                        strResult = String.valueOf((long) (op3 + op4));
                    } else {
                        BigDecimal b1 = new BigDecimal(Double.toString(op3));
                        BigDecimal b2 = new BigDecimal(Double.toString(op4));
                        double m = b1.add(b2).doubleValue();
                        strResult = String.valueOf(m);
                    }
                    strOp1 = strResult;
                }
            }
            et_Input1.setText(strResult);
            strOp2 = "";
            lastInputType = 1;
            equalFlag = 1;
        }

        @Override
        // 当被点击时 进入该方法
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.bt_0:
                    numInput(0);
                    break;
                case R.id.bt_1:
                    numInput(1);
                    break;
                case R.id.bt_2:
                    numInput(2);
                    break;
                case R.id.bt_3:
                    numInput(3);
                    break;
                case R.id.bt_4:
                    numInput(4);
                    break;
                case R.id.bt_5:
                    numInput(5);
                    break;
                case R.id.bt_6:
                    numInput(6);
                    break;
                case R.id.bt_7:
                    numInput(7);
                    break;
                case R.id.bt_8:
                    numInput(8);
                    break;
                case R.id.bt_9:
                    numInput(9);
                    break;
                case R.id.bt_point:
                    dotInput();
                    break;
                case R.id.bt_add:
                    operationInput("+");
                    break;
                case R.id.bt_sub:
                    operationInput("-");
                    break;
                case R.id.bt_mul:
                    operationInput("×");
                    break;
                case R.id.bt_div:
                    operationInput("÷");
                    break;
                case R.id.bt_jj1:
                    jjInput();
                    break;
                case R.id.bt_percent:
                    Percent();
                    break;
                case R.id.bt_Equal:
                    equalInput();
                    break;
                case R.id.bt_clear:
                    clearInput();
                    break;
                case R.id.bt_del:
                    delInput();
                    break;
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}

