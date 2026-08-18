package org.example

class PipelineUtils implements Serializable {

    private final def steps

    PipelineUtils(def steps) {
        this.steps = steps
    }

    String normalizedEnvironment(String environment) {
        String value = environment?.trim()?.toLowerCase()

        if (!(value in ['dev', 'test', 'prod'])) {
            steps.error("Unsupported environment: ${environment}")
        }

        value
    }

    boolean fileExists(String path) {
        steps.fileExists(path)
    }
}