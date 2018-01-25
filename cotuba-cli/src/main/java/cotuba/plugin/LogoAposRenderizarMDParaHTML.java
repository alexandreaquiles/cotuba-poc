package cotuba.plugin;

import java.util.ServiceLoader;

import cotuba.domain.Capitulo;

public interface LogoAposRenderizarMDParaHTML {

	void trata(Capitulo capitulo);

	public static void rodaPara(Capitulo capitulo) {
		ServiceLoader
			.load(LogoAposRenderizarMDParaHTML.class)
			.forEach(d -> d.trata(capitulo));
	}
}
