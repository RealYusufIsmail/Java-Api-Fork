package io.elastic.api.demo;

import io.realyusufismail.elastic.api.ExecutionParameters;
import io.realyusufismail.elastic.api.IModule;

public class ErroneousComponent implements IModule {

    @Override
    public void execute(ExecutionParameters parameters) {

        throw new RuntimeException("Ouch! We did not expect that");
    }
}
