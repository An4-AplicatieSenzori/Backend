package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;
import java.util.UUID;

public class ClientMessage
{
    //Message and Id:
    //Client si Admin message, in teorie la amandoi este asa, dar fie, macar merge;
    @NotNull
    private String clientMessage;
    //messageFromClient;

    @NotNull
    private String name;

    @NotNull
    private UUID clientId;

    @NotNull
    private UUID adminId;

    //Constructor gol;
    public ClientMessage() {
    }

    public ClientMessage(String clientMessage, String name, UUID clientId, UUID adminId)
    {
        //this.messageFromClient = messageFromClient;
        this.clientMessage = clientMessage;
        this.name = name;
        this.clientId = clientId;
        this.adminId = adminId;
    }

    public String getClientMessage() {
        //return clientMessage;
        return clientMessage;
    }
    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }
    public UUID getClientId() {
        return clientId;
    }
    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UUID getAdminId() {
        return adminId;
    }
    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientMessage that = (ClientMessage) o;
        return Objects.equals(clientMessage, that.clientMessage) &&
                Objects.equals(name, that.name);
//                && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientMessage, name, clientId, adminId);
    }
}
























