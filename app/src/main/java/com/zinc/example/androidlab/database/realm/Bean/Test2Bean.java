package com.zinc.example.androidlab.database.realm.Bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * author： zhao zhongxin
 * date： 2019/4/14
 * description：
 */
public class Test2Bean extends RealmObject {
    @PrimaryKey
    String data;

    String test;

    String test1;

    String test2;

    String test3;

    String test4;

    String test5;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3;
    }

    public String getTest4() {
        return test4;
    }

    public void setTest4(String test4) {
        this.test4 = test4;
    }

    public String getTest5() {
        return test5;
    }

    public void setTest5(String test5) {
        this.test5 = test5;
    }
}
