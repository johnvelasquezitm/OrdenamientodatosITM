package ordenamientodatos;

public class ArbolBinario {

    private Nodo raiz;
    private int criterio;

    public ArbolBinario(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArbolBinario() {
    }

    public int getCriterio() {
        return criterio;
    }

    public void setCriterio(int criterio) {
        this.criterio = criterio;
    }

    /*
     public boolean insertarNodo(Nodo n) {
     if (raiz == null) {
     raiz = n;
     return true;
     } else {
     return insertar(raiz, n);
     }
     }
     */
    private boolean insertar(Nodo nRecorrido, Nodo n) {
        //no insertar si el documento es igual
        if (n.getDocumento().equals(nRecorrido.getDocumento())) {
            return false;
        } else if (!Documento.esMayor(n.getDocumento(), nRecorrido.getDocumento(), criterio)) {
            //insertar a la izquierda del nodo
            //esta disponible el nodo izquierdo?
            if (nRecorrido.izquierdo == null) {
                nRecorrido.izquierdo = n;
                return true;
            } else {
                //continuar recorriendo el subarbol izquierdo
                return insertar(nRecorrido.izquierdo, n);
            }
        } else {
            //insertar a la derecha del nodo
            //esta disponible el nodo izquierdo?
            if (nRecorrido.derecho == null) {
                nRecorrido.derecho = n;
                return true;
            } else {
                //continuar recorriendo el subarbol izquierdo
                return insertar(nRecorrido.derecho, n);
            }
        }
    }

    public boolean insertarNodo(Nodo n) {
        if (raiz == null) {
            raiz = n;
            return true;
        }

        Nodo actual = raiz;
        Nodo padre;

        while (true) {
            padre = actual;

            // Si el documento es igual, no se permite la inserción
            if (n.getDocumento().equals(actual.getDocumento())) {
                return false;

            } else if (!Documento.esMayor(n.getDocumento(), actual.getDocumento(), criterio)) {
                // Si el documento es menor, avanza hacia el subárbol izquierdo
                actual = actual.izquierdo;
                if (actual == null) {
                    padre.izquierdo = n;
                    return true;
                }
            } // Si el documento es mayor, avanza hacia el subárbol derecho
            else {
                actual = actual.derecho;
                if (actual == null) {
                    padre.derecho = n;
                    return true;
                }
            }
        }
    }

}
