package com.restful.spring.boot.restful_spring_boot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "books")
@ApiModel(description = "Semua Detail tentang Buku.")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
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

    @Column(nullable = false , updatable = false)
    @Temporal(TemporalType.TIMESTAMP)

    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public void setTitleBook(String titleBook) {
        this.titleBook = titleBook;
    }

    public String getNamaDepanPengarang() {
        return namaDepanPengarang;
    }

    public void setNamaDepanPengarang(String namaDepanPengarang) {
        namaDepanPengarang = namaDepanPengarang;
    }

    public String getNamaBelakangPengarang() {
        return namaBelakangPengarang;
    }

    public void setNamaBelakangPengarang(String namaBelakangPengarang) {
        namaBelakangPengarang = namaBelakangPengarang;
    }

    public int getStatusPeminjaman() {
        return statusPeminjaman;
    }

    public void setStatusPeminjaman(int statusPeminjaman) {
        this.statusPeminjaman = statusPeminjaman;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
