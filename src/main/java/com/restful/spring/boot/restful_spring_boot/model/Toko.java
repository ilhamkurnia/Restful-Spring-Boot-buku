package com.restful.spring.boot.restful_spring_boot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "Ruko")
@ApiModel(description = "Semua Detail tentang Toko")
public class Toko {

    @ApiModelProperty(notes = "Semua Detail tentang Id Toko")
    private Long idToko;

    @ApiModelProperty(notes = "Semua Detail tentang Nama Toko")
    private String namaToko;

    @ApiModelProperty(notes = "Semua Detail tentang Alamat Toko")
    private String alamatToko;

    @ApiModelProperty(notes = "Semua Detai tentang Owner Toko")
    private String ownerToko;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_toko", nullable =false)
    public Long getIdToko() {
        return idToko;
    }

    public void setIdToko(Long idToko) {
        this.idToko = idToko;
    }

    @Column(name = "nama_toko", nullable = false)
    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    @Column(name = "alamat_toko", nullable = false)
    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
    }

    @Column(name = "owner_toko", nullable = false)
    public String getOwnerToko() {
        return ownerToko;
    }

    public void setOwnerToko(String ownerToko) {
        this.ownerToko = ownerToko;
    }

    @Override
    public String toString(){
        return "Toko[id_toko" +idToko+ "nama_toko" +namaToko+ "alamat_toko" +alamatToko+ "owner_toko" + ownerToko+"]";
    }
}
