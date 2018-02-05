module tema.paradizo {
	requires cotuba.cli;
	requires jsoup;

	provides cotuba.plugin.LogoAposRenderizarMDParaHTML with br.com.paradizo.cotuba.tema.TemaParadizo;
}