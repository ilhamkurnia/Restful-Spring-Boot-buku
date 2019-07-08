package com.restful.spring.boot.restful_spring_boot.controller;

import com.restful.spring.boot.restful_spring_boot.exception.ResourceNotFoundException;
import com.restful.spring.boot.restful_spring_boot.model.Toko;
import com.restful.spring.boot.restful_spring_boot.repository.TokoRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Toko")
@Api(value = "Toko Rest Api")
public class TokoController {

    @Autowired
    TokoRepository tokoRepository;

    @ApiOperation(value = "Melihat Semua List Toko",response = List.class)
    @ApiResponses(value = {@ApiResponse(code=200, message = "Sukses"),
    @ApiResponse(code = 400, message = "Kesalahan Input"),
    @ApiResponse(code = 500, message = "Tidak Ditemukan")})

    @GetMapping("/toko")
    public List<Toko> getAll(){
        return tokoRepository.findAll();
    }

    @ApiOperation(value = "Melihat List Toko dengan Id")
    @GetMapping("/toko/findByid{id}")
    public ResponseEntity<Toko>getIdToko(
            @ApiParam(value = "Id Toko", required = true)
            @PathVariable(value = "id")Long id) throws ResourceNotFoundException{
        Toko toko = tokoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id toko tidak ditemukan"+id));
        return ResponseEntity.ok().body(toko);
    }

    @ApiOperation(value = "Membuat List Toko")
    @PostMapping("/toko/post")
    public Toko postToko(
            @ApiParam(value = "List Toko", required = true) @Valid @RequestBody Toko toko){
        return tokoRepository.save(toko);
    }

    @ApiOperation(value = "Update List Toko")
    @PutMapping("/toko/put{id}")
    public ResponseEntity<Toko> updateToko(
            @ApiParam(value = "Update List Toko", required = true)
            @PathVariable(value = "id") Long idToko,
            @ApiParam(value = "Update List Toko", required = true)
            @Valid @RequestBody Toko detailToko) throws ResourceNotFoundException{
        Toko toko = tokoRepository.findById(idToko)
                .orElseThrow(()-> new ResourceNotFoundException("Toko tidak ditemukan"+idToko));
        toko.setNamaToko(detailToko.getNamaToko());
        toko.setAlamatToko(detailToko.getAlamatToko());
        toko.setOwnerToko(detailToko.getOwnerToko());
        final Toko updatedToko = tokoRepository.save(toko);
        return ResponseEntity.ok(updatedToko);
    }

    @ApiOperation("Delete List buku")
    @DeleteMapping("/toko/delete{id}")
    public Map<String, Boolean> deleteBuku(
            @ApiParam(value = "List Toko dihapus", required = true)
            @PathVariable(value = "id") Long idToko)
        throws ResourceNotFoundException{
        Toko toko = tokoRepository.findById(idToko)
                .orElseThrow(()-> new ResourceNotFoundException("Toko tidak ditemukan"+idToko));
        tokoRepository.delete(toko);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
