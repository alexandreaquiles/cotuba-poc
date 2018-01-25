package cotuba.md;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Text;

import cotuba.domain.Capitulo;

class HeadingVisitor extends AbstractVisitor {
	
	private Capitulo capitulo;
	
	public HeadingVisitor(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	public void visit(Heading heading) {
		if (heading.getLevel() == 1) {
			//capítulo
			Text tituloCapitulo = (Text) heading.getFirstChild();
			capitulo.setTitulo(tituloCapitulo.getLiteral());
		} else if (heading.getLevel() == 2) {
			//seção
		} else if (heading.getLevel() == 3) {
			//título
		}
	}
}