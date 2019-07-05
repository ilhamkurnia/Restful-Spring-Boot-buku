package com.restful.spring.boot.restful_spring_boot.controller;

import com.restful.spring.boot.restful_spring_boot.exception.ResourceNotFoundException;
import com.restful.spring.boot.restful_spring_boot.model.Buku;
import com.restful.spring.boot.restful_spring_boot.repository.BukuRepository;
import io.swagger.annotations.*;
import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Buku")
@Api(value = "Buku Rest Api", description = "Menjalankan Rest Api Buku")
public class BukuController {

    @Autowired
    BukuRepository bukuRepository;

    @ApiOperation(value = "Melihat List Buku ", response = List.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="Sukses Menjalankan List"),
    @ApiResponse(code = 400, message = "Accesing forbiddeen"),
    @ApiResponse(code = 500, message = "tidak ditemukan")})

    @GetMapping("/buku")
    public List<Buku> getAll(){
        return bukuRepository.findAll();
    }

    @ApiOperation(value = "Mendapatkan List Semua Buku")
    @GetMapping("/buku/{id}")
    public ResponseEntity<Buku> getId(
            @ApiParam(value = "List Buku", required = true)
            @PathVariable(value = "id") Long Id) throws ResourceNotFoundException{
        Buku buku = bukuRepository.findById(Id)
                .orElseThrow(()-> new ResourceNotFoundException("Buku tidak ditemukan ::" + Id));
        return ResponseEntity.ok().body(buku);
    }


    @ApiOperation(value = "Menambahkan Buku ")
    @PostMapping("/Add")
    public Buku tambahbuku(
            @ApiParam(value = "Menambahkan List Buku",required = true)
            @Valid @RequestBody Buku buku){
        return bukuRepository.save(buku);
    }

    @ApiOperation(value = "Update Buku")
    @PutMapping("/Update/{id}")
    public ResponseEntity<Buku> updateBuku (
            @ApiParam(value = "Update List Buku", required = true)
            @PathVariable(value = "id")Long id, @Valid @RequestBody Buku detailbuku){
        Buku buku = bukuRepository.getOne(id);
        if (buku == null)
            return ResponseEntity.notFound().build();
        buku.setTitleBook(detailbuku.getTitleBook());
        buku.setNamaDepanPengarang(detailbuku.getNamaDepanPengarang());
        buku.setNamaBelakangPengarang(detailbuku.getNamaBelakangPengarang());
        buku.setNamaPeminjam(detailbuku.getNamaPeminjam());
        buku.setStatusPeminjaman(detailbuku.getStatusPeminjaman());
        Buku updatedBuku = bukuRepository.save(buku);
        return ResponseEntity.ok(updatedBuku);
    }

    @ApiOperation(value = "Hapus Buku")
    @DeleteMapping("/Delete/{id}")
    public String deleteBuku(
            @ApiParam(value = "Hapus List Buku", required = true)@PathVariable ( value="id") Long id){
        Buku buku =  bukuRepository.getOne(id);
        String result = "";
        if(buku == null){
            result  = "id" +id+ "Tidak Ditemukan";
            return result;
        }
        result = "id" +id+ "Berhasil Dihapus";
        bukuRepository.deleteById(id);
        return result;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable(value = "id") Long id){
        Buku buku = bukuRepository.getOne(id);
        if (buku == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(buku);
    }

    @GetMapping("/{sortbuku}")
    public List<Buku> sortbuku(@RequestParam(value = "title") String titleBook){
        return bukuRepository.findByTitleBook(titleBook);
    }

    @GetMapping("/sortstatus/{statusPeminjaman}")
    public List<Buku> sortstatus(@PathVariable(value = "statu   sPeminjaman") int statusPeminjaman){
        return bukuRepository.findByStatusPeminjaman(statusPeminjaman);
    }
}
