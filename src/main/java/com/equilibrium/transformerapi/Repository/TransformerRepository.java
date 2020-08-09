package com.equilibrium.transformerapi.Repository;



import com.equilibrium.transformerapi.model.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformerRepository extends JpaRepository<Transformer, Long> {

}
