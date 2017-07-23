package com.kelly.usuario.Classes;

/**
 * Created by KELLY on 16-Jul-17.
 */

public class Schedule {
    private String costo;
    private String order;
    private String slots;
    private String timeEnd;
    private String timeInit;
    private String choferID;
    private String idTravel;
    public Schedule(){}
    public Schedule(Schedule schedule){
        this.setOrder(schedule.getOrder());
        this.setSlots(schedule.getSlots());
        this.setTimeEnd(schedule.getTimeEnd());
        this.setTimeInit(schedule.getTimeInit());
        this.setCosto(schedule.getCosto());
        this.setChoferID(schedule.getChoferID());
    }
    public Schedule(String order,String slost,String timeEnd,String timeInit){
        this.setOrder(order);
        this.setSlots(slost);
        this.setTimeEnd(timeEnd);
        this.setTimeInit(timeInit);
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSlots() {
        return slots;
    }

    public void setSlots(String slots) {
        this.slots = slots;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTimeInit() {
        return timeInit;
    }

    public void setTimeInit(String timeInit) {
        this.timeInit = timeInit;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getChoferID() {
        return choferID;
    }

    public void setChoferID(String choferID) {
        this.choferID = choferID;
    }

    public String getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(String idTravel) {
        this.idTravel = idTravel;
    }
}
