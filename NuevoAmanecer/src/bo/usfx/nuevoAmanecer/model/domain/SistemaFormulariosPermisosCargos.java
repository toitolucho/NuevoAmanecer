/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.domain;

/**
 *
 * @author Luis Molina
 */
public class SistemaFormulariosPermisosCargos  implements Comparable<Object>{
	private String codigo_cargo;
	private int codigo_formulario;
	private boolean permitir_insertar;
	private boolean permitir_editar;
	private boolean pemitir_eliminar;
	private boolean permitir_navegar;
	private boolean permitir_reportes;
	private boolean permitir_anular;


	private SistemaFormularios sistemaFormulario;

	public SistemaFormulariosPermisosCargos(String codigo_cargos,
			int idSistemaFormulario, boolean permitirInsertar,
			boolean permitirEditar, boolean pemitirEliminar,
			boolean permitirNavegar, boolean permitirReportes,
			boolean permitirAnular) {
		super();
		codigo_cargo = codigo_cargos;
		codigo_formulario = idSistemaFormulario;
		permitir_insertar = permitirInsertar;
		permitir_editar = permitirEditar;
		pemitir_eliminar = pemitirEliminar;
		permitir_navegar = permitirNavegar;
		permitir_reportes = permitirReportes;
		permitir_anular = permitirAnular;

	}

	public SistemaFormulariosPermisosCargos()
	{
		this.sistemaFormulario = new SistemaFormularios();
	}

	public SistemaFormulariosPermisosCargos(SistemaFormularios formulario, String codigo_cargos)
	{
		this.sistemaFormulario = formulario;
		codigo_cargo = codigo_cargos;
		codigo_formulario = formulario.getCodigo_formulario();
		permitir_insertar = false;
		permitir_editar = false;
		pemitir_eliminar = false;
		permitir_navegar = false;
		permitir_reportes = false;
		permitir_anular = false;

	}


	public int getId_sistema_formulario() {
		return codigo_formulario;
	}

	public void setId_sistema_formulario(int idSistemaFormulario) {
		codigo_formulario = idSistemaFormulario;
	}

	public boolean isPermitir_insertar() {
		return permitir_insertar;
	}

	public void setPermitir_insertar(boolean permitirInsertar) {
		permitir_insertar = permitirInsertar;
	}

	public boolean isPermitir_editar() {
		return permitir_editar;
	}

	public void setPermitir_editar(boolean permitirEditar) {
		permitir_editar = permitirEditar;
	}

	public boolean isPemitir_eliminar() {
		return pemitir_eliminar;
	}

	public void setPemitir_eliminar(boolean pemitirEliminar) {
		pemitir_eliminar = pemitirEliminar;
	}

	public boolean isPermitir_navegar() {
		return permitir_navegar;
	}

	public void setPermitir_navegar(boolean permitirNavegar) {
		permitir_navegar = permitirNavegar;
	}

	public boolean isPermitir_reportes() {
		return permitir_reportes;
	}

	public void setPermitir_reportes(boolean permitirReportes) {
		permitir_reportes = permitirReportes;
	}

	public boolean isPermitir_anular() {
		return permitir_anular;
	}

	public void setPermitir_anular(boolean permitirAnular) {
		permitir_anular = permitirAnular;
	}


	public void setSistemaFormulario(SistemaFormularios sistemaFormulario) {
		this.sistemaFormulario = sistemaFormulario;
	}

	public SistemaFormularios getSistemaFormulario() {
		return sistemaFormulario;
	}

        public int getCodigo_formulario() {
            return codigo_formulario;
        }

        public void setCodigo_formulario(int codigo_formulario) {
            this.codigo_formulario = codigo_formulario;
        }

    public String getCodigo_cargo() {
        return codigo_cargo;
    }

    public void setCodigo_cargo(String codigo_cargo) {
        this.codigo_cargo = codigo_cargo;
    }

        



	@Override
	public String toString() {

		return "SistemaFormulariosPermisosUsuarios [Id Cargo =" + codigo_cargo
				+", sistemaFormulario=" + sistemaFormulario
				+ ", id_sistema_formulario=" + codigo_formulario
				+ ", pemitir_eliminar=" + pemitir_eliminar
				+ ", permitir_anular=" + permitir_anular
				+ ", permitir_editar=" + permitir_editar
				+ ", permitir_insertar=" + permitir_insertar
				+ ", permitir_navegar=" + permitir_navegar
				+ ", permitir_reportes=" + permitir_reportes + "]";
	}

	public boolean existeAlgunPermiso()
	{
		int suma = 0;
		suma += permitir_anular ? 1 : 0;
		suma += permitir_editar ? 1 : 0;
		suma += permitir_insertar ? 1 : 0;
		suma += permitir_navegar ? 1 : 0;
		suma += permitir_reportes ? 1 : 0;
		suma += pemitir_eliminar ? 1 : 0;

//		boolean retorno = permitir_insertar & permitir_editar & pemitir_eliminar;

//		&& permitirNavegar && permitirReportes
//		&& permitirAnular &&  permitirFinalizar
//		&& permitirConfirmar && permitirPermisoAux1
//		&& permitirPermisoAux2 && permitirPermisoAux3;

		return suma > 0 ? true : false;
	}

	@Override
	public int compareTo(Object o) {
		SistemaFormulariosPermisosCargos sistemaFormulariosPermisosUsuarios = (SistemaFormulariosPermisosCargos) o;
//		return this.sistemaFormulario.getNombre_formulario().compareTo(sistemaFormulariosPermisosUsuarios.getSistemaFormulario().getDescripcion());
		return this.sistemaFormulario.compareTo(sistemaFormulariosPermisosUsuarios.getSistemaFormulario());
	}
}
