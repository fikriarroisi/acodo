package com.fikriarroisi;

/**
 *
 * @author fikriarroisi
 */
public class Task {
    
    String name;
    String max;
    int type;
    
    public Task(String name, String max){
        this.name = name;
        this.type = General.getType(max);
        if(type == 0){
            this.max = General.getSecond(max)+"";
        }else if(type == 1){
            this.max = General.getSecond(max)+"";
        }else if(type == 2){
            this.max = max;
        }
    }
    
}