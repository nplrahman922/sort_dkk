package org.example.node;

public class node {
    private int data;
    private node next;

    public node(int data) {
        this.data = data;
        this.next = null;
    }

    public void setdata(int data) {
        this.data = data;
    }

    public int getdata() {
        return this.data;
    }

    public void setnext(node next) {
        this.next = next;
    }

    public node getnext() {
        return this.next;
    }

}
