//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.6 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.07.10 时间 11:50:03 AM CST 
//


package io.renren.modules.miniapp.ws.client.schema;

import javax.xml.bind.annotation.*;


/**
 * <p>DocumentType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DocumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="doc_type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cntr_no" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="soa" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="vessel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="voyage" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="size" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="sealno" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="iso" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="truck_lic" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="truck_company" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="gatein_time" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="gateout_time" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="move_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="status_type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="consignee" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bookingno" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="loadport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="discport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="discport2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="destport" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="weight" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ol1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ol2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ow1" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ow2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="oh" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dg_no" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rf_temp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="despatch_temp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="truck_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="la" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="is_get" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="is_send" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="gs_split" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cont" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="cwi" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="remark" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="notice" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentType")
public class DocumentType {

    @XmlAttribute(name = "doc_type", required = true)
    protected String docType;
    @XmlAttribute(name = "cntr_no", required = true)
    protected String cntrNo;
    @XmlAttribute(name = "soa", required = true)
    protected String soa;
    @XmlAttribute(name = "vessel")
    protected String vessel;
    @XmlAttribute(name = "voyage")
    protected String voyage;
    @XmlAttribute(name = "size")
    protected String size;
    @XmlAttribute(name = "type")
    protected String type;
    @XmlAttribute(name = "height")
    @XmlSchemaType(name = "anySimpleType")
    protected String height;
    @XmlAttribute(name = "sealno")
    protected String sealno;
    @XmlAttribute(name = "iso")
    protected String iso;
    @XmlAttribute(name = "truck_lic")
    protected String truckLic;
    @XmlAttribute(name = "truck_company")
    protected String truckCompany;
    @XmlAttribute(name = "gatein_time")
    protected String gateinTime;
    @XmlAttribute(name = "gateout_time")
    protected String gateoutTime;
    @XmlAttribute(name = "move_type")
    protected String moveType;
    @XmlAttribute(name = "status_type")
    protected String statusType;
    @XmlAttribute(name = "consignee")
    protected String consignee;
    @XmlAttribute(name = "bookingno")
    protected String bookingno;
    @XmlAttribute(name = "loadport")
    protected String loadport;
    @XmlAttribute(name = "discport")
    protected String discport;
    @XmlAttribute(name = "discport2")
    protected String discport2;
    @XmlAttribute(name = "destport")
    protected String destport;
    @XmlAttribute(name = "weight")
    protected String weight;
    @XmlAttribute(name = "ol1")
    protected String ol1;
    @XmlAttribute(name = "ol2")
    protected String ol2;
    @XmlAttribute(name = "ow1")
    protected String ow1;
    @XmlAttribute(name = "ow2")
    protected String ow2;
    @XmlAttribute(name = "oh")
    protected String oh;
    @XmlAttribute(name = "dg_no")
    protected String dgNo;
    @XmlAttribute(name = "rf_temp")
    protected String rfTemp;
    @XmlAttribute(name = "despatch_temp")
    protected String despatchTemp;
    @XmlAttribute(name = "truck_code")
    protected String truckCode;
    @XmlAttribute(name = "la")
    protected String la;
    @XmlAttribute(name = "is_get")
    protected String isGet;
    @XmlAttribute(name = "is_send")
    protected String isSend;
    @XmlAttribute(name = "gs_split")
    protected String gsSplit;
    @XmlAttribute(name = "cont")
    protected String cont;
    @XmlAttribute(name = "cwi")
    protected String cwi;
    @XmlAttribute(name = "remark")
    protected String remark;
    @XmlAttribute(name = "notice")
    protected String notice;

    /**
     * 获取docType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocType() {
        return docType;
    }

    /**
     * 设置docType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocType(String value) {
        this.docType = value;
    }

    /**
     * 获取cntrNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCntrNo() {
        return cntrNo;
    }

    /**
     * 设置cntrNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCntrNo(String value) {
        this.cntrNo = value;
    }

    /**
     * 获取soa属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoa() {
        return soa;
    }

    /**
     * 设置soa属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoa(String value) {
        this.soa = value;
    }

    /**
     * 获取vessel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVessel() {
        return vessel;
    }

    /**
     * 设置vessel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVessel(String value) {
        this.vessel = value;
    }

    /**
     * 获取voyage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoyage() {
        return voyage;
    }

    /**
     * 设置voyage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoyage(String value) {
        this.voyage = value;
    }

    /**
     * 获取size属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置size属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * 获取type属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * 获取height属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeight() {
        return height;
    }

    /**
     * 设置height属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeight(String value) {
        this.height = value;
    }

    /**
     * 获取sealno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSealno() {
        return sealno;
    }

    /**
     * 设置sealno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSealno(String value) {
        this.sealno = value;
    }

    /**
     * 获取iso属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIso() {
        return iso;
    }

    /**
     * 设置iso属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIso(String value) {
        this.iso = value;
    }

    /**
     * 获取truckLic属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckLic() {
        return truckLic;
    }

    /**
     * 设置truckLic属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckLic(String value) {
        this.truckLic = value;
    }

    /**
     * 获取truckCompany属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckCompany() {
        return truckCompany;
    }

    /**
     * 设置truckCompany属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckCompany(String value) {
        this.truckCompany = value;
    }

    /**
     * 获取gateinTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateinTime() {
        return gateinTime;
    }

    /**
     * 设置gateinTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateinTime(String value) {
        this.gateinTime = value;
    }

    /**
     * 获取gateoutTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGateoutTime() {
        return gateoutTime;
    }

    /**
     * 设置gateoutTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGateoutTime(String value) {
        this.gateoutTime = value;
    }

    /**
     * 获取moveType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoveType() {
        return moveType;
    }

    /**
     * 设置moveType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoveType(String value) {
        this.moveType = value;
    }

    /**
     * 获取statusType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusType() {
        return statusType;
    }

    /**
     * 设置statusType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusType(String value) {
        this.statusType = value;
    }

    /**
     * 获取consignee属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsignee() {
        return consignee;
    }

    /**
     * 设置consignee属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsignee(String value) {
        this.consignee = value;
    }

    /**
     * 获取bookingno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookingno() {
        return bookingno;
    }

    /**
     * 设置bookingno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookingno(String value) {
        this.bookingno = value;
    }

    /**
     * 获取loadport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadport() {
        return loadport;
    }

    /**
     * 设置loadport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadport(String value) {
        this.loadport = value;
    }

    /**
     * 获取discport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscport() {
        return discport;
    }

    /**
     * 设置discport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscport(String value) {
        this.discport = value;
    }

    /**
     * 获取discport2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscport2() {
        return discport2;
    }

    /**
     * 设置discport2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscport2(String value) {
        this.discport2 = value;
    }

    /**
     * 获取destport属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestport() {
        return destport;
    }

    /**
     * 设置destport属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestport(String value) {
        this.destport = value;
    }

    /**
     * 获取weight属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeight() {
        return weight;
    }

    /**
     * 设置weight属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeight(String value) {
        this.weight = value;
    }

    /**
     * 获取ol1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOl1() {
        return ol1;
    }

    /**
     * 设置ol1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOl1(String value) {
        this.ol1 = value;
    }

    /**
     * 获取ol2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOl2() {
        return ol2;
    }

    /**
     * 设置ol2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOl2(String value) {
        this.ol2 = value;
    }

    /**
     * 获取ow1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOw1() {
        return ow1;
    }

    /**
     * 设置ow1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOw1(String value) {
        this.ow1 = value;
    }

    /**
     * 获取ow2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOw2() {
        return ow2;
    }

    /**
     * 设置ow2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOw2(String value) {
        this.ow2 = value;
    }

    /**
     * 获取oh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOh() {
        return oh;
    }

    /**
     * 设置oh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOh(String value) {
        this.oh = value;
    }

    /**
     * 获取dgNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDgNo() {
        return dgNo;
    }

    /**
     * 设置dgNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDgNo(String value) {
        this.dgNo = value;
    }

    /**
     * 获取rfTemp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfTemp() {
        return rfTemp;
    }

    /**
     * 设置rfTemp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfTemp(String value) {
        this.rfTemp = value;
    }

    /**
     * 获取despatchTemp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDespatchTemp() {
        return despatchTemp;
    }

    /**
     * 设置despatchTemp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDespatchTemp(String value) {
        this.despatchTemp = value;
    }

    /**
     * 获取truckCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTruckCode() {
        return truckCode;
    }

    /**
     * 设置truckCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTruckCode(String value) {
        this.truckCode = value;
    }

    /**
     * 获取la属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLa() {
        return la;
    }

    /**
     * 设置la属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLa(String value) {
        this.la = value;
    }

    /**
     * 获取isGet属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsGet() {
        return isGet;
    }

    /**
     * 设置isGet属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsGet(String value) {
        this.isGet = value;
    }

    /**
     * 获取isSend属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSend() {
        return isSend;
    }

    /**
     * 设置isSend属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSend(String value) {
        this.isSend = value;
    }

    /**
     * 获取gsSplit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGsSplit() {
        return gsSplit;
    }

    /**
     * 设置gsSplit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGsSplit(String value) {
        this.gsSplit = value;
    }

    /**
     * 获取cont属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCont() {
        return cont;
    }

    /**
     * 设置cont属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCont(String value) {
        this.cont = value;
    }

    /**
     * 获取cwi属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCwi() {
        return cwi;
    }

    /**
     * 设置cwi属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCwi(String value) {
        this.cwi = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * 获取notice属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 设置notice属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotice(String value) {
        this.notice = value;
    }

}
