package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class ReadMessage
{
    @NotNull
    private UUID clientIdRead;

    @NotNull
    private String read;

    public ReadMessage()
    {

    }

    public ReadMessage(UUID clientIdRead, String read)
    {
        this.clientIdRead = clientIdRead;
        this.read = read;
    }

    public UUID getClientIdRead() {
        return clientIdRead;
    }
    public void setClientIdRead(UUID clientIdRead) {
        this.clientIdRead = clientIdRead;
    }
    public String getRead() {
        return read;
    }
    public void setRead(String read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadMessage that = (ReadMessage) o;
        return Objects.equals(read, that.read);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientIdRead, read);
    }
}





