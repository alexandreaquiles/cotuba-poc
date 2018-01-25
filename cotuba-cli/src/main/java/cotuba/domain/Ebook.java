package cotuba.domain;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ebook {

	private FormatoDoEbook formato;

	private List<Capitulo> capitulos = new ArrayList<>();

	private Path arquivoDeSaida;

	public Ebook(FormatoDoEbook formato, Path arquivoDeSaida) {
		this.formato = formato;
		this.arquivoDeSaida = arquivoDeSaida;
	}

	public FormatoDoEbook getFormato() {
		return formato;
	}
	
	public Path getArquivoDeSaida() {
		return arquivoDeSaida;
	}
	
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public Capitulo ultimoCapitulo() {
		return capitulos.get(capitulos.size()-1);
	}

	public void adicionaCapitulo(Capitulo capitulo) {
		this.capitulos.add(capitulo);
	}

	public void adicionaCapitulos(List<Capitulo> capitulos) {
		this.capitulos.addAll(capitulos);
	}
}
