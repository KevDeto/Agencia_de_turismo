package hackacode.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "paquetes_turisticos")
public class PaqueteTuristico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoPaquete;

    @ManyToMany
    @JoinTable(
        name = "paquete_servicio",
        joinColumns = @JoinColumn(name = "codigo_paquete"),
        inverseJoinColumns = @JoinColumn(name = "codigo_servicio")
    )
    private List<ServicioTuristico> listaServiciosIncluidos;
 
    @Column(nullable = false)
    private Double costoPaquete;
}
