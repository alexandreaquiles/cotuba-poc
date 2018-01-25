package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.CotubaException;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoDoEbook;
import cotuba.domain.GeradorDeEbook;
import cotuba.epub.GeradorDeEPUB;
import cotuba.md.RenderizadorDeMD;
import cotuba.pdf.GeradorDePDF;
import cotuba.plugin.AoFinalizarGeracao;

public class Cotuba {

	private FormatoDoEbook formato;
	private Path arquivoDeSaida;
	private Path diretorioDosMD;
	
	public Cotuba(FormatoDoEbook formato, Path arquivoDeSaida, Path diretorioDosMD) {
		this.formato = formato;
		this.arquivoDeSaida = arquivoDeSaida;
		this.diretorioDosMD = diretorioDosMD;
	}

	public void executa () {
		Ebook ebook = new Ebook(formato, arquivoDeSaida);

		RenderizadorDeMD renderizador = new RenderizadorDeMD();
		List<Capitulo> capitulos = renderizador.renderizaParaHTML(diretorioDosMD);
		ebook.adicionaCapitulos(capitulos);
		
		GeradorDeEbook gerador;
		if (FormatoDoEbook.EPUB.equals(formato)) {
			gerador = new GeradorDeEPUB();
		} else if (FormatoDoEbook.PDF.equals(formato)) {
			gerador = new GeradorDePDF();
		} else {
			throw new CotubaException("Formato do ebook inválido: " + formato);
		}
		
		gerador.gera(ebook);
		System.out.println("Arquivo gerado com sucesso: " + ebook.getArquivoDeSaida());
	
		AoFinalizarGeracao.roda(ebook);
	}
}
