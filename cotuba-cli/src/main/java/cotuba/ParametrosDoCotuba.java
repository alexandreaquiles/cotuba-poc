package cotuba;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import cotuba.domain.CotubaException;
import cotuba.domain.FormatoDoEbook;

class ParametrosDoCotuba {

	private static final FormatoDoEbook FORMATO_DEFAULT = FormatoDoEbook.PDF;
	
	private static final String OPCAO_DIR = "dir";
	private static final String OPCAO_FORMATO = "format";
	private static final String OPCAO_OUTPUT = "output";
	
	private Path diretorioDosMD;
	private FormatoDoEbook formatoDoEbook;
	private Path arquivoDeSaidaDoEbook;

	public ParametrosDoCotuba(CommandLine cmd) {

		trataDiretorioDosMD(cmd);

		trataFormatoDoEbook(cmd);

		trataArquivoDeSaidaDoEbook(cmd);

	}

	private void trataFormatoDoEbook(CommandLine cmd) {
		String nomeDoFormatoDoEbook = cmd.getOptionValue(OPCAO_FORMATO);

		if (nomeDoFormatoDoEbook != null) {
			try {
				this.formatoDoEbook = FormatoDoEbook.valueOf(nomeDoFormatoDoEbook.toUpperCase());
			} catch (IllegalArgumentException ex) {
				throw new CotubaException("O formato " + nomeDoFormatoDoEbook + " não é suportado.", ex);

			}
		} else {
			this.formatoDoEbook = FORMATO_DEFAULT;
		}
	}

	private void trataDiretorioDosMD(CommandLine cmd) {
		String nomeDoDiretorioDosMD = cmd.getOptionValue(OPCAO_DIR);

		if (nomeDoDiretorioDosMD != null) {
			Path diretorioDosMD = Paths.get(nomeDoDiretorioDosMD);
			if (!Files.isDirectory(diretorioDosMD)) {
				throw new CotubaException(nomeDoDiretorioDosMD + " não é um diretório.");
			}
			this.diretorioDosMD = diretorioDosMD;

		} else {
			Path diretorioAtual = Paths.get("");
			this.diretorioDosMD = diretorioAtual;
		}
	}

	private void trataArquivoDeSaidaDoEbook(CommandLine cmd) {
		String nomeDoArquivoDeSaidaDoEbook = cmd.getOptionValue(OPCAO_OUTPUT);
		if (nomeDoArquivoDeSaidaDoEbook != null) {
			Path arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
			if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
				throw new CotubaException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
			}
			this.arquivoDeSaidaDoEbook = arquivoDeSaida;
		} else {
			this.arquivoDeSaidaDoEbook = Paths.get("book." + this.formatoDoEbook.name().toLowerCase());
		}

	}

	public Path getDiretorioDosMD() {
		return diretorioDosMD;
	}

	public FormatoDoEbook getFormato() {
		return formatoDoEbook;
	}

	public Path getArquivoDeSaidaDoEbook() {
		return arquivoDeSaidaDoEbook;
	}

	
	@Override
	public String toString() {
		return "ParametrosDoCotuba [diretorioDosMD=" + diretorioDosMD + ", formatoDoEbook=" + formatoDoEbook
				+ ", arquivoDeSaidaDoEbook=" + arquivoDeSaidaDoEbook + "]";
	}

	public static Options criaOpcoes() {
		Options options = new Options();

		Option opcaoDeDiretorioDosMD = new Option("d", OPCAO_DIR, true, "Diretório que contem os arquivos md. Default: diretório atual.");
		options.addOption(opcaoDeDiretorioDosMD);

		Option opcaoDeFormatoDoEbook = new Option("f", OPCAO_FORMATO, true, "Formato de saída do ebook. Pode ser: pdf ou epub. Default: " + FORMATO_DEFAULT.name().toLowerCase());
		options.addOption(opcaoDeFormatoDoEbook);

		Option opcaoDeArquivoDeSaida = new Option("o", OPCAO_OUTPUT, true, "Arquivo de saída do ebook. Default: book.{formato}.");
		options.addOption(opcaoDeArquivoDeSaida);

		return options;
	}


}
