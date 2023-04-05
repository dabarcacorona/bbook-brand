package cl.corona.bbookbrand.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BBOOK_ENVIOMRC")
public class BbookEnviaBrand {

    @Id
    @Column(name="idd" ,columnDefinition = "varchar2(100)")
    private String id;

    @Column(name="atr_code" ,columnDefinition = "number(12)")
    private long atrCode;

    @Column(name="atr_code_desc" ,columnDefinition = "varchar2(100)")
    protected String atrCodeDesc;

    @Column(name="inactive" ,columnDefinition = "varchar2(10)")
    protected String inactive;

    @Column(name="tran_type" ,columnDefinition = "varchar2(1)")
    protected String tranType;

    public BbookEnviaBrand() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BbookEnviaBrand(String id, long atrCode, String atrCodeDesc, String inactive, String tranType) {
        super();
        this.id = id;
        this.atrCode = atrCode;
        this.atrCodeDesc = atrCodeDesc;
        this.inactive = inactive;
        this.tranType = tranType;

    }

    @Override
    public String toString() {
        return "BbookEnviaBrand [id=" + id + ", atrCode=" + atrCode + ", atrCodeDesc=" + atrCodeDesc
                + ", inactive=" + inactive + ", tranType=" + tranType +"]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAtrCode() {
        return atrCode;
    }

    public void setAtrCode(long atrCode) {
        this.atrCode = atrCode;
    }

    public String getatrCodeDesc() {
        return atrCodeDesc;
    }

    public void setatrCodeDesc(String atrCodeDesc) {
        this.atrCodeDesc = atrCodeDesc;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

}
