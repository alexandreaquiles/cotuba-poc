package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Ebook;

public interface AoFinalizarGeracao {

	void trata(Ebook ebook);

	public static void roda(Ebook ebook) {
		ServiceLoader
			.load(AoFinalizarGeracao.class)
			.forEach(f -> f.trata(ebook));
	}
}
