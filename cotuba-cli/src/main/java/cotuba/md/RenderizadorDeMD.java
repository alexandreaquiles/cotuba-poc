package cotuba.md;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import cotuba.domain.Capitulo;
import cotuba.domain.CotubaException;
import cotuba.plugin.LogoAposRenderizarMDParaHTML;

public class RenderizadorDeMD {

	public List<Capitulo> renderizaParaHTML(Path diretorioDosMD) {
		List<Capitulo> capitulos = new ArrayList<>();
		obtemArquivosMD(diretorioDosMD)
			.forEach(arquivoMD -> {
				Capitulo capitulo = new Capitulo();
				Node document = parseDoArquivoMD(arquivoMD, capitulo);
				renderizaArquivoMDParaHTML(arquivoMD, capitulo, document);
				capitulos.add(capitulo);
				LogoAposRenderizarMDParaHTML.rodaPara(capitulo);
			});
		return capitulos;
	}

	private Stream<Path> obtemArquivosMD(Path diretorioDosMD) {
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.md");
		Stream<Path> arquivosMD = Stream.empty();
		try {
			arquivosMD = Files.list(diretorioDosMD).filter(arquivo -> matcher.matches(arquivo));
		} catch (IOException ex) {
			throw new CotubaException("Erro tentando encontrar arquivos .md em " + diretorioDosMD.toAbsolutePath(), ex);
		}
		return arquivosMD;
	}

	private Node parseDoArquivoMD(Path arquivoMD, Capitulo capitulo) {
		Parser parser = Parser.builder().build();
		Node document = null;
		try {
			document = parser.parseReader(Files.newBufferedReader(arquivoMD));
			document.accept(new HeadingVisitor(capitulo));
		} catch (Exception ex) {
			throw new CotubaException("Error parsing file " + arquivoMD, ex);
		}
		return document;
	}

	private void renderizaArquivoMDParaHTML(Path arquivoMD, Capitulo capitulo, Node document) {
		try {
			HtmlRenderer renderer = HtmlRenderer.builder().build();
			String html = renderer.render(document);
			capitulo.setConteudoHTML(html);
		} catch (Exception ex) {
			throw new CotubaException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
		}
	}
}
