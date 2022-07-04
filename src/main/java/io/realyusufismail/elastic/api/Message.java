package io.realyusufismail.elastic.api;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.Serializable;
import java.util.UUID;

/**
 * Message to be processed by a {@link IModule}. A message may have a body, which represents a
 * message's payload to be processed, and multiple attachments. Both body and attachments are
 * {@link ObjectNode}s.
 *
 * <p>
 *
 * A {@link IModule} may retrieve a value from {@link Message}'s body by a name, as shown in the
 * following example.
 *
 * <pre>
 * {
 *     &#64;code
 *     JsonArray orders = message.getBody().getJsonArray("orders");
 * }
 * </pre>
 *
 * <p>
 *
 * A message is build using {@link Builder}, as shown in the following example.
 *
 * <pre>
 * {
 *     &#64;code
 *     JsonArray orders = JSON.parseArray(response.getOrders());
 *
 *     ObjectNode body = Json.createObjectBuilder().add("orders", orders).build();
 *
 *     Message message = new Message.Builder().body(body).build();
 * }
 * </pre>
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_BODY = "body";
    public static final String PROPERTY_HEADERS = "headers";
    public static final String PROPERTY_ATTACHMENTS = "attachments";
    public static final String PROPERTY_PASSTHROUGH = "passthrough";

    private UUID id;
    private ObjectNode headers;
    private ObjectNode body;
    private ObjectNode attachments;
    private ObjectNode passthrough;

    /**
     * Creates a message with headers, body and attachments.
     *
     * @param id id of the message
     * @param headers headers of the message
     * @param body body of the message
     * @param attachments attachments of the message
     * @param passthrough passthrough of the message
     */
    private Message(final UUID id, final ObjectNode headers, final ObjectNode body,
            final ObjectNode attachments, final ObjectNode passthrough) {

        if (id == null) {
            throw new IllegalArgumentException("Message id must not be null");
        }

        if (headers == null) {
            throw new IllegalArgumentException("Message headers must not be null");
        }

        if (body == null) {
            throw new IllegalArgumentException("Message body must not be null");
        }

        if (attachments == null) {
            throw new IllegalArgumentException("Message attachments must not be null");
        }

        if (passthrough == null) {
            throw new IllegalArgumentException("Message passthrough must not be null");
        }

        this.id = id;
        this.headers = headers;
        this.body = body;
        this.attachments = attachments;
        this.passthrough = passthrough;
    }

    /**
     * Returns message id.
     *
     * @return id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns message headers.
     *
     * @return headers
     */
    public ObjectNode getHeaders() {
        return headers;
    }

    /**
     * Returns message body.
     *
     * @return body
     */
    public ObjectNode getBody() {
        return body;
    }

    /**
     * Returns message attachments.
     *
     * @return attachments
     */
    public ObjectNode getAttachments() {
        return attachments;
    }

    /**
     * Returns message passthrough.
     *
     * @return passthrough
     */
    public ObjectNode getPassthrough() {
        return passthrough;
    }

    /**
     * Returns this message as {@link ObjectNode}.
     *
     * @return message as JSON object
     */
    public ObjectNode toObjectNode() {
        ObjectNode message = JsonNodeFactory.instance.objectNode();
        message.put(PROPERTY_ID, id.toString());
        message.set(PROPERTY_HEADERS, headers);
        message.set(PROPERTY_BODY, body);
        message.set(PROPERTY_ATTACHMENTS, attachments);
        message.set(PROPERTY_PASSTHROUGH, passthrough);
        return message;
    }

    @Override
    public String toString() {
        final ObjectNode json = JsonNodeFactory.instance.objectNode();
        return json.toString();
    }

    /**
     * Used to build {@link Message} instances.
     */
    public static final class Builder {
        private UUID id;
        private ObjectNode headers;
        private ObjectNode body;
        private ObjectNode attachments;
        private ObjectNode passthrough;

        /**
         * Default constructor.
         */
        public Builder() {
            this.id = UUID.randomUUID();
            this.headers = JsonNodeFactory.instance.objectNode();
            this.body = JsonNodeFactory.instance.objectNode();
            this.attachments = JsonNodeFactory.instance.objectNode();
            this.passthrough = JsonNodeFactory.instance.objectNode();
        }

        /**
         * Sets message id.
         *
         * @param id id for the message
         * @return same builder instance
         */
        public Builder id(final UUID id) {

            this.id = id;

            return this;
        }

        /**
         * Adds a headers to build message with.
         *
         * @param headers headers for the message
         * @return same builder instance
         */
        public Builder headers(final ObjectNode headers) {

            this.headers = headers;

            return this;
        }

        /**
         * Adds a body to build message with.
         *
         * @param body body for the message
         * @return same builder instance
         */
        public Builder body(final ObjectNode body) {

            this.body = body;

            return this;
        }

        /**
         * Adds attachments to build message with.
         *
         * @param attachments attachments for the message
         * @return same builder instance
         */
        public Builder attachments(final ObjectNode attachments) {
            this.attachments = attachments;

            return this;
        }

        /**
         * Adds passthrough to build message with.
         *
         * @param passthrough passthrough for the message
         * @return same builder instance
         */
        public Builder passthrough(final ObjectNode passthrough) {
            this.passthrough = passthrough;

            return this;
        }

        /**
         * Builds a {@link Message} instance and returns it.
         *
         * @return Message
         */
        public Message build() {
            return new Message(this.id, this.headers, this.body, this.attachments,
                    this.passthrough);
        }
    }
}
