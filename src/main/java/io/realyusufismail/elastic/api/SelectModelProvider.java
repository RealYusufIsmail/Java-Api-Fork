package io.realyusufismail.elastic.api;


import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Interface to be implemented by components which want to provide select model.
 */
public interface SelectModelProvider {

    /**
     * Allows a component to dynamically populate select boxes. The given <i>configuration</i>
     * object contains authentication data that might be required if the values are retrieved from
     * the desired service. The method must return a simple JSON object in which the keys are mapped
     * to human-readable labels, as shown in the following example.
     * 
     * <pre>
     * <code>
     *
     * {
     *     "de" : "Germany"
     *     "us" : "United States"
     *     "uk" : "United Kingdom"
     * }
     * </code>
     * </pre>
     *
     * @param configuration Config data needed to execute the method
     * @return the select model for a particular select box
     */
    ObjectNode getSelectModel(ObjectNode configuration);
}
