package model.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
public class Cliente extends Persona{
	
}
