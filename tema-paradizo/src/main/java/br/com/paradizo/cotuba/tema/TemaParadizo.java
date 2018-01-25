package br.com.paradizo.cotuba.tema;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cotuba.domain.Capitulo;
import cotuba.plugin.LogoAposRenderizarMDParaHTML;

public class TemaParadizo implements LogoAposRenderizarMDParaHTML {

	public void trata(Capitulo capitulo) {
		System.out.println("Executando o tema Caelum para o capítulo: " + capitulo.getTitulo());
		String html = capitulo.getConteudoHTML();
		Document doc = Jsoup.parse(html);
		doc.select("head").prepend("<style> h1 { border: 1px dashed black; } </style>");
		capitulo.setConteudoHTML(doc.html());
	}

}
