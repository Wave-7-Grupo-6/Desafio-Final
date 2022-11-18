package br.com.dh.meli.desafiofinal.repository;

import br.com.dh.meli.desafiofinal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
