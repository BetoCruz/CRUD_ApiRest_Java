package med.voll.api.endereco;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//----- Vamos usar |Embeddable Attribute| da JPA para que Endereco fique
// em uma classe separada, mas faça parte da mesma tabela de Medicos
// junto ao banco de dados.

@Embeddable
//Vamos importar a biblioteca Lombok, para gerar os códigos Java que faltam automaticamente. Adicionaremos @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode(of = "id"):
@Getter //Gera os metodos Getters
@NoArgsConstructor //Gera o constructor defaut sem argumentos que a JPA exige em todas as entidades.
@AllArgsConstructor //Gera o constructor que recebe todos os campos.

public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInfoemacao(DadosEndereco dados) {
        if(dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        }
        if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
        if(dados.uf() != null){
            this.uf = dados.uf();
        }
        if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }
        if(dados.numero() != null){
            this.numero = dados.numero();
        }
        if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }

    }
}
