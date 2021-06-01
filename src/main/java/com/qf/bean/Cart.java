package com.qf.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cart implements Serializable {
    private int cid;
    private int pid;
    private int uid;
    private int num;
    private BigDecimal money;//小计,总价

    private Product product;//要知道商品数据,关联商品属性即可

    public Cart() {
    }

    public Cart(int cid, int pid, int uid, int num, BigDecimal money, Product product) {
        this.cid = cid;
        this.pid = pid;
        this.uid = uid;
        this.num = num;
        this.money = money;
        this.product = product;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getMoney() {//商品得到了,而购物数量又赋值了,等会取商品总价格就是获取cart对象的getMoney方法,去修改它的返回值即可,不用设置了
        //总价=商品价格*购物数量,而上面的product属性刚刚已经设置好了,可以直接拿过来用了
        BigDecimal price = product.getPrice();

        BigDecimal n = new BigDecimal(num);

        return price.multiply(n);
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", num=" + num +
                ", money=" + money +
                ", product=" + product +
                '}';
    }
}
