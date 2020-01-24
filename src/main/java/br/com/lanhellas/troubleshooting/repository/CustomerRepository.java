package br.com.lanhellas.troubleshooting.repository;

import br.com.lanhellas.troubleshooting.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
