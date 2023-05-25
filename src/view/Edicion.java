package view;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Durable;
import model.EnumTipoPublicacion;
import model.Filtrable;
import model.Publicacion;

public class Edicion extends JPanel {
	public Edicion(Publicacion publicacion) {
		super();
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new GridLayout(2, 1, 0, 15));
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
        this.setBorder(compoundBorder);
        
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.AUDIO || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
			add(new PanelDuracion((Durable)publicacion));
		}
		if (publicacion.getTipoPublicacion() == EnumTipoPublicacion.IMAGEN || publicacion.getTipoPublicacion() == EnumTipoPublicacion.VIDEO) {
			add(new PanelFiltros((Filtrable)publicacion));
		}
	}
}
