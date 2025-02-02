package io.realyusufismail.elastic.api;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A component is an unit implementing a custom business logic to be executed in the elastic.io
 * runtime.
 *
 * <p>
 * A component is executed with a {@link ExecutionParameters} which provides the component with a
 * {@link Message}, configuration and a snapshot.
 * </p>
 *
 * <p>
 * The given {@link Message} represents component's input. Besides other things it contains a
 * payload to be consumed by a component.
 * </p>
 *
 * <p>
 * A configuration is an instance {@link ObjectNode} containing required information, such as API
 * key or username/password combination, that components needs to collect from user to function
 * properly.
 * </p>
 *
 * <p>
 * A snapshot is an instance of {@link ObjectNode} that represents component's state. For example, a
 * Twitter timeline component might store the id of the last retrieved tweet for the next execution
 * in order to ask Twitter for tweets whose ids are greater than the one in the snapshot.
 * </p>
 *
 * A component communicates with elastic.io runtime by emitting events, such as <i>data</i>,
 * <i>error</i>, etc. For more info please check out {@link EventEmitter}.
 *
 * <p>
 * The following example demonstrates a simple component which echos the incoming message.
 * </p>
 *
 * <pre>
 * <code>
 *
 * public class EchoComponent implements IModule {
 *
 *
 *    &#064;Override
 *    public void execute(ExecutionParameters parameters) {
 *
 *       final ObjectNode snapshot = JsonNodeFactory.instance.objectNode()
 *               .put("echo", parameters.getSnapshot());
 *
 *       parameters.getEventEmitter()
 *          .emitSnapshot(snapshot)
 *          .emitData(echoMessage(parameters));
 *    }
 *
 *    private Message echoMessage(ExecutionParameters parameters) {
 *
 *       final Message msg = parameters.getMessage();
 *
 *       final ObjectNode body = JsonNodeFactory.instance.objectNode()
 *               .puy("echo", msg.getBody())
 *               .puy("config", parameters.getConfiguration());
 *
 *       return new Message.Builder()
 *                   .body(body)
 *                   .attachments(msg.getAttachments())
 *                   .build();
 *    }
 * }
 * </code>
 * </pre>
 *
 * @see ExecutionParameters
 * @see Message
 * @see EventEmitter
 */
public interface IModule {

    /**
     * Executes this component with the given {@link ExecutionParameters}.
     *
     * @param parameters parameters to execute component with
     */
    void execute(ExecutionParameters parameters);

    /**
     * Used to initialize the component on flow start. For example, a webhook trigger can subscribe
     * a webhook url to receive events from a target API inside this method. The subscription data,
     * such as ID, can be returned from this method as JSON object for persistence in the platform.
     * Once <i>shutdown</i> method is supported, the persisted JSON will be passed as argument to
     * <i>shutdown</i> method where the subscription can be canceled on flow stop.
     *
     * @since 2.0
     *
     * @param configuration component's configuration
     * @return JSON object to be persisted or null
     */
    default ObjectNode startup(final ObjectNode configuration) {
        return JsonNodeFactory.instance.objectNode();
    }

    /**
     * Used to initialize the component on flow shutdown.
     *
     * @since 2.2.0
     *
     * @param configuration component's configuration
     * @return JSON object to be persisted or null
     */
    default ObjectNode shutdown(final ObjectNode configuration) {
        return JsonNodeFactory.instance.objectNode();
    }

    /**
     * Used to initialize a component before message processing. For polling flows this method is
     * called once per scheduled execution. For real-time flows this method is called once on first
     * execution.
     *
     * @since 2.0
     *
     * @param configuration component's configuration
     */
    default void init(final ObjectNode configuration) {
        // default implementation does nothing
    }
}
