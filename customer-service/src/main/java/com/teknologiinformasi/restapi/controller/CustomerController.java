package com.teknologiinformasi.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.model.Customer;
import com.teknologiinformasi.restapi.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/customers")
public class CustomerController {


   @Autowired
   private CustomerService customerService;


   // Endpoint untuk mengambil semua produk
   @GetMapping
   public List<Customer> getAllProduk() {
       return customerService.getAllProduk();
   }

      // Endpoint untuk mengambil produk berdasarkan id
      @GetMapping("/{id}")
      public ResponseEntity<Customer> getProdukById(@PathVariable Long id) {
          return customerService.getProdukById(id)
                  .map(produk -> ResponseEntity.ok().body(produk))
                  .orElse(ResponseEntity.notFound().build());
      }


   // Endpoint untuk membuat produk baru
   @PostMapping
   public Customer createProduk(@RequestBody Customer produk) {
       return customerService.createProduk(produk);
   }


   // Endpoint untuk mengupdate produk yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Customer> updateProduk(@PathVariable Long id, @RequestBody Customer produkDetails) {
       try {
           Customer updatedProduk = customerService.updateProduk(id, produkDetails);
           return ResponseEntity.ok(updatedProduk);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
   }


   // Endpoint untuk menghapus produk
  @DeleteMapping("/{id}")
public ResponseEntity<Map<String, String>> deleteProduk(@PathVariable Long id) {
   try {
       customerService.deleteProduk(id);
       Map<String, String> response = new HashMap<>();
       response.put("message", "Customer berhasil dihapus");
       return ResponseEntity.ok(response);
   } catch (RuntimeException e) {
       Map<String, String> response = new HashMap<>();
       response.put("message", "Customer tidak ditemukan dengan id " + id);
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
   }
}
}
