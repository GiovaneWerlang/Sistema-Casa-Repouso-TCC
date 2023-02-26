package br.edu.utfpr.residente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResidenteRepository implements PanacheRepository<ResidenteModel> {
}
