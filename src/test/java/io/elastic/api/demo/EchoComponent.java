package io.elastic.api.demo;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.realyusufismail.elastic.api.ExecutionParameters;
import io.realyusufismail.elastic.api.IModule;
import io.realyusufismail.elastic.api.Message;

public class EchoComponent implements IModule {

    public void execute(ExecutionParameters parameters) {

        final ObjectNode snapshot =
                JsonNodeFactory.instance.objectNode().set("echo", parameters.getSnapshot());

        parameters.getEventEmitter().emitSnapshot(snapshot).emitData(echoMessage(parameters));
    }

    private Message echoMessage(ExecutionParameters parameters) {

        final Message msg = parameters.getMessage();

        final ObjectNode body = JsonNodeFactory.instance.objectNode()
            .set("echo", msg.getBody());

        body.set("config", parameters.getConfiguration());

        return new Message.Builder().body(body).attachments(msg.getAttachments()).build();
    }
}
