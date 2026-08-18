def call(Map config = [:]) {
    String application = requireValue(config, 'application')
    String command = requireValue(config, 'command')

    echo "Building ${application}"

    sh(
        label: "Build ${application}",
        script: command
    )
}

private String requireValue(Map config, String key) {
    def value = config[key]

    if (value == null || value.toString().trim().isEmpty()) {
        error "Missing required parameter: ${key}"
    }

    value.toString()
}