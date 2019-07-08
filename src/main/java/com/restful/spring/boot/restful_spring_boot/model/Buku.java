package com.restful.spring.boot.restful_spring_boot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "books")
@ApiModel(description = "Semua Detail tentang Buku.")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Buku {

    @ApiModelProperty(notes = "Informasi Id")
    private Long id;

    @ApiModelProperty(notes = "Infomasi JuduL Buku")
    private String titleBook;

    @ApiModelProperty(notes = "Informasi Nama Depan Pengarang")
    private String namaDepanPengarang;

    @ApiModelProperty(notes = "Informasi Nama Belakang Pengarang")
    private String namaBelakangPengarang;

    @ApiModelProperty(notes = "Informasi Status Peminjaman")
    private int statusPeminjaman;

    @ApiModelProperty(notes = "Informasi Nama Peminjam")
    private String namaPeminjam;

//    @Column(nullable = false , updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
//    private Date createdAt;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @LastModifiedDate
//    private Date updatedAt;

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title_book", nullable = false)
    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    @Column(name = "nama_depan", nullable = false)
    public String getNamaDepanPengarang() {
        return namaDepanPengarang;
    }

    public void setNamaDepanPengarang(String namaDepanPengarang) {
        this.namaDepanPengarang = namaDepanPengarang;
    }

    @Column(name = "nama_belakang", nullable = false)
    public String getNamaBelakangPengarang() {
        return namaBelakangPengarang;
    }

    public void setNamaBelakangPengarang(String namaBelakangPengarang) {
        this.namaBelakangPengarang = namaBelakangPengarang;
    }

    @Column(name = "status_peminjaman", nullable = false)
    public int getStatusPeminjaman() {
        return statusPeminjaman;
    }

    public void setStatusPeminjaman(int statusPeminjaman) {
        this.statusPeminjaman = statusPeminjaman;
    }

    @Column(name = "nama_peminjam", nullable = false)
    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    @Override
    public String toString() {
        return "Buku [id=" + id +  "title_book"+titleBook + ",nama_depan =" + namaDepanPengarang +
                ", nama_belakang=" + namaBelakangPengarang + ", status_peminjaman=" + statusPeminjaman
                +", nama_peminjam" +namaPeminjam+
                "]";
    }
}
