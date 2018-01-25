package cotuba.epub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import cotuba.domain.Capitulo;
import cotuba.domain.CotubaException;
import cotuba.domain.Ebook;
import cotuba.domain.GeradorDeEbook;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;

public class GeradorDeEPUB implements GeradorDeEbook {

	public void gera(Ebook ebook) {
		Book epub = new Book();
		for (Capitulo capitulo : ebook.getCapitulos()) {
			epub.addSection(capitulo.getTitulo(),
					new Resource(capitulo.getConteudoHTML().getBytes(), MediatypeService.XHTML));
		}

		EpubWriter epubWriter = new EpubWriter();

		Path epubFile = ebook.getArquivoDeSaida();

		try {
			epubWriter.write(epub, Files.newOutputStream(epubFile));
		} catch (IOException ex) {
			throw new CotubaException("Erro ao criar arquivo EPUB: " + epubFile.toAbsolutePath(), ex);
		}
	}
}
