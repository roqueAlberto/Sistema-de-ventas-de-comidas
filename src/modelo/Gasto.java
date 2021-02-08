

package modelo;

import java.sql.Date;



public class Gasto {

    private String desc_gasto;
    private int monto;
    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

    public String getDesc_gasto() {
        return desc_gasto;
    }

    public void setDesc_gasto(String desc_gasto) {
        this.desc_gasto = desc_gasto;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

}
