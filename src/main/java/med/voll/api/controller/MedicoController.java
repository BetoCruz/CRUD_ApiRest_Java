package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // une o Controller a um ResponseBody para todos métodos marcados pelo RequestMapping.
@RequestMapping("/medicos")
public class MedicoController {

    //   @Autowired Delega ao spring a responsabilidade de instanciar o atributo, ja que o atributo é uma interface repositori,conhecido pelo Spring, ele concegue criar o obj é passar para o controller aotomaticamnete.
    @Autowired //injetando repository como sendo um atributo ,
    private MedicoRepository repository;

    @PostMapping
    @Transactional //estabelece a transação ativa para inserts no banco de dados.
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicos dados){ // @Valid solicita ao Spring a integração com o bean validation e realizar as validações em cima desse DTO e todos os outros que possuem @valid.
        repository.save(new Medico(dados));
    }

    //----------Modulo 4 Requisições GET -----Paginação Page,Pageable.---------ordenação no parametro use: (@PageableDefault(size = 10)
    @GetMapping //Metodo de leitura // Repository erda findAll() de JPA.
    public Page<DadosListagemMedico> Listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        //@PageableDefault possui varios parametros de paginação.
        // Pegeable , este é uma interface do spring, deve ser o tipo da variavel no parametro. Exige que o tipo do metodo seja 'Page<>'/Page<DadosListagemMedico> e não mais 'List'.PAge<> devolve informações sobre a paginação.
        //_____return repository.findAll(paginacao).map(DadosListagemMedico::new);
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        //Page ja tem um metodo map , logo não é necessario o metodo stream  e o toList() ,
        // pois a devoluçaõ do page cumpre esse papel.
        //O ".map(DadosListagemMedico::new)" continua em uso para realizar a converçao de  tipos , de medico para DadosListagemMedico.
        //-----Ordenação ------ns URL usar SORT,
        //-----http://localhost:8090/medicos?size=1&page=1
        //-----http://localhost:8090/medicos?sort=nome
        //-----http://localhost:8090/medicos?sort=ATRIBUTO DO OBJETO
        //-----http://localhost:8090/medicos?sort=crm,desc
        //-----http://localhost:8090/medicos?sort=crm,asc
        //-----http://localhost:8090/medicos?sort=crm,desc&size=2&page=1
        //-----Os parametros da URL sobrescrevem a anotação @PageableDefault.
    }

    @PutMapping //Realiza escrita no banco de dados, logo requer anotação @Transaction
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

//fdfdfd

    @Transactional
    @DeleteMapping("/{id}") //Definindo o parametro dinamico
    // O id sera capturado como parametro do metodo excluir.
    // @PathVariable : para dizer ao spring que o "/{id}" que chega pela URL
    // é o mesmo recebido no metodo excluir e para que ele os use.
    public void excluir(@PathVariable Long id){  //@PathVariable diz ao spring que a o id da url é
        //repository.delete ById(id);
        var medico = repository.getReferenceById(id); //captura a entidade no banco de dados.
        medico.excluir();
    }


}