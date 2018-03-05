package com.srpgroup.iot.wx.bean;

public class TestBody1 {
        private int a;
        private String b;

        @Override
        public String toString() {
            return "TestBody1{" +
                    "a=" + a +
                    ", b='" + b + '\'' +
                    '}';
        }


        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }