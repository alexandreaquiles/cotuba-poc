module cotuba.cli {
	exports cotuba.plugin;
	exports cotuba.domain;

	requires commons.cli;

	requires epublib.core;

	requires html2pdf;
	requires kernel;
	requires layout;

	requires org.commonmark;
	
	uses cotuba.plugin.LogoAposRenderizarMDParaHTML;
	uses cotuba.plugin.AoFinalizarGeracao;
}
