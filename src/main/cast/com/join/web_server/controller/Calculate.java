package com.join.web_server.controller;


//计算时长
public class Calculate {
    public Long calc(Long t1, Long t2){
        if(t2 < t1){
            System.out.println("error");
            return 0L;
        }
        return (t2 - t1)/1000;
    }
}
