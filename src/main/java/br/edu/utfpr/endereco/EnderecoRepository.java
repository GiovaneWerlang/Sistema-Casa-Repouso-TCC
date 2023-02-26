package br.edu.utfpr.endereco;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository  implements PanacheRepository<EnderecoModel> {
}
