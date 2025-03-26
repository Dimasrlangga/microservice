package com.teknologiinformasi.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teknologiinformasi.restapi.model.Customer;
import com.teknologiinformasi.restapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {


   @Autowired
   private CustomerRepository customerRepository;


   public List<Customer> getAllProduk() {
       return customerRepository.findAll();
   }


   public Optional<Customer> getProdukById(Long id) {
       return customerRepository.findById(id);
   }


   public Customer createProduk(Customer produk) {
       return customerRepository.save(produk);
   }


   public Customer updateProduk(Long id, Customer produkDetails) {
       Customer produk = customerRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Customer tidak ditemukan dengan id " + id));
       produk.setNama(produkDetails.getNama());
       produk.setEmail(produkDetails.getEmail());
       produk.setAlamat(produkDetails.getAlamat());
       return customerRepository.save(produk);
   }


   public void deleteProduk(Long id) {
       Customer produk = customerRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Customer tidak ditemukan dengan id " + id));
       customerRepository.delete(produk);
   }
}
