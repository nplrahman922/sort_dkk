package org.example.node;

public class linkedlist {

    public node first;

    public linkedlist() {
        this.first = null;
    }

    public void insert(int data) {

        node newNode = new node(data);

        if (this.first == null) {
            first = new node(data);

        } else {
            node temp = this.first;
            while (temp.getnext() != null) {
                temp = temp.getnext();
            }
            temp.setnext(newNode);
        }
    }

    public boolean remove(int data) {
        if (this.first == null) {
            return false;
        }

        if (this.first.getdata() == data) {
            this.first = this.first.getnext();
            return true;
        }

        node temp = this.first;
        node prev = null;

        while (temp.getnext() != null) {
            prev = temp;
            temp = temp.getnext();
        }

        if (prev == null) {
            return false;
        }

        prev.setnext(temp.getnext());
        return true;
    }

    public void display() {
        System.out.print("isi dari linkedlist : [");
        node temp = this.first;
        while (temp != null) {
            System.out.print(temp.getdata() + " ");
            temp = temp.getnext();
        }
        System.out.print("]");
    }
}
