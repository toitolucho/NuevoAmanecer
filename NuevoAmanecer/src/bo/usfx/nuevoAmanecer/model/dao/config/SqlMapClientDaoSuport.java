/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bo.usfx.nuevoAmanecer.model.dao.config;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
public class SqlMapClientDaoSuport{
        private static final SqlMapClient sqlMap;
                static {
                        try {
                                Reader reader = Resources.getResourceAsReader("bo/usfx/nuevoAmanecer/model/dao/config/sql-map-config.xml");
                                sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
                        } catch (Exception e) {
                        // Si hay un error en este punto, no importa cual sea. Será un error irrecuperable del cual
                        // nos interesará solo estar informados.
                        // Deberás registrar el error y reenviar la excepción de forma que se te notifique el
                        // problema de forma inmediata.
                                e.printStackTrace();
                                throw new RuntimeException ("Error initializing MyAppSqlConfig class. Cause: " + e);
                        }
                }
        public static SqlMapClient getSqlMapInstance () {
                return sqlMap;
        }
}