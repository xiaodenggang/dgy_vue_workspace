package dgy.hbdgy.entity;

import java.io.Serializable;
//
public class Result<T> implements Serializable {
    Result<T> rs = new Result<T>();


    int count ;

    public int getCount(){
        return  count;
    }

    public void  setCount(int count){
        this.count = count;
    }
}
