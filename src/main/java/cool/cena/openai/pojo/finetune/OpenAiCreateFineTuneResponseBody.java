package cool.cena.openai.pojo.finetune;

import java.util.List;

public class OpenAiCreateFineTuneResponseBody extends OpenAiFineTuneResponseBody {

    List<OpenAiFineTuneEvent> events;

    public List<OpenAiFineTuneEvent> getEvents() {
        return events;
    }

    public void setEvents(List<OpenAiFineTuneEvent> events) {
        this.events = events;
    }
    
}
