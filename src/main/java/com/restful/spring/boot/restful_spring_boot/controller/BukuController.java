package com.restful.spring.boot.restful_spring_boot.controller;

import com.restful.spring.boot.restful_spring_boot.exception.ResourceNotFoundException;
import com.restful.spring.boot.restful_spring_boot.model.Buku;
import com.restful.spring.boot.restful_spring_boot.repository.BukuRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/Buku")
@Api(value = "Buku Rest Api", description = "Menjalankan Rest Api Buku")
public class BukuController {

    @Autowired
    BukuRepository bukuRepository;

    @ApiOperation(value = "Melihat List Buku ", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sukses Menjalankan List"),
            @ApiResponse(code = 400, message = "Accesing forbiddeen"),
            @ApiResponse(code = 500, message = "tidak ditemukan")})

    @GetMapping("/buku/List")
    public List<Buku> getAll() {
        return bukuRepository.findAll();
    }

    @ApiOperation(value = "Mendapatkan List Semua Buku")
    @GetMapping("/buku/Get{id}")
    public ResponseEntity<Buku> getId(
            @ApiParam(value = "id Buku Object", required = true)
            @PathVariable(value = "id") Long Id) throws ResourceNotFoundException {
        Buku buku = bukuRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Buku tidak ditemukan ::" + Id));
        return ResponseEntity.ok().body(buku);
    }


    @ApiOperation(value = "Membuat List Buku ")
    @PostMapping("buku/Post")
    public Buku tambahbuku(
            @ApiParam(value = "Membuat List Buku", required = true) @Valid @RequestBody Buku buku) {
        return bukuRepository.save(buku);
    }

    @ApiOperation(value = "MengUpdate Buku")
    @PutMapping("/buku/Update{id}")
    public ResponseEntity<Buku> updateBuku(
            @ApiParam(value = "id buku untuk update buku", required = true)
            @PathVariable(value = "id") Long id,
            @ApiParam(value = "Update Buku", required = true)
            @Valid @RequestBody Buku detailbuku) throws ResourceNotFoundException {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buku tidak ditemukan::" + id));
        buku.setTitleBook(detailbuku.getTitleBook());
        buku.setNamaDepanPengarang(detailbuku.getNamaDepanPengarang());
        buku.setNamaBelakangPengarang(detailbuku.getNamaBelakangPengarang());
        buku.setNamaPeminjam(detailbuku.getNamaPeminjam());
        buku.setStatusPeminjaman(detailbuku.getStatusPeminjaman());
        final Buku updatedBuku = bukuRepository.save(buku);
        return ResponseEntity.ok(updatedBuku);
    }

    @ApiOperation(value = "Menghapus Buku")
    @DeleteMapping("/buku/Delete/{id}")
    public Map<String, Boolean> deleteBuku(
            @ApiParam(value = "Id Buku dihapus", required = true)
            @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buku Tidak ditemukan" + id));

        bukuRepository.delete(buku);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return  response;

    }
}

