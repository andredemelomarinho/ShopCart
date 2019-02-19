package Enum;

public enum Etiqueta {
	
	LiminarDeferida("Liminar Deferida", "Lim"),
	EmTurmasRecursais("Em Turmas Recursais", "Tur"),
	ImpugnacaoFaseCumprimentoSentenca("Impugnação à Fase de Cumprimento de Sentença", "Imp"),
	ExecucaoTituloExtrajudicial("Execução de Título Extrajudicial", "Exe"),
	ExcecaoIncompetencia("Exceção de Incompetência", "Exc"),
	EmbargosExecucao("Embargos à Execução", "Emb"),
	SubstituicaoJulgador("Substituição de Julgador", "Sub"),
	EmbargosExecucaoExtrajudicial("Embargos à Execução Extrajudicial", "Emb"),
	ExcecaoPreexecutividade("Exceção de Pré-executividade", "Epe"),
	BacenJud("Bacen Jud", "Bac"),
	RenaJud("Rena Jud", "Ren"),
	Penhora("Penhora", "Pen"),
	Urgente("Urgente", "Urg"),
	Idoso("Idoso", "Ido"), 
	ProcessoApensadoConsulteNoEditarProcesso("Processo Apensado - Consulte no Editar Processo", "Ape"),
	Calculo("Cálculo", "Cal"),
	ValorAtualizado("Valor Atualizado", "Val"),
	ProcessoRedistribuido("Processo Redistribuído", "Red");
	
	private final String descricaoEtiqueta;
	private final String exibicaoEtiqueta;
	
	Etiqueta(String descricaoEtiqueta, String exibicaoEtiqueta){
		this.descricaoEtiqueta = descricaoEtiqueta;
		this.exibicaoEtiqueta = exibicaoEtiqueta;
	}
	public String getDescricaoEtiqueta(){
		return descricaoEtiqueta;
	}
	public String getExibicaoEtiqueta() {
		return exibicaoEtiqueta;
	}
}
