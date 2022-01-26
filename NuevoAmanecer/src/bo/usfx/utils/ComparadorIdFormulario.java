package bo.usfx.utils;

import bo.usfx.nuevoAmanecer.model.domain.SistemaFormulariosPermisosUsuarios;
import java.util.Comparator;



public class ComparadorIdFormulario implements Comparator<SistemaFormulariosPermisosUsuarios> {
	@Override
	public int compare(SistemaFormulariosPermisosUsuarios o1,SistemaFormulariosPermisosUsuarios o2) {
//		int retorno = 0;
//		if (o1.getSistemaFormulario().getNombre_formulario().compareTo(o2.getSistemaFormulario().getNombre_formulario()) < 0)
//			retorno = -1;
//		if (o1.getSistemaFormulario().getNombre_formulario().compareTo(o2.getSistemaFormulario().getNombre_formulario()) == 0)
//			retorno = 0;
//		if (o1.getSistemaFormulario().getNombre_formulario().compareTo(o2.getSistemaFormulario().getNombre_formulario()) > 0)
//			retorno = 1;		
		return o1.getSistemaFormulario().getNombre_formulario().compareTo(o2.getSistemaFormulario().getNombre_formulario());
	}
	public boolean equals(Object o) {
		return this == o;
	}
}
