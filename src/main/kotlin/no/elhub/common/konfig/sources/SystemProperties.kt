package no.elhub.common.konfig.sources

import java.util.*

class SystemProperties : PropertySource() {

    override fun load(properties: Properties) {
        System.getProperties().forEach { key, value ->
            properties[key] = value
        }
    }

}
