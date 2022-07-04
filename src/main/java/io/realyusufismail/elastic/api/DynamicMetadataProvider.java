package io.realyusufismail.elastic.api;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Interface to be implemented by components which want to provide dynamic metadata.
 */
public interface DynamicMetadataProvider {

    /**
     * Allows a component to provide dynamic metadata.
     *
     * @param configuration data needed to execute the method
     * @return The JSON schema representing the meta model for this component
     */
    ObjectNode getMetaModel(ObjectNode configuration);
}
