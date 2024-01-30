package med.voll.api.medico;

public record DadosListagemMedico(Long id,String nome, String email , String crm, Especialidade especialidade) {
    //-----Modulo 4 Requisições GET -----
    public DadosListagemMedico(Medico medico) {//Construtor do record para estabelecer a captura do tipo medico e deste pegar os atributos de interesse.
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
