package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;


//  ----------  Anotações para criar a entidade JPA
//  ---------- Persistencia de dados é registra os dados no banco.
@Table(name = "medicos")
@Entity(name = "Medico")
//Vamos importar a biblioteca Lombok, para gerar os códigos Java que faltam automaticamente. Adicionaremos @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode(of = "id"):
@Getter //Gera os metodos Getters
@NoArgsConstructor //Gera o constructor defaut sem argumentos que a JPA exige em todas as entidades.
@AllArgsConstructor //Gera o constructor que recebe todos os campos.
@EqualsAndHashCode(of = "id")//Gera o EqualsHashCode em cima do ID e não em cima de todos os atributos.

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tratamento da chave primaria, vamos deixar essa responsabilidade com o provedor de persistência (quando também declaramos a anotação @GeneratedValue).
    private Long id;
    private String nome;
    private String email;

    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded //integração
    private Endereco endereco;

    private boolean ativo; //Propriedade/coluna ativo

    public Medico(DadosCadastroMedicos dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    //-----modulo 5 Atualizabdo dados
    public void atualizarInformacoes(DadosAtualizacaoMedicos dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInfoemacao(dados.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
