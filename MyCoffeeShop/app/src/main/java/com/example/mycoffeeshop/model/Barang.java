package com.example.mycoffeeshop.model;

public class Barang {
    private int id;
    private String nama_barang;
    private int harga_barang;
    private int quantity;

    public void setId(int id) {
        this.id = id;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public void setHarga_barang(int harga_barang) {
        this.harga_barang = harga_barang;
    }

    public int getId() {
        return id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public int getHarga_barang() {
        return harga_barang;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
