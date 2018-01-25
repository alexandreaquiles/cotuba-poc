package cotuba;

import java.nio.file.Path;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cotuba.application.Cotuba;
import cotuba.domain.FormatoDoEbook;

public class Main {

	public static void main(String[] args) {
		Options opcoesCLI = ParametrosDoCotuba.criaOpcoes();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter ajuda = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = parser.parse(opcoesCLI, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			ajuda.printHelp("cotuba", opcoesCLI);
			System.exit(1);
			return;
		}

		try {
			ParametrosDoCotuba parametros = new ParametrosDoCotuba(cmd);
			Path diretorioDosMD = parametros.getDiretorioDosMD();
			FormatoDoEbook formato = parametros.getFormato();
			Path arquivoDeSaida = parametros.getArquivoDeSaidaDoEbook();
	
			Cotuba cotuba = new Cotuba(formato, arquivoDeSaida, diretorioDosMD);
			cotuba.executa();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			System.exit(1);
		}
	}

}
