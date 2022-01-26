/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model;

/**
 *
 * @author Administrador
 */
public class ModelImpl implements ModelIface{
    
//        private static CommonDao dao;
//
//        //private static SqlMapClient dao;
//
//        public ModelImpl() {
//            dao = new SqlMapCommonDao();
//            //dao = SqlMapClientDaoSuport.getSqlMapInstance();
//        }
//        /**
//         * Funcion que permite editar (registrar y actualizar) objetos en la BD.
//         * @param ob
//         */
//        public void editar(Object ob){
//            try {
//                dao.edit(ob);
//            } catch (SQLException ex) {
//                Logger.getLogger(ModelImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        /**
//         * Verifica si existe un objeto registrado en la base de datos
//         * @param ob
//         * @return
//         */
//        public boolean exist(Object ob){
//            try {
//                return dao.existsId(ob);
//            } catch (SQLException ex) {
//                Logger.getLogger(ModelImpl.class.getName()).log(Level.SEVERE, null, ex);
//                return false;
//            }
//        }
//
//        /**
//         * Funcion que permite eliminar un registro de la base de datos
//         * @param ob
//         */
//        public void eliminar(Object ob){
//            try {
//                dao.delete(ob);
//            } catch (SQLException ex) {
//                Logger.getLogger(ModelImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        public List getLists(String string){
//            try {
//                if (string.equalsIgnoreCase(PAIS))
//                    return dao.getObjects("Pais");
//            } catch (SQLException ex) {
//                System.out.println("Errorrrr "+ex.getMessage());
//            }
//            return null;
//        }
//
//        public List getLists(String string,Object obj){
//        //public List getLists(String string){
//            try {
//                if (string.equalsIgnoreCase(DEPARTAMENTO))
//                    return dao.getObjects("Departamento",obj);
//                if (string.equalsIgnoreCase(COLEGIO))
//                    return dao.getObjects("Colegio",obj);
//            } catch (SQLException ex){
//                System.out.println("Errorrrr "+ex.getMessage());
//            }
//            return null;
//        }
//
//        public Postulante getPostulante(String ci){
//            Postulante postu = new Postulante();
//            postu.setCi(ci);
//            try{
//                return (Postulante) dao.obtenerObjeto(postu);
//            }catch(SQLException e){
//                System.out.println("Error al hacer la consulta "+e.getMessage());
//                return null;
//            }
//            //return (Postulante) dao.queryForObject("ObtenerPostulante", ci);
//        }
//
//        public boolean setPostulante(Postulante postu){
//            System.err.println(//postu.getAnioGraduacion()+"\n"+
//                    postu.getApellidos()+"\n"+
//                    postu.getCi()+"\n"+
//                    postu.getDireccion()+"\n"+
//                    postu.getEmail()+"\n"+
//                    postu.getIdColegio()+"\n"+
//                    postu.getIdDepartamento()+"\n"+
//                    postu.getIdPostulante()+"\n"+
//                    postu.getNombres()+"\n"+
//                    postu.getTelfcasa()+"\n"+
//                    postu.getTelfcel()+"\n"+
//                    //postu.getAnioGraduacion()+"\n"+
//                    postu.getEdad()
//                    );
//            try{
//                dao.edit(postu);
//                return true;
//            }catch(SQLException e){
//                System.err.println("Herror: " + e.getMessage());
//                    return false;
//            }
//        }

}
