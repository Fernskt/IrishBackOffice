/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.specifications;

import com.IrishBackOffice.ART.entities.Siniestro;
import com.IrishBackOffice.ART.entities.Usuario;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Pc
 */
public class SiniestroSpecification {
    
     public static Specification<Siniestro> tieneTipoStro(String tipoStro) {
        return (root, query, builder) -> {
            // WHERE lower(tipoStro) = lower(:tipoStro)
            return builder.equal(
                builder.lower(root.get("tipoStro")), 
                tipoStro.toLowerCase()
            );
        };
    }

    public static Specification<Siniestro> tieneTipoInvestigacion(String tipoInvestigacion) {
        return (root, query, builder) -> {
            return builder.equal(
                builder.lower(root.get("tipoInvestigacion")), 
                tipoInvestigacion.toLowerCase()
            );
        };
    }

    public static Specification<Siniestro> tieneResultado(String resultado) {
        return (root, query, builder) -> {
            return builder.equal(
                builder.lower(root.get("resultado")), 
                resultado.toLowerCase()
            );
        };
    }
    
        public static Specification<Siniestro> tieneArtId(Long artId) {
        return (root, query, builder) -> {
            if (artId == null) {
                return builder.conjunction(); // no agrega condición si artId es null
            }
            // JOIN implícito a Art y comparación por idART
            return builder.equal(root.get("art").get("idART"), artId);
        };
    }
          public static Specification<Siniestro> tieneAnalistaId(UUID analistaId) {
        return (root, query, builder) -> {
            if (analistaId == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("analista").get("id"), analistaId);
        };
    }
}
