package no.elhub.common.konfig.sources

import java.util.*

class ResourceFile(private val fileName: String) : PropertySource() {

    override fun load(properties: Properties) {
        val classLoader = ClassLoader.getSystemClassLoader()
        load(classLoader.getResource(fileName)?.openStream(), properties) {
            "resource $fileName not found"
        }
    }

}
