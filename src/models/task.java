/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author AL
 */
public class task {

    private int id;
    private String titel;
    private String state;
    private String addedBy;
    private String creationDate;

    public task(int id, String titel, String state, String addedBy, String creationDate) {
        setId(id);
        setTitel(titel);
        setState(state);
        setAddedBy(addedBy);
        setCreationDate(creationDate);
    }

    public task() {
    }

    ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if (titel == null || titel.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty!");
        }
        this.titel = titel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state != null) {
            this.state = state;
        } else {
            throw new IllegalArgumentException("choose state");
        }
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        if (addedBy == null || addedBy.isEmpty()) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        this.addedBy = addedBy;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        if (creationDate == null || creationDate.isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty!");
        } else {
            this.creationDate = creationDate;
        }
    }

    @Override
    public String toString() {
        return this.id + " - " + this.titel + " - " + this.addedBy + " - " + this.state
                + " - " + this.creationDate;
    }

    public String toCSV() {
        return this.id + "," + this.titel + "," + this.state + "," + this.addedBy + "," + this.creationDate;
    }

}
