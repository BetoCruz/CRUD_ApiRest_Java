package med.voll.api.medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//nesta interface iremos erdar de outra interface que é disponibilizada pelo SpringData
// (A interface armazena os dados no banco de dados) - " JpaRepository< Tipo do obj que será tdrabalhado ,tipo do atributo da chave primaria > "
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao); // Esse padrao de nomenclatura gera a busca
}
