package br.edu.utfpr.consulta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsultaRepository implements PanacheRepository<ConsultaModel> {
}
