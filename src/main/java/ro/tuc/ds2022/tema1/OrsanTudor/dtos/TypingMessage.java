package ro.tuc.ds2022.tema1.OrsanTudor.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;
import java.util.UUID;

public class TypingMessage
{
    @NotNull
    private UUID clientIdTyping;

    @NotNull
    private String typing;

    public TypingMessage()
    {

    }

    public TypingMessage(UUID clientIdTyping, String typing)
    {
        this.clientIdTyping = clientIdTyping;
        this.typing = typing;
    }

    public UUID getClientIdTyping() {
        return clientIdTyping;
    }
    public void setClientIdTyping(UUID clientIdTyping) {
        this.clientIdTyping = clientIdTyping;
    }
    public String getTyping() {
        return typing;
    }
    public void setTyping(String typing) {
        this.typing = typing;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypingMessage that = (TypingMessage) o;
        return Objects.equals(typing, that.typing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientIdTyping, typing);
    }
}





