package br.edu.utfpr.exame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExameRepository implements PanacheRepository<ExameModel> {
}
