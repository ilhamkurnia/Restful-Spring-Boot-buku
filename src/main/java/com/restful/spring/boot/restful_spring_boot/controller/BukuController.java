package com.restful.spring.boot.restful_spring_boot.controller;

import com.restful.spring.boot.restful_spring_boot.model.Buku;
import com.restful.spring.boot.restful_spring_boot.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import com.restful.spring.boot.restful_spring_boot.repository.BukuRepository;

@RestController
@RequestMapping("/buku")
public class BukuController {

    @Autowired
    BukuRepository bukuRepository;

    @GetMapping("/")
    public List<Buku> getAll(){
        return bukuRepository.findAll();
    }

    @PostMapping("/")
    public Buku tambahbuku(@Valid @RequestBody Buku buku){
        return bukuRepository.save(buku);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku (@PathVariable(value = "id")Long id, @Valid @RequestBody Buku detailbuku){
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

    @DeleteMapping("/{id}")
    public String deleteBuku(@PathVariable ( value="id") Long id){
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
