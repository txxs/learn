package com.zookeeper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:jianghuimin
 * @Date: 2017/6/7
 * @Time:15:04
 */
public class TimeTest {

    public static void main(String args[]){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));
        for(int i=0;i<100000;i++){
            for(int j=0;j<100000;j++){
                int b=i+j;
                int b1=i+j;
                int b2=i+j;
                int b3=i+j;
                int b4=i+j;
                int b5=i+j;
                int b6=i+j;
                int b7=i+j;
                int b8=i+j;
                int b9=i+j;
                int b10=i+j;

            }
        }

        Calendar cal1 = Calendar.getInstance();
        Date date1 = cal1.getTime();
        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1));
    }
}
