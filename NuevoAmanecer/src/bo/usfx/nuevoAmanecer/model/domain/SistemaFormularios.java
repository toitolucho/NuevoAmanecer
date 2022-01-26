package bo.usfx.nuevoAmanecer.model.domain;

public class SistemaFormularios implements Comparable<SistemaFormularios> {
	private int codigo_formulario;
	private String nombre_formulario;
	private boolean estado;
	private String descripcion;
	
	
	public SistemaFormularios(int idformulario, String nombreFormulario,
			boolean estado, String descripcion) {
		super();
		this.codigo_formulario = idformulario;
		nombre_formulario = nombreFormulario;
		this.estado = estado;
		this.descripcion = descripcion;
	}
	
	public SistemaFormularios()
	{
		codigo_formulario = -1;
		nombre_formulario = "formlario sin Nombre";
		estado = false;
		descripcion = "Ninguna";
	}

        public int getCodigo_formulario() {
            return codigo_formulario;
        }

        public void setCodigo_formulario(int codigo_formulario) {
            this.codigo_formulario = codigo_formulario;
        }

	

	public String getNombre_formulario() {
		return nombre_formulario;
	}

	public void setNombre_formulario(String nombreFormulario) {
		nombre_formulario = nombreFormulario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {		
		return this.nombre_formulario;
	}

	@Override
	public int compareTo(SistemaFormularios o) {
		// TODO Auto-generated method stub
		return this.nombre_formulario.compareTo(o.getNombre_formulario());
	}
	
	
	
	
}
