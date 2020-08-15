package com.zinc.example.androidlab.draft;

/**
 * Author: zhao zhongxin
 * Create: 2019/12/20
 * Describe:
 */
public class OuterClass {

    public abstract static class InnerClass{
        private String name;

        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            printName();
            return this.name;
        }

        abstract void printName();

    }

    public static class StaticInnerClass extends InnerClass{
        private String print;

        public StaticInnerClass(String print){
            this.print = print;
        }

        @Override
        void printName() {
            System.out.println("zzx "+"printName: "+print);
        }
    }

}
