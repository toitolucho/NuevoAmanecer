package bo.usfx.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuscadorObjetos {

	private List keywordList = new ArrayList();
        private int filtroEntero = -1;

	@SuppressWarnings("unchecked")
	public BuscadorObjetos(String keywords) {
		StringTokenizer splitter = new StringTokenizer(keywords, ",", false);
		while (splitter.hasMoreTokens()) {
			String key = splitter.nextToken();
			String cl = new String("");
			if (key.indexOf("*") < 0 && key.indexOf("?") < 0)
				cl += "%" + key + "%";
			else
				for (int i = 0; i < key.length(); i++)
					cl += (key.charAt(i) == '*') ? '%': (key.charAt(i) == '?') ? '_' : key.charAt(i);
			//System.out.println("key: " + key + "  formateado: " + cl);
			this.keywordList.add(cl);
		}
		System.out.println("a Buscar: "+keywordList);
	}

    public List getKeywordList() {
        return keywordList;
    }
//       public BuscadorObjetos(String keywords, int filtroEntero) {
//        StringTokenizer splitter = new StringTokenizer(keywords, ",", false);
//        while (splitter.hasMoreTokens()) {
//            String key = splitter.nextToken();
//            String cl = new String("");
//            if (key.indexOf("*") < 0 && key.indexOf("?") < 0) {
//                cl += "%" + key + "%";
//            } else {
//                for (int i = 0; i < key.length(); i++) {
//                    cl += (key.charAt(i) == '*') ? '%' : (key.charAt(i) == '?') ? '_' : key.charAt(i);
//                }
//            }
//            //System.out.println("key: " + key + "  formateado: " + cl);
//            this.palabrasClave.add(cl);
//        }
//        this.filtroEntero = filtroEntero;
//        System.out.println("a Buscar: " + palabrasClave + ", Filtro Entero " + this.filtroEntero);
//
//
//    }

    

    public int getFiltroEntero() {
        return filtroEntero;
    }

    public void setFiltroEntero(int filtroEntero) {
        this.filtroEntero = filtroEntero;
        System.out.println("Filtro Entero "+ this.filtroEntero);
    }

    public String toString()
    {
        return this.keywordList +", Filtro Entero :" + filtroEntero;
    }

//	public List getKeywordList() {
//		// System.out.println(keywordList.toString() +"  "+keywordList.size());
//		return palabrasClave;
//	}
    
        
     


}
